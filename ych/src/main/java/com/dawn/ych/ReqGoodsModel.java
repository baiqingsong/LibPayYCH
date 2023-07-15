package com.dawn.ych;

import androidx.annotation.NonNull;

/**
 * 商品信息返回的数据
 */
class ReqGoodsModel {
    private int Price;//价格
    private int Amount;//数量
    private String GoodsName;//商品名称

    @NonNull
    @Override
    public String toString() {
        return "ReqGoodsModel{" +
                "Price=" + Price +
                ", Amount=" + Amount +
                ", GoodsName=" + GoodsName +
                '}';
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }
}
