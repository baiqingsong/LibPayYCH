package com.dawn.ych;

import androidx.annotation.NonNull;

public class ReqAppVerInfo {
    private String AppName;//包名
    private int HardVer;//硬件版本,sdk版本
    private int SoftVer;//软件版本
    private String DownUrl;//下载地址
    private String Action;//Boot=自动启动
    private String AppTitle;//应用名称
    private String DeviceType;//APK=安卓应用

    @NonNull
    @Override
    public String toString() {
        return "ReqAppVerInfo{" +
                "AppName='" + AppName + '\'' +
                ", HardVer=" + HardVer +
                ", SoftVer=" + SoftVer +
                ", DownUrl='" + DownUrl + '\'' +
                ", Action='" + Action + '\'' +
                ", AppTitle='" + AppTitle + '\'' +
                ", DeviceType='" + DeviceType + '\'' +
                '}';
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public void setHardVer(int hardVer) {
        HardVer = hardVer;
    }

    public void setSoftVer(int softVer) {
        SoftVer = softVer;
    }

    public void setDownUrl(String downUrl) {
        DownUrl = downUrl;
    }

    public void setAction(String action) {
        Action = action;
    }

    public void setAppTitle(String appTitle) {
        AppTitle = appTitle;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }
}
