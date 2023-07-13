package com.dawn.ych;

import androidx.annotation.NonNull;

/**
 * 获取token返回的数据
 */
public class ResTokenModel extends BaseResModel{
    private ResData Data;

    @NonNull
    @Override
    public String toString() {
        return "ResTokenModel{" +
                "ResponseStatus=" + ResponseStatus +
                ", Data=" + Data +
                '}';
    }

    public ResData getData() {
        return Data;
    }

    public static class ResData{
        private String Token;//响应的认证码
        private String TerminalName;//终端名称
        private String UpdateUrl;//更新地址
        private String UpdateVer;//更新版本

        @NonNull
        @Override
        public String toString() {
            return "ResData{" +
                    "Token='" + Token + '\'' +
                    ", TerminalName='" + TerminalName + '\'' +
                    ", UpdateUrl='" + UpdateUrl + '\'' +
                    ", UpdateVer='" + UpdateVer + '\'' +
                    '}';
        }

        public String getToken() {
            return Token;
        }

        public String getTerminalName() {
            return TerminalName;
        }

        public String getUpdateUrl() {
            return UpdateUrl;
        }

        public String getUpdateVer() {
            return UpdateVer;
        }
    }
}
