package com.dawn.ych;

import android.util.Log;

import com.dawn.http.http.net.HTTPCaller;
import com.dawn.http.http.net.Header;
import com.dawn.http.http.net.RequestDataCallback;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 油菜花的相关工具类
 */
public class YCHUtil {
    /**
     * 注册请求
     */
    public void netRegister(OnPayYchListener listener){
        Header[] header = new Header[1];
        ReqRegisterModel bodyModel = getRegisterInfo();
        String bodyJson = new GsonBuilder().create().toJson(bodyModel);
        String authorization = SignatureUtils.getSignature(bodyJson, PayConstant.key);
        header[0] = new Header("Authorization", authorization);
//        Log.i("dawn", "bodyJson " + bodyJson);
//        Log.i("dawn", "authorization " + authorization);
        HTTPCaller.getInstance().post(ResRegisterModel.class, PayConstant.register_url, header, bodyJson, new RequestDataCallback<ResRegisterModel>(){
            @Override
            public void dataCallback(ResRegisterModel obj) {
                super.dataCallback(obj);
//                Log.e("dawn", "register " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
//                        ResRegisterModel.ResData data = obj.getData();
//                        Log.i("dawn", "register " + data);
                        if(listener != null)
                            listener.onSuccess(obj);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    Log.e("dawn", "register fail " + obj);
                    if(listener != null)
                        listener.onFail();
                }
            }
        });
    }

    /**
     * 设备信息请求
     */
    public void netDeviceInfo(OnPayYchListener listener){
        Header[] header = new Header[1];
        ReqDeviceInfoModel bodyModel = getDeviceInfo();
        String bodyJson = new GsonBuilder().create().toJson(bodyModel);
        String authorization = SignatureUtils.getSignature(bodyJson, PayConstant.key);
        header[0] = new Header("Authorization", authorization);
//        Log.i("dawn", "bodyJson " + bodyJson);
//        Log.i("dawn", "authorization " + authorization);
        HTTPCaller.getInstance().post(ResDeviceInfoModel.class, PayConstant.device_info_url, header, bodyJson, new RequestDataCallback<ResDeviceInfoModel>(){
            @Override
            public void dataCallback(ResDeviceInfoModel obj) {
                super.dataCallback(obj);
//                Log.e("dawn", "device info " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
//                        ResDeviceInfoModel.ResData data = obj.getData();
//                        Log.i("dawn", "device info " + data);
                        if(listener != null)
                            listener.onSuccess(obj);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    Log.e("dawn", "device info fail " + obj);
                    if(listener != null)
                        listener.onFail();
                }
            }
        });
    }

    /**
     * 网络连接测试
     */
    public void netConnectTest(OnPayYchListener listener){
        HTTPCaller.getInstance().get(ResConnectTestModel.class, PayConstant.base_new_url + PayConstant.connect_test_url, null, new RequestDataCallback<ResConnectTestModel>(){
            @Override
            public void dataCallback(ResConnectTestModel obj) {
                super.dataCallback(obj);
//                Log.e("dawn", "connect test " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
//                        ResConnectTestModel.ResData data = obj.getData();
//                        Log.i("dawn", "connect test " + data);
                        if(listener != null)
                            listener.onSuccess(obj);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Log.e("dawn", "connect test fail " + obj);
                    if(listener != null)
                        listener.onFail();
                }
            }
        });
    }

    /**
     * 获取token
     */
    public void netGetToken(OnPayYchListener listener){
        ReqLoginModel bodyModel = getLogin();
        String bodyJson = new GsonBuilder().create().toJson(bodyModel);
        String changeJson = "devnum=" + bodyModel.getDevNum() + "&macid=" + bodyModel.getMacID();

        String authorization = SignatureUtils.getSignature(changeJson, PayConstant.new_key);
        Header[] header = new Header[1];
        header[0] = new Header("Authorization", authorization);
//        Log.i("dawn", "bodyJson " + bodyJson);
//        Log.i("dawn", "changeJson " + changeJson);
//        Log.i("dawn", "authorization " + authorization);
        HTTPCaller.getInstance().post(ResTokenModel.class, PayConstant.base_new_url + PayConstant.get_token_url, header, bodyJson, new RequestDataCallback<ResTokenModel>(){
            @Override
            public void dataCallback(ResTokenModel obj) {
                super.dataCallback(obj);
//                Log.e("dawn", "get token " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
//                        ResTokenModel.ResData data = obj.getData();
//                        Log.i("dawn", "get token " + data);
                        if(listener != null)
                            listener.onSuccess(obj);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Log.e("dawn", "get token fail " + obj);
                    if(listener != null)
                        listener.onFail();
                }
            }
        });

    }

    /**
     * 生成订单
     */
    public void netSubmitOrder(String transId, int price, OnSubmitOrderListener listener){
        Header[] header = new Header[1];
        header[0] = new Header("Authorization", PayConstant.token);
        ReqOrderModel bodyModel = getOrder(transId, price);
        String bodyJson = new GsonBuilder().create().toJson(bodyModel);

        HTTPCaller.getInstance().post(ResGoodsModel.class, PayConstant.base_new_url + PayConstant.submit_order_url, header, bodyJson, new RequestDataCallback<ResGoodsModel>(){
            @Override
            public void dataCallback(ResGoodsModel obj) {
                super.dataCallback(obj);
//                Log.e("dawn", "submit order " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
                        ResGoodsModel.ResData data = obj.getData();
                        Log.i("dawn", "submit order " + data);
                        if(listener != null)
                            listener.onSubmitOrderSuccess(data.getQRCode(), data.getOrderID());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    Log.e("dawn", "submit order fail " + obj);
                    if(listener != null)
                        listener.onSubmitOrderFail();
                }
            }
        });

    }

    /**
     * 取消支付的请求
     */
    public void netReturnPay(String orderId, OnPayYchListener listener){
        Header[] header = new Header[1];
        header[0] = new Header("Authorization", PayConstant.token);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("OrderID", orderId);//订单id

        HTTPCaller.getInstance().post(BaseResModel.class, PayConstant.base_new_url + PayConstant.cancel_pay_url, header, paramsMap, new RequestDataCallback<BaseResModel>(){
            @Override
            public void dataCallback(BaseResModel obj) {
                super.dataCallback(obj);
//                Log.e("dawn", "return pay " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try {
                        Log.i("dawn", "return pay " + obj);
                        if(listener != null)
                            listener.onSuccess(obj);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Log.e("dawn", "return pay fail " + obj);
                    if(listener != null)
                        listener.onFail();
                }
            }
        });
    }

    /**
     * 获取支付结果
     * @param orderId 订单id
     * @param listener 回调
     */
    public void netGetPayResult(String orderId, OnPayYchListener listener){
        Header[] header = new Header[1];
        header[0] = new Header("Authorization", PayConstant.token);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("OrderID", orderId);//订单id

        HTTPCaller.getInstance().post(BaseResModel.class, PayConstant.base_new_url + PayConstant.order_result_url, header, paramsMap, new RequestDataCallback<BaseResModel>(){
            @Override
            public void dataCallback(BaseResModel obj) {
                super.dataCallback(obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try {
                        Log.i("dawn", "pay result " + obj);
                        if(listener != null)
                            listener.onSuccess(obj);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Log.e("dawn", "pay result fail " + obj);
                    if(listener != null)
                        listener.onFail();
                }
            }
        });
    }

    /**
     * 心跳
     */
    public void netHeart(OnPayYchListener listener){
        Header[] header = new Header[1];
        header[0] = new Header("Authorization", PayConstant.token);
        ReqHeartModel bodyModel = getHeart();
        String bodyJson = new GsonBuilder().create().toJson(bodyModel);

        HTTPCaller.getInstance().post(BaseResModel.class, PayConstant.base_new_url + PayConstant.get_heart_url, header, bodyJson, new RequestDataCallback<BaseResModel>() {
            @Override
            public void dataCallback(BaseResModel obj) {
                super.dataCallback(obj);
                if(obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    Log.i("dawn", "heart success");
                    try{
                        if(listener != null)
                            listener.onSuccess(obj);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Log.e("dawn", "heart fail " + obj);
                    if(listener != null)
                        listener.onFail();
                }
            }
        });
    }

    public void netTest(){
        String jsonStr = "{\"AppInfo\":[{\"sIMID\":\"898604\",\"softVer\":9,\"deviceType\":\"Android\",\"appTitle\":\"芸苔兑币机V10\",\"hardVer\":9}],\"DeviceType\":\"AndrSdk_Test\",\"Disk\":10894,\"MacID\":\"a1b78d53233244fabd13550cab76f69b053F\",\"MainBoardNum\":\"a1b78d53233244fabd13550cab76f69b\",\"Memery\":524288,\"OSName\":\"Android\",\"OSVer\":25,\"OSVerName\":\"7.1.2\",\"RegCode\":\"odIsLCTgMEmrfGIkl24mwt\",\"TimeSpan\":1689306973125}";
        Header[] header = new Header[2];
        header[0] = new Header("Authorization", "FB890E9D09DC897FDFEF14A62F7B941AE07CA75C");
        header[1] = new Header("sss", "application/json");
        HTTPCaller.getInstance().post(ResRegisterModel.class, "https://02w.cn/api/test666", header, jsonStr, new RequestDataCallback<ResRegisterModel>(){
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
     * 获取注册信息
     */
    private ReqRegisterModel getRegisterInfo(){
        //获取注册信息
        ReqRegisterModel bodyModel = new ReqRegisterModel();
        bodyModel.setOSVer("7.1.2");
        bodyModel.setMainBoardNum(PayConstant.deviceId);
        bodyModel.setMemery(1024);
        bodyModel.setDisk(1024);
        bodyModel.setOSName("Android");
        bodyModel.setOSVerName("Java");
        bodyModel.setDeviceType("0x0001000c");
        bodyModel.setMacID(getMacId(PayConstant.deviceId, PayConstant.key));
        bodyModel.setRegCode("7NcuKJS63ki12NVMUBx66w");
        bodyModel.setTimeSpan(System.currentTimeMillis() +"");
        bodyModel.setUnionDevice(false);
        bodyModel.setUnion(1);
        bodyModel.setAppInfo(Collections.singletonList(getAppVerInfo()));
        return bodyModel;
    }

    /**
     * 获取app版本信息
     */
    private ReqAppVerInfo getAppVerInfo(){
        ReqAppVerInfo reqAppVerInfo = new ReqAppVerInfo();
        reqAppVerInfo.setAppName("camera");
        reqAppVerInfo.setHardVer(21);
        reqAppVerInfo.setSoftVer(1);
        reqAppVerInfo.setDownUrl("");
        reqAppVerInfo.setAction("");
        reqAppVerInfo.setAppTitle("apk");
        reqAppVerInfo.setDeviceType("APK");
        return reqAppVerInfo;
    }

    /**
     * 获取设备信息
     */
    private ReqDeviceInfoModel getDeviceInfo(){
        ReqDeviceInfoModel bodyModel = new ReqDeviceInfoModel();
        bodyModel.setTimeStamp((int)(System.currentTimeMillis()/1000));
        bodyModel.setMacID(getMacId(PayConstant.deviceId, PayConstant.key));
        return bodyModel;
    }

    /**
     * 获取登录信息
     */
    private ReqLoginModel getLogin(){
        ReqLoginModel bodyModel = new ReqLoginModel();
//        bodyModel.setAppID("camera");
//        bodyModel.setAppVer(1);
        bodyModel.setDevNum(PayConstant.deviceSn);
//        bodyModel.setHardVer(1);
        bodyModel.setMacID(getMacId(PayConstant.deviceId, PayConstant.key));
//        bodyModel.setSign("");
//        bodyModel.setTS(System.currentTimeMillis() +"");
        return bodyModel;
    }

    /**
     * 获取订单信息
     */
    private ReqOrderModel getOrder(String transId, int price){
        ReqOrderModel bodyModel = new ReqOrderModel();
        bodyModel.setTransID(transId);
//        bodyModel.setDeviceId(getMacId(PayConstant.deviceId, PayConstant.key));
        List<ReqGoodsModel> goodsList = new ArrayList<>();
        goodsList.add(getGoods(price));
        bodyModel.setGoodsItem(goodsList);
        return bodyModel;
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

    /**
     * 获取心跳信息
     */
    private ReqHeartModel getHeart(){
        ReqHeartModel bodyModel = new ReqHeartModel();
        bodyModel.setNetDelay(0);
        bodyModel.setWifiStrength(100);
        bodyModel.setAPMac("camera");
        bodyModel.setMqOnline(1);
        return bodyModel;
    }

    /**
     * 获取设备id
     * @param macId 设备id
     * @param key 加密key
     */
    public String getMacId(String macId, String key){
        String signatureTemp = SignatureUtils.getSignature(macId, key);
        return macId + signatureTemp.substring(0, 4);
    }

}
