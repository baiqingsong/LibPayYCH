package com.dawn.ych;

import com.google.gson.annotations.SerializedName;

class ReqLoginModel {
    @SerializedName("AppID")
    private String AppID;//应用id
    @SerializedName("DevNum")
    private String DevNum;//设备编码
    @SerializedName("MacID")
    private String MacID;//设备id
    @SerializedName("Sign")
    private String Sign;//签名
    @SerializedName("TS")
    private String TS;//时间戳

    public void setAppID(String appID) {
        AppID = appID;
    }

    public void setMacID(String macID) {
        MacID = macID;
    }

    public void setSign(String sign) {
        Sign = sign;
    }

    public void setTS(String TS) {
        this.TS = TS;
    }

    public void setDevNum(String devNum) {
        DevNum = devNum;
    }

    public String getAppID() {
        return AppID;
    }

    public String getDevNum() {
        return DevNum;
    }

    public String getMacID() {
        return MacID;
    }

    public String getSign() {
        return Sign;
    }

    public String getTS() {
        return TS;
    }
}
