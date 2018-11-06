package com.example.yunwen.testdemo1022.network.http;

import com.example.yunwen.testdemo1022.network.bean.CookCategoryBean;
import com.example.yunwen.testdemo1022.network.bean.CookMenuQueryBean;
import com.example.yunwen.testdemo1022.network.bean.CookMenuSearchBean;
import com.example.yunwen.testdemo1022.network.bean.CookSearchBean;
import com.example.yunwen.testdemo1022.network.bean.LoginBean;
import com.example.yunwen.testdemo1022.network.bean.WeatherHistoryBean;
import com.example.yunwen.testdemo1022.network.bean.WeatherOldBean;


import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableNever;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * * 登录
     * *
     * * @param username     用户名
     * * @param password     密码
     * * @param type         登录类型
     * * @param google_token 谷歌推送token
     * * @returns    
     */
//    @FormUrlEncoded
//    @POST("/mobile/index.php?act=login&op=index")
//    Observable<LoginBean> login(@Field("username") String username, @Field("password") String password, @Field("type") String type, @Field("google_token") String google_token);
//    @FormUrlEncoded
//    @POST("/mobile/index.php?act=login&op=index")
//    Observable<LoginBean> login(@Field("username") String username, @Field("password") String password, @Field("type") String type, @Field("google_token") String google_token);
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean> login(@Field("username") String username,
                                @Field("password") String password,
                                @Field("type") String type,
                                @Field("google_token") String google_token);

    /**
     * 获取相关城市名称
     * @param key : 接口 key 值
     * @param province_id ： 城市id
     * @return
     */
    @FormUrlEncoded
    @POST("historyWeather/citys")
    Observable<WeatherHistoryBean> weatherhistory(@Field("key") String key,
                                                  @Field("province_id") String province_id);

    /**
     * 查询某城市过去的天气
     * @param key : 接口 key 值
     * @param city_id : 城市id
     * @param weather_date : 查询的日期值
     * @return
     */
    @GET("historyWeather/weather")
    Observable<WeatherOldBean> weatherold(@Query("key") String key,
                                          @Query("city_id") String city_id,
                                          @Query("weather_date") String weather_date);

    /**
     *
     * @param menu : 需要查询的菜谱名
     * @param dtype : 返回数据的格式,xml或json，默认json
     * @param pn : 数据返回起始下标
     * @param rn : 数据返回条数，最大30
     * @param albums : albums字段类型，1字符串，默认数组
     * @return
     */
    @GET("cook/query.php")
    Observable<CookSearchBean> cooksearch(@Query("menu") String menu,
                                          @Query("dtype") String dtype,
                                          @Query("pn") String pn,
                                          @Query("rn") String rn,
                                          @Query("albums") int albums,
                                          @Query("key") String key);

    //http://apicloud.mob.com/v1/cook/category/query

    /**
     * 获取菜谱分类结构
     * @param key 项目key值
     * @return
     */
    @GET("cook/category/query")
    Observable<CookCategoryBean> cookcategory(@Query("key") String key);

    ////  @Query("name") String name,

    /**
     * 根据分类id 获取该分类下的相关菜谱列表
     * @param key 项目key值
     * @param cid 菜谱id
     * @param page 当前页数
     * @param size 每页的条目数
     * @return
     */
    @GET("cook/menu/search")
    Observable<CookMenuSearchBean> cookmenusearchid(@Query("key") String key,
                                                  @Query("cid") String cid,
                                                  @Query("page") int page,
                                                  @Query("size") int size);

    /**
     * 根据菜谱名称 搜索 获取该分类下的相关菜谱列表
     * @param key 项目key值
     * @param name 菜谱name
     * @param page 当前页数
     * @param size 每页的条目数
     * @return
     */
    @GET("cook/menu/search")
    Observable<CookMenuSearchBean> cookmenusearchname(@Query("key") String key,
                                                  @Query("name") String name,
                                                  @Query("page") int page,
                                                  @Query("size") int size);

    /**
     * 根据单个菜谱名称，获取详细菜谱内容
     * @param key
     * @param id
     * @return
     */
    @GET("cook/menu/query")
    Observable<CookMenuQueryBean> cookmenuquery(@Query("key") String key, @Query("id") String id);
}
