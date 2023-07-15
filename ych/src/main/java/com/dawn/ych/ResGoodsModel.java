package com.dawn.ych;

import androidx.annotation.NonNull;

/**
 * 商品信息返回的数据
 */
class ResGoodsModel extends BaseResModel{
    private ResData Data;

    @NonNull
    @Override
    public String toString() {
        return "ResGoodsModel{" +
                "ResponseStatus=" + ResponseStatus +
                ", Data=" + Data +
                '}';
    }

    public ResData getData() {
        return Data;
    }

    public static class ResData{
        private String QRCode;//二维码
        private String OrderNo;//订单号
        private String OrderID;//订单id
        private String TransID;//事务id

        @NonNull
        @Override
        public String toString() {
            return "ResData{" +
                    "QRCode='" + QRCode + '\'' +
                    ", OrderNo='" + OrderNo + '\'' +
                    ", OrderID='" + OrderID + '\'' +
                    ", TransID='" + TransID + '\'' +
                    '}';
        }

        public String getQRCode() {
            return QRCode;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public String getOrderID() {
            return OrderID;
        }

        public String getTransID() {
            return TransID;
        }
    }
}
