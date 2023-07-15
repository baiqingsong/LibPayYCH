package com.dawn.ych;

import androidx.annotation.NonNull;

/**
 * 测试连接返回的数据
 */
class ResConnectTestModel extends BaseResModel{
    private ResData Data;

    @NonNull
    @Override
    public String toString() {
        return "ResConnectTestModel{" +
                "ResponseStatus=" + ResponseStatus +
                ", data=" + Data +
                '}';
    }

    public ResData getData() {
        return Data;
    }

    public static class ResData{
        private int time;//时间戳
        private int Ver;//版本号
        private String Product;//系统名
        private String InstanceName;//实例名

        @NonNull
        @Override
        public String toString() {
            return "ResConnectTestModel{" +
                    "time=" + time +
                    ", Ver=" + Ver +
                    ", Product='" + Product + '\'' +
                    ", InstanceName='" + InstanceName + '\'' +
                    '}';
        }

        public int getTime() {
            return time;
        }

        public int getVer() {
            return Ver;
        }

        public String getProduct() {
            return Product;
        }

        public String getInstanceName() {
            return InstanceName;
        }
    }

}
