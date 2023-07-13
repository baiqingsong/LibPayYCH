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

        @NonNull
        @Override
        public String toString() {
            return "ResData{" +
                    "Sign='" + Sign + '\'' +
                    ", TimeStamp=" + TimeStamp +
                    ", MacID='" + MacID + '\'' +
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
    }
}
