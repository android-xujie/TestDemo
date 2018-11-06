package com.example.yunwen.testdemo1022.frames.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseDao<T> implements IBaseDao<T> {

    /**
     * 保证实例化一次
     */
    private boolean isInit = false;
    /**
     * 持有操作数据表所对应的java类型
     */
    private Class<T> entityClass;
    /**
     * 持有数据库操作类的引用
     */
    private SQLiteDatabase database;
    private String tableName;
    /**
     * 维护这表名与成员变量名的映射关系
     * key ----> 表名
     * value -----> Field
     */
    private HashMap<String, Field> cacheMap;

    protected synchronized boolean init(Class<T> entity, SQLiteDatabase sqLiteDatabase) {
        if (!isInit) {
            entityClass = entity;
            LogUtils.e("sqlite", entityClass.getSimpleName());
            database = sqLiteDatabase;
            if (entity.getAnnotation(DaTable.class) == null) {
                tableName = entity.getClass().getSimpleName();
            } else {
                tableName = entity.getAnnotation(DaTable.class).value();
            }
            if (!database.isOpen()) {
                return false;
            }
            if (!TextUtils.isEmpty(createTable())) {
                database.execSQL(createTable());
            }
            cacheMap = new HashMap<>();
            initCacheMap();
            isInit = true;
        }
        return isInit;
    }

    /**
     * 维护映射关系
     */
    private void initCacheMap(){
        String sql = "select * from " + this.tableName + " limit 1 , 0";
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(sql, null);
            /**
             * 表的列名数组
             */
            String[] columnNames = cursor.getColumnNames();
            /**
             * 拿到Field数组
             */
            Field[] columnFields = entityClass.getFields();
            for (Field field : columnFields) {
                field.setAccessible(true);
            }
            /**
             * 如果找到对应的Filed赋值给他
             * User
             */
            Field columnField = null;
            for (String colmunName : columnNames) {
                for (Field field : columnFields) {
                    String fileName = null;
                    if (field.getAnnotation(DbField.class) != null) {
                        fileName = field.getAnnotation(DbField.class).value();
                    } else {
                        fileName = field.getName();
                    }
                    /**
                     * 如果表的名字 等于 成员变量的注解名字
                     */
                    if (colmunName.equals(fileName)) {
                        columnField = field;
                    }
                }
                if (columnField != null) {
                    cacheMap.put(colmunName, columnField);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            cursor.close();
        }
    }

    @Override
    public Long insert(T entity) {
        Map<String, String> map = getValues(entity);
        ContentValues values = getContentValues(map);
        long result = database.insert(tableName, null, values);
        return result;
    }

    @Override
    public int update(T entity, T where) {
        int result = -1;
        Map<String, String> values = getValues(entity);
        Map<String, String> whereClause = getValues(where);
        Condition condition = new Condition(whereClause);
        ContentValues contentValues = getContentValues(values);
        result = database.update(tableName, contentValues, condition.getWhereClause(), condition.getWhereArgs());
        return result;
    }

    @Override
    public int delete(T where) {
        Map<String, String> map = getValues(where);
        Condition condition = new Condition(map);
        int result = database.delete(tableName, condition.getWhereClause(), condition.getWhereArgs());
        return result;
    }

    @Override
    public List<T> query(T where) {
        return query(where, null, null, null);
    }

    @Override
    public List<T> query(T where, String orderBy, Integer startIndex, Integer limit) {
        Map<String, String> map = getValues(where);
        String limiString = null;
        if (startIndex != null && limit != null) {
            limiString = startIndex + " , " + limit;
        }
        Condition condition = new Condition(map);
        Cursor cursor = database.query(tableName, null, condition.getWhereClause(), condition.getWhereArgs(), null, null, orderBy, limiString);
        List<T> result = getResult(cursor, where);
        cursor.close();
        return result;
    }

    protected List<T> getResult(Cursor cursor, T where){
        ArrayList list = new ArrayList<>();
        Object item;
        while (cursor.moveToNext()) {
            try {
                item =  where.getClass().newInstance();

                Iterator<Map.Entry<String, Field>> iterator = cacheMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String colomunName = (String) entry.getKey();
                    Integer columnIndex = cursor.getColumnIndex(colomunName);
                    Field field = (Field) entry.getValue();
                    Class<?> type = field.getType();
                    if (columnIndex != -1) {
                        if (type == String.class) {
                            field.set(item,cursor.getString(columnIndex));
                        } else if (type == Double.class) {
                            field.set(item, cursor.getDouble(columnIndex));
                        } else if (type == Integer.class) {
                            field.set(item, cursor.getInt(columnIndex));
                        }else if (type == Long.class) {
                            field.set(item, cursor.getLong(columnIndex));
                        }else if (type == Float.class) {
                            field.set(item, cursor.getFloat(columnIndex));
                        } else if (type == byte[].class) {
                            field.set(item, cursor.getBlob(columnIndex));
                        } else {
                            continue;
                        }
                    }
                }
                list.add(item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 转换成ContentValues
     * @param map
     * @return
     */
    protected ContentValues getContentValues(Map<String, String> map){
        ContentValues contentValues = new ContentValues();
        Set keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            if (value != null) {
                contentValues.put(key, value);
            }
        }
        return contentValues;
    }

    protected Map<String,String> getValues(T entity){
        HashMap<String, String> result = new HashMap<>();
        Iterator<Field> fieldIterator = cacheMap.values().iterator();
        /**
         * 循环遍历 映射map的 Field
         */
        while (fieldIterator.hasNext()) {
            Field columnToField = fieldIterator.next();
            String cacheKey = null;
            String cacheValue = null;
            if (columnToField.getAnnotation(DbField.class) != null) {
                cacheKey = columnToField.getAnnotation(DbField.class).value();
            } else {
                cacheKey = columnToField.getName();
            }

            try {
                if (null == columnToField.get(entity)) {
                    continue;
                }
                cacheValue = columnToField.get(entity).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            result.put(cacheKey, cacheValue);
        }
        return result;
    }

    class Condition{
        /**
         * 查询条件
         */
        private String whereClause;

        private String[] whereArgs;

        public Condition(Map<String, String> whereClause) {
            ArrayList list = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("1=1 ");
            Set<String> keys = whereClause.keySet();
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = whereClause.get(key);

                if (value != null) {
                    stringBuilder.append(" and " + key + " =?");
                    list.add(value);
                }
            }

            this.whereClause = stringBuilder.toString();
            this.whereArgs = (String[]) list.toArray(new String[list.size()]);
        }

        public String getWhereClause() {
            return whereClause;
        }

        public void setWhereClause(String whereClause) {
            this.whereClause = whereClause;
        }

        public String[] getWhereArgs() {
            return whereArgs;
        }

        public void setWhereArgs(String[] whereArgs) {
            this.whereArgs = whereArgs;
        }

    }


    /**
     * 创建表
     *
     * @return
     */
    protected abstract String createTable();


}
