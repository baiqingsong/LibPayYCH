package com.dawn.ych;

import java.util.List;

/**
 * 订单的实体类
 */
class ReqOrderModel {
    private String TransID;//订单号
    private String DeviceId;//设备id
    private List<ReqGoodsModel> GoodsItem;//商品列表

    public void setTransID(String transID) {
        TransID = transID;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public void setGoodsItem(List<ReqGoodsModel> goodsItem) {
        GoodsItem = goodsItem;
    }
}
