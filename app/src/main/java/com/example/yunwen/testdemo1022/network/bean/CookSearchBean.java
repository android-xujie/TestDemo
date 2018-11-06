package com.example.yunwen.testdemo1022.network.bean;

import java.util.List;

public class CookSearchBean extends BaseBean {

    private String resultcode;
    private ResultBean result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CookSearchBean{" +
                "resultcode='" + resultcode + '\'' +
                ", result=" + result +
                '}';
    }


    public class ResultBean {
        private List<DataBean> data;
        private String totalNum;
        private int pn;
        private int rn;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public int getPn() {
            return pn;
        }

        public void setPn(int pn) {
            this.pn = pn;
        }

        public int getRn() {
            return rn;
        }

        public void setRn(int rn) {
            this.rn = rn;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "data=" + data +
                    ", totalNum='" + totalNum + '\'' +
                    ", pn=" + pn +
                    ", rn=" + rn +
                    '}';
        }
    }

    public class DataBean {

        private String id;
        private String title;
        private String tags;
        private String imtro;
        private String ingredients;
        private String burden;
        private List<String> albums;
        private List<Steps> steps;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getImtro() {
            return imtro;
        }

        public void setImtro(String imtro) {
            this.imtro = imtro;
        }

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public String getBurden() {
            return burden;
        }

        public void setBurden(String burden) {
            this.burden = burden;
        }

        public List<String> getAlbums() {
            return albums;
        }

        public void setAlbums(List<String> albums) {
            this.albums = albums;
        }

        public List<Steps> getSteps() {
            return steps;
        }

        public void setSteps(List<Steps> steps) {
            this.steps = steps;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", tags='" + tags + '\'' +
                    ", imtro='" + imtro + '\'' +
                    ", ingredients='" + ingredients + '\'' +
                    ", burden='" + burden + '\'' +
                    ", albums=" + albums +
                    ", steps=" + steps +
                    '}';
        }
    }

    public class Steps {

        private String img;
        private String step;


        public void setImg(String img) {
            this.img = img;
        }
        public String getImg() {
            return img;
        }


        public void setStep(String step) {
            this.step = step;
        }
        public String getStep() {
            return step;
        }

        @Override
        public String toString() {
            return "Steps{" +
                    "img='" + img + '\'' +
                    ", step='" + step + '\'' +
                    '}';
        }
    }

}
