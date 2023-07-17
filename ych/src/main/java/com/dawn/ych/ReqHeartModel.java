package com.dawn.ych;

public class ReqHeartModel {
    private int NetDelay;//上次心跳延迟时间
    private int WifiStrength;//上次心跳wifi信号强度
    private String APMac;//连接的AP的BSSID
    private int MqOnline;//0:离线 1:在线

    public void setNetDelay(int netDelay) {
        NetDelay = netDelay;
    }

    public void setWifiStrength(int wifiStrength) {
        WifiStrength = wifiStrength;
    }

    public void setAPMac(String APMac) {
        this.APMac = APMac;
    }

    public void setMqOnline(int mqOnline) {
        MqOnline = mqOnline;
    }
}
