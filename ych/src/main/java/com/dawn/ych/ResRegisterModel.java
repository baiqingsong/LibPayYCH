package com.dawn.ych;

import androidx.annotation.NonNull;

/**
 * 注册返回的数据
 */
class ResRegisterModel extends BaseResModel{
    private ResData Data;

    @NonNull
    @Override
    public String toString() {
        return "ResRegisterModel{" +
                "Data=" + Data +
                '}';
    }

    public ResData getData() {
        return Data;
    }

    public static class ResData{
        private String AppKey;//应用key
        private String ProductKey;//iot的productkey
        private String DeviceName;//iot的devicename
        private String DeviceKey;//iot的devicekey

        @NonNull
        @Override
        public String toString() {
            return "ResRegisterModel{" +
                    "AppKey='" + AppKey + '\'' +
                    ", ProductKey='" + ProductKey + '\'' +
                    ", DeviceName='" + DeviceName + '\'' +
                    ", DeviceKey='" + DeviceKey + '\'' +
                    '}';
        }

        public String getAppKey() {
            return AppKey;
        }

        public String getProductKey() {
            return ProductKey;
        }

        public String getDeviceName() {
            return DeviceName;
        }

        public String getDeviceKey() {
            return DeviceKey;
        }
    }

}
