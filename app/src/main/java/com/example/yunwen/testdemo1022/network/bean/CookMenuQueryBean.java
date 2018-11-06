package com.example.yunwen.testdemo1022.network.bean;

import java.util.List;

public class CookMenuQueryBean extends BaseCookBean {

    private Result result;

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }


    public class Result {


        private List<String> ctgIds;
        private String ctgTitles;
        private String menuId;
        private String name;
        private Recipe recipe;
        private String thumbnail;


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
