package com.dawn.ych;

import androidx.annotation.NonNull;

/**
 * 设备信息返回的数据
 */
public class ResDeviceInfoModel extends BaseResModel{
    private ResData Data;

    @NonNull
    @Override
    public String toString() {
        return "ResDeviceInfoModel{" +
                "ResponseStatus=" + ResponseStatus +
                ", Data=" + Data +
                '}';
    }

    public ResData getData() {
        return Data;
    }

    public static class ResData{
        private String Sign;//签名
        private int TimeStamp;//时间戳
        private String MacID;//设备id
        private boolean HasBinding;//是否绑定
        private String BizServer;//业务服务器地址
        private String AppKey;//加密key

        @NonNull
        @Override
        public String toString() {
            return "ResData{" +
                    "Sign='" + Sign + '\'' +
                    ", TimeStamp=" + TimeStamp +
                    ", MacID='" + MacID + '\'' +
                    ", HasBinding=" + HasBinding +
                    ", BizServer='" + BizServer + '\'' +
                    ", AppKey='" + AppKey + '\'' +
                    '}';
        }

        public String getSign() {
            return Sign;
        }

        public int getTimeStamp() {
            return TimeStamp;
        }

        public String getMacID() {
            return MacID;
        }

        public boolean isHasBinding() {
            return HasBinding;
        }

        public String getBizServer() {
            return BizServer;
        }

        public String getAppKey() {
            return AppKey;
        }
    }
}
