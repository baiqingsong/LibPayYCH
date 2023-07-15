package com.dawn.ych;

import androidx.annotation.NonNull;

import java.util.List;

class ReqRegisterModel {
    private String OSVer;//操作系统版本，安卓版本
    private String MainBoardNum;//主板序列号
    private int Memery;//内存大小
    private int Disk;//硬盘大小
    private String OSName;//操作系统名称
    private String OSVerName;//操作系统版本名称
    private String RegCode;//连接网络的ssid
    private String DeviceType;//设备类型
    private String MacID;//mac地址
    private String TimeSpan;//时间戳
    private boolean IsUnionDevice;//是否是分账设备
    private List<ReqAppVerInfo> AppInfo;//应用信息
    private int Union;//设置1

    @NonNull
    @Override
    public String toString() {
        return "ReqRegisterModel{" +
                "OSVer='" + OSVer + '\'' +
                ", MainBoardNum='" + MainBoardNum + '\'' +
                ", Memery=" + Memery +
                ", Disk=" + Disk +
                ", OSName='" + OSName + '\'' +
                ", OSVerName='" + OSVerName + '\'' +
                ", RegCode='" + RegCode + '\'' +
                ", DeviceType='" + DeviceType + '\'' +
                ", MacID='" + MacID + '\'' +
                ", TimeSpan='" + TimeSpan + '\'' +
                ", IsUnionDevice=" + IsUnionDevice +
                ", AppInfo=" + AppInfo +
                ", Union=" + Union +
                '}';
    }

    public void setOSVer(String OSVer) {
        this.OSVer = OSVer;
    }

    public void setMainBoardNum(String mainBoardNum) {
        MainBoardNum = mainBoardNum;
    }

    public void setMemery(int memery) {
        Memery = memery;
    }

    public void setDisk(int disk) {
        Disk = disk;
    }

    public void setOSName(String OSName) {
        this.OSName = OSName;
    }

    public void setOSVerName(String OSVerName) {
        this.OSVerName = OSVerName;
    }

    public void setRegCode(String regCode) {
        RegCode = regCode;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public void setMacID(String macID) {
        MacID = macID;
    }

    public void setTimeSpan(String timeSpan) {
        TimeSpan = timeSpan;
    }

    public void setUnionDevice(boolean unionDevice) {
        IsUnionDevice = unionDevice;
    }

    public void setAppInfo(List<ReqAppVerInfo> appInfo) {
        AppInfo = appInfo;
    }

    public void setUnion(int union) {
        Union = union;
    }
}
