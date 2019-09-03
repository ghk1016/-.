package com.bawei.guohaokun.model;

import java.util.List;

public class ModelBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public ModelBean(List<DataBean> data) {
        this.data = data;
    }

    public class DataBean{
        private String currency_price;
        private String goods_name;
        private String goods_thumb;

        @Override
        public String toString() {
            return "DataBean{" +
                    "currency_price='" + currency_price + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", goods_thumb='" + goods_thumb + '\'' +
                    '}';
        }

        public String getCurrency_price() {
            return currency_price;
        }

        public void setCurrency_price(String currency_price) {
            this.currency_price = currency_price;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public DataBean(String currency_price, String goods_name, String goods_thumb) {
            this.currency_price = currency_price;
            this.goods_name = goods_name;
            this.goods_thumb = goods_thumb;
        }
    }
}
