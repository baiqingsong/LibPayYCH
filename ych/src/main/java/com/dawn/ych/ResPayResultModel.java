package com.dawn.ych;

import androidx.annotation.NonNull;

public class ResPayResultModel extends BaseResModel{
    private ResData Data;

    @NonNull
    @Override
    public String toString() {
        return "ResPayResultModel{" +
                "Data=" + Data +
                '}';
    }

    public ResData getData() {
        return Data;
    }

    public static class ResData{
        private String Status;//支付状态

        @Override
        public String toString() {
            return "ResData{" +
                    "Status='" + Status + '\'' +
                    '}';
        }

        public String getStatus() {
            return Status;
        }
    }
}
