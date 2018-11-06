package com.example.yunwen.testdemo1022.network.bean;

import java.util.List;

public class WeatherHistoryBean extends BaseBean {

    private List<DataBean> result;

    public List<DataBean> getBeanList() {
        return result;
    }

    public void setBeanList(List<DataBean> result) {
        this.result = result;
    }


    public static class DataBean {
        /**
         * "id":"1112",
         * "province_id":"16",
         * "city_name":"滨海"
         */
        private String id;
        private String province_id;
        private String city_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

    }



}
