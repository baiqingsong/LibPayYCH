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

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 油菜花的相关工具类
 */
public class YCHUtil {
    /**
     * 注册请求
     * @param deviceId 设备id
     */
    public void netRegister(String key, String deviceId){
        Header[] header = new Header[1];
        ReqRegisterModel bodyModel = new ReqRegisterModel();
        bodyModel.setOSVer("");
        bodyModel.setMainBoardNum("");
        bodyModel.setMemery(0);
        bodyModel.setDisk(0);
        bodyModel.setOSName("");
        bodyModel.setOSVerName("");
        bodyModel.setRegCode("7NcuKJS63ki12NVMUBx66w0");
        bodyModel.setDeviceType("0x0001000c");
        bodyModel.setMacID(getMacId(deviceId, key));
        bodyModel.setTimeSpan(System.currentTimeMillis() +"");
        bodyModel.setUnionDevice(false);
        bodyModel.setUnion(1);
        bodyModel.setAppInfo(Collections.singletonList(getAppVerInfo()));
        String bodyJson = new GsonBuilder().create().toJson(bodyModel);
        String authorization = SignatureUtils.getSignature(bodyJson, key);
        header[0] = new Header("Authorization", authorization);
//        Log.i("dawn", "bodyJson " + bodyJson);
//        Log.i("dawn", "authorization " + authorization);
        HTTPCaller.getInstance().post(ResRegisterModel.class, PayConstant.register_url, header, bodyJson, new RequestDataCallback<ResRegisterModel>(){
            @Override
            public void dataCallback(ResRegisterModel obj) {
                super.dataCallback(obj);
                Log.e("dawn", "register " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {
                    try{
                        ResRegisterModel.ResData data = obj.getData();
//                        Log.i("dawn", "register " + data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

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
     * 获取token
     */
    public void netGetToken(String key, String newKey, String deviceId, String deviceNum){
        ReqLoginModel bodyModel = new ReqLoginModel();
//        bodyModel.setAppID("照片机");
//        bodyModel.setAppVer(1);
        bodyModel.setDevNum(deviceNum);
//        bodyModel.setHardVer(1);
        bodyModel.setMacID(getMacId(deviceId, key));
//        bodyModel.setSign("");
//        bodyModel.setTS(System.currentTimeMillis() +"");
        String bodyJson = new GsonBuilder().create().toJson(bodyModel);
        String changeJson = "devnum=" + bodyModel.getDevNum() + "&macid=" + bodyModel.getMacID();

        String authorization = SignatureUtils.getSignature(changeJson, newKey);
        Header[] header = new Header[1];
        header[0] = new Header("Authorization", authorization);
        Log.i("dawn", "bodyJson " + bodyJson);
        Log.i("dawn", "changeJson " + changeJson);
        Log.i("dawn", "authorization " + authorization);
        HTTPCaller.getInstance().post(ResTokenModel.class, PayConstant.get_token_url, header, bodyJson, new RequestDataCallback<ResTokenModel>(){
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
    public void netDeviceInfo(String key, String deviceId){
        Header[] header = new Header[1];
        ReqDeviceInfoModel bodyModel = new ReqDeviceInfoModel();
        bodyModel.setTimeStamp((int)(System.currentTimeMillis()/1000));
        bodyModel.setMacID(getMacId(deviceId, key));
        String bodyJson = new GsonBuilder().create().toJson(bodyModel);
        String authorization = SignatureUtils.getSignature(bodyJson, key);
        header[0] = new Header("Authorization", authorization);
        Log.i("dawn", "bodyJson " + bodyJson);
        Log.i("dawn", "authorization " + authorization);
        HTTPCaller.getInstance().post(ResDeviceInfoModel.class, PayConstant.device_info_url, header, bodyJson, new RequestDataCallback<ResDeviceInfoModel>(){
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
     * 取消支付的请求
     */
    public void netReturnPay(String token, String orderId){
        Header[] header = new Header[1];
        header[0] = new Header("Authorization", token);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("OrderID", orderId);//订单id

        HTTPCaller.getInstance().post(BaseResModel.class, PayConstant.cancel_pay_url, header, paramsMap, new RequestDataCallback<BaseResModel>(){
            @Override
            public void dataCallback(BaseResModel obj) {
                super.dataCallback(obj);
                Log.e("dawn", "return pay " + obj);
                if (obj != null && obj.getResponseStatus() != null && "0".equals(obj.getResponseStatus().getErrorCode())) {


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
        reqAppVerInfo.setAppName("apk");
        reqAppVerInfo.setHardVer(21);
        reqAppVerInfo.setSoftVer(1);
        reqAppVerInfo.setDownUrl("");
        reqAppVerInfo.setAction("");
        reqAppVerInfo.setAppTitle("apk");
        reqAppVerInfo.setDeviceType("APK");
//        reqAppVerInfo.setSlMID("898604");
//        reqAppVerInfo.setSoftVer(9);
//        reqAppVerInfo.setDeviceType("Android");
//        reqAppVerInfo.setAppTitle("芸苔兑币机V10");
//        reqAppVerInfo.setHardVer(9);
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

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";
    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return 返回被加密后的字符串
     * @throws Exception
     */
    public static String HmacSHA1Encrypt(String encryptKey, String encryptText) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        // 完成 Mac 操作
        byte[] digest = mac.doFinal(text);
        StringBuilder sBuilder = bytesToHexString(digest);
        return sBuilder.toString();
    }
    /**
     * 转换成Hex
     *
     * @param bytesArray 字节数组
     */
    public static StringBuilder bytesToHexString(byte[] bytesArray) {
        if (bytesArray == null) {
            return null;
        }
        StringBuilder sBuilder = new StringBuilder();
        for (byte b : bytesArray) {
            String hv = String.format("%02x", b);
            sBuilder.append(hv);
        }
        return sBuilder;
    }

    public String getMacId(String macId, String key){
        String signatureTemp = SignatureUtils.getSignature(macId, key);
        return macId + signatureTemp.substring(0, 4);
    }

}
