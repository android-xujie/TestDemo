package com.example.yunwen.testdemo1022.network.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CookMenuSearchBean extends BaseCookBean implements Parcelable {

    private Result result;

    protected CookMenuSearchBean(Parcel in) {
    }

    public static final Creator<CookMenuSearchBean> CREATOR = new Creator<CookMenuSearchBean>() {
        @Override
        public CookMenuSearchBean createFromParcel(Parcel in) {
            return new CookMenuSearchBean(in);
        }

        @Override
        public CookMenuSearchBean[] newArray(int size) {
            return new CookMenuSearchBean[size];
        }
    };

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }


    public static class Result implements Parcelable{


        private int curPage;
        private List<Lists> list;
        private int total;


        protected Result(Parcel in) {
            curPage = in.readInt();
            list = in.createTypedArrayList(Lists.CREATOR);
            total = in.readInt();
        }

        public static final Creator<Result> CREATOR = new Creator<Result>() {
            @Override
            public Result createFromParcel(Parcel in) {
                return new Result(in);
            }

            @Override
            public Result[] newArray(int size) {
                return new Result[size];
            }
        };

        public void setCurpage(int curpage) {
            this.curPage = curpage;
        }

        public int getCurpage() {
            return curPage;
        }


        public void setList(List<Lists> list) {
            this.list = list;
        }

        public List<Lists> getList() {
            return list;
        }


        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(curPage);
            dest.writeTypedList(list);
            dest.writeInt(total);
        }
    }

        public static class Lists implements Parcelable{

            private List<String> ctgIds;
            private String ctgTitles;
            private String menuId;
            private String name;
            private Recipe recipe;
            private String thumbnail;


            protected Lists(Parcel in) {
                ctgIds = in.createStringArrayList();
                ctgTitles = in.readString();
                menuId = in.readString();
                name = in.readString();
                thumbnail = in.readString();
            }

            public static final Creator<Lists> CREATOR = new Creator<Lists>() {
                @Override
                public Lists createFromParcel(Parcel in) {
                    return new Lists(in);
                }

                @Override
                public Lists[] newArray(int size) {
                    return new Lists[size];
                }
            };

            public void setCtgids(List<String> ctgids) {
                this.ctgIds = ctgids;
            }
            public List<String> getCtgids() {
                return ctgIds;
            }


            public void setCtgtitles(String ctgtitles) {
                this.ctgTitles = ctgtitles;
            }
            public String getCtgtitles() {
                return ctgTitles;
            }


            public void setMenuid(String menuid) {
                this.menuId = menuid;
            }
            public String getMenuid() {
                return menuId;
            }


            public void setName(String name) {
                this.name = name;
            }
            public String getName() {
                return name;
            }


            public void setRecipe(Recipe recipe) {
                this.recipe = recipe;
            }
            public Recipe getRecipe() {
                return recipe;
            }


            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }
            public String getThumbnail() {
                return thumbnail;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeStringList(ctgIds);
                dest.writeString(ctgTitles);
                dest.writeString(menuId);
                dest.writeString(name);
                dest.writeString(thumbnail);
            }


            public class Recipe {

                private String img;
                private String ingredients;
                private String method;
                private String sumary;
                private String title;


                public void setImg(String img) {
                    this.img = img;
                }
                public String getImg() {
                    return img;
                }


                public void setIngredients(String ingredients) {
                    this.ingredients = ingredients;
                }
                public String getIngredients() {
                    return ingredients;
                }


                public void setMethod(String method) {
                    this.method = method;
                }
                public String getMethod() {
                    return method;
                }


                public void setSumary(String sumary) {
                    this.sumary = sumary;
                }
                public String getSumary() {
                    return sumary;
                }


                public void setTitle(String title) {
                    this.title = title;
                }
                public String getTitle() {
                    return title;
                }

            }
        }


}
