package com.dawn.ych;

public class ReqDeviceInfoModel {
    private int TimeStamp;//时间戳,秒
    private String Sign;//校验内容
    private String DevNum;//设备编码
    private String MallCode;//门店编码
    private String MacID;//设备mac地址

    public void setTimeStamp(int timeStamp) {
        TimeStamp = timeStamp;
    }

    public void setSign(String sign) {
        Sign = sign;
    }

    public void setDevNum(String devNum) {
        DevNum = devNum;
    }

    public void setMallCode(String mallCode) {
        MallCode = mallCode;
    }

    public void setMacID(String macID) {
        MacID = macID;
    }
}
