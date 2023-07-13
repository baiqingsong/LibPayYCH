package com.dawn.ych;

import android.preference.PreferenceActivity;
import android.util.Log;

import com.dawn.http.http.net.HTTPCaller;
import com.dawn.http.http.net.Header;
import com.dawn.http.http.net.RequestDataCallback;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 油菜花的相关工具类
 */
public class YCHUtil {
    /**
     * 注册请求
     * @param deviceId 设备id
     */
    public void netRegister(String deviceId){
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("OSVer", "7.1.2");
        paramsMap.put("MainBoardNum", deviceId);
        paramsMap.put("Memery", "1024");
        paramsMap.put("Disk", "1024");
        paramsMap.put("OSName", "Android");
        paramsMap.put("OSVerName", "Android 7.1.2");
        paramsMap.put("RegCode", "7NcuKJS63ki12NVMUBx66w");
        paramsMap.put("DeviceType", "0x0001000c");
        paramsMap.put("MacID", "000000000000");
        paramsMap.put("TimeSpan", System.currentTimeMillis() +"");
        paramsMap.put("IsUnionDevice", false);
        List<ReqAppVerInfo> appVerInfo = new ArrayList<>();
        appVerInfo.add(getAppVerInfo());
        paramsMap.put("AppInfo", Collections.singletonList(appVerInfo));
        Log.i("dawn", " " + Collections.singletonList(appVerInfo));
        HTTPCaller.getInstance().post(ResRegisterModel.class, PayConstant.register_url, null, paramsMap, new RequestDataCallback<ResRegisterModel>(){
            @Override
            public void dataCallback(ResRegisterModel obj) {
                super.dataCallback(obj);
                Log.e("dawn", "register " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
                        ResRegisterModel.ResData data = obj.getData();
                        Log.i("dawn", "register " + data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    /**
     * 获取token
     */
    public void netGetToken(String deviceId){
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("AppID", "照片机");//应用名
        paramsMap.put("MacID", deviceId);//设备id
        paramsMap.put("Sign", "3A5B6BF5FAB891783A5B6CF4FAF79275");//签名
        paramsMap.put("AppVer", 1);//程序版本
        paramsMap.put("HardVer", 1);//硬件版本
        paramsMap.put("TS", System.currentTimeMillis() +"");//时间戳
        paramsMap.put("DevNum", "camera");//设备编号
        HTTPCaller.getInstance().post(ResTokenModel.class, PayConstant.get_token_url, null, paramsMap, new RequestDataCallback<ResTokenModel>(){
            @Override
            public void dataCallback(ResTokenModel obj) {
                super.dataCallback(obj);
                Log.e("dawn", "get token " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
                        ResTokenModel.ResData data = obj.getData();
                        Log.i("dawn", "get token " + data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    /**
     * 设备信息请求
     */
    public void netDeviceInfo(){
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("TimeStamp", System.currentTimeMillis()/1000);//时间戳
        paramsMap.put("Sign", "");//校验内容
        paramsMap.put("DevNum", "");//设备编号
        paramsMap.put("MallCode", "");//门店编码

        HTTPCaller.getInstance().post(ResDeviceInfoModel.class, PayConstant.device_info_url, null, paramsMap, new RequestDataCallback<ResDeviceInfoModel>(){
            @Override
            public void dataCallback(ResDeviceInfoModel obj) {
                super.dataCallback(obj);
                Log.e("dawn", "device info " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
                        ResDeviceInfoModel.ResData data = obj.getData();
                        Log.i("dawn", "device info " + data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    /**
     * 网络连接测试
     */
    public void netConnectTest(){

        HTTPCaller.getInstance().get(ResConnectTestModel.class, PayConstant.connect_test_url, null, new RequestDataCallback<ResConnectTestModel>(){
            @Override
            public void dataCallback(ResConnectTestModel obj) {
                super.dataCallback(obj);
                Log.e("dawn", "connect test " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
                        ResConnectTestModel.ResData data = obj.getData();
                        Log.i("dawn", "connect test " + data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    /**
     * 生成订单
     */
    public void netSubmitOrder(String token, String deviceId, String transId, int price){

        Header[] header = new Header[1];
        header[0] = new Header("Authorization", token);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("TransID", System.currentTimeMillis() + "");//流水
        paramsMap.put("DeviceID", deviceId);//设备id
        List<ReqGoodsModel> goodsList = new ArrayList<>();
        goodsList.add(getGoods(price));
        paramsMap.put("GoodsItem", goodsList);//商品信息

        HTTPCaller.getInstance().post(ResGoodsModel.class, PayConstant.submit_order_url, header, paramsMap, new RequestDataCallback<ResGoodsModel>(){
            @Override
            public void dataCallback(ResGoodsModel obj) {
                super.dataCallback(obj);
                Log.e("dawn", "submit order " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
                        ResGoodsModel.ResData data = obj.getData();
                        Log.i("dawn", "submit order " + data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    /**
     * 获取注册信息
     * @param deviceId 设备id
     */
    private ReqRegisterModel getRegisterInfo(String deviceId){
        //获取注册信息
        ReqRegisterModel reqRegisterModel = new ReqRegisterModel();
        reqRegisterModel.setOSVer("7.1.2");
        reqRegisterModel.setMainBoardNum(deviceId);
        reqRegisterModel.setMemery(1024);
        reqRegisterModel.setDisk(1024);
        reqRegisterModel.setOSName("Android");
        reqRegisterModel.setOSVerName("Android 7.1.2");
        reqRegisterModel.setDeviceType("0x0001000c");
        reqRegisterModel.setMacID("000000000000");
        reqRegisterModel.setRegCode("7NcuKJS63ki12NVMUBx66w");
        reqRegisterModel.setTimeSpan(System.currentTimeMillis() +"");
        reqRegisterModel.setUnionDevice(false);
        List<ReqAppVerInfo> appVerInfo = new ArrayList<>();
        appVerInfo.add(getAppVerInfo());
        reqRegisterModel.setAppInfo(appVerInfo);
        return reqRegisterModel;
    }

    /**
     * 获取app版本信息
     */
    private ReqAppVerInfo getAppVerInfo(){
        ReqAppVerInfo reqAppVerInfo = new ReqAppVerInfo();
        reqAppVerInfo.setAppName("com.dawn.ych");
        reqAppVerInfo.setHardVer(21);
        reqAppVerInfo.setSoftVer(1);
        reqAppVerInfo.setDownUrl("https://www.baidu.com/");
        reqAppVerInfo.setAction("Boot");
        reqAppVerInfo.setAppTitle("照片机");
        reqAppVerInfo.setDeviceType("APK");
        return reqAppVerInfo;
    }

    /**
     * 获取商品信息
     * @param price 价格
     */
    private ReqGoodsModel getGoods(int price){
        ReqGoodsModel reqGoodsModel = new ReqGoodsModel();
        reqGoodsModel.setPrice(price);
        reqGoodsModel.setAmount(1);
        reqGoodsModel.setGoodsName("照片");
        return reqGoodsModel;
    }

}
