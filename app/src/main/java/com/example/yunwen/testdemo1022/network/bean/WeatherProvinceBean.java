package com.example.yunwen.testdemo1022.network.bean;

import java.util.List;

public class WeatherProvinceBean extends BaseBean {

    private List<Result> result;

    public void setResult(List<Result> result) {
        this.result = result;
    }
    public List<Result> getResult() {
        return result;
    }

    public class Result {

        private String id;
        private String province;


        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }


        public void setProvince(String province) {
            this.province = province;
        }
        public String getProvince() {
            return province;
        }

    }
}
