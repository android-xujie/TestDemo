package com.example.yunwen.testdemo1022.network.bean;

import java.util.List;

public class CookCategoryBean extends BaseCookBean {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    public class Result {


        private Categoryinfo categoryInfo;
        private List<Childs> childs;


        public void setCategoryinfo(Categoryinfo categoryinfo) {
            this.categoryInfo = categoryinfo;
        }

        public Categoryinfo getCategoryinfo() {
            return categoryInfo;
        }


        public void setChilds(List<Childs> childs) {
            this.childs = childs;
        }

        public List<Childs> getChilds() {
            return childs;
        }

        public class Categoryinfo {


            private String ctgId;
            private String name;


            public void setCtgid(String ctgid) {
                this.ctgId = ctgid;
            }

            public String getCtgid() {
                return ctgId;
            }


            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

        }

        public class Childs {

            private Categoryinfos categoryInfo;
            private List<Childss> childs;


            public void setCategoryinfo(Categoryinfos categoryinfo) {
                this.categoryInfo = categoryinfo;
            }

            public Categoryinfos getCategoryinfo() {
                return categoryInfo;
            }


            public void setChilds(List<Childss> childs) {
                this.childs = childs;
            }

            public List<Childss> getChilds() {
                return childs;
            }

            public class Childss {

                private Boolean isSelect;
                private Categoryinfos categoryInfo;

                public Boolean getSelect() {
                    return isSelect;
                }

                public void setSelect(Boolean select) {
                    isSelect = select;
                }

                public void setCategoryinfo(Categoryinfos categoryinfo) {
                    this.categoryInfo = categoryinfo;
                }

                public Categoryinfos getCategoryinfo() {
                    return categoryInfo;
                }

            }

            public class Categoryinfos {

                private String ctgId;
                private String name;
                private String parentId;

                public void setCtgid(String ctgid) {
                    this.ctgId = ctgid;
                }

                public String getCtgid() {
                    return ctgId;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getName() {
                    return name;
                }

                public void setParentid(String parentid) {
                    this.parentId = parentid;
                }

                public String getParentid() {
                    return parentId;
                }

            }

        }

    }


}
