package com.dawn.ych;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dawn.http.http.entity.HttpConfig;
import com.dawn.http.http.net.HTTPCaller;

/**
 * 支付工厂类
 */
public class PayFactory {
    private Context mContext;
    //单例模式
    private static PayFactory instance = null;
    private PayFactory(Context context){
        this.mContext = context;
        mYCHUtil = new YCHUtil();
    }
    public static PayFactory getInstance(Context context){
        if(instance == null){
            synchronized (PayFactory.class){
                if(instance == null){
                    instance = new PayFactory(context);
                }
            }
        }
        return instance;
    }

    /**
     * 初始化，必须调用
     * @param deviceId 设备序列号
     * @param key 加密key
     * @param deviceType 设备类型
     * @param regCode 注册码
     */
    public PayFactory initValue(String deviceId, String key, String deviceType, String regCode){
        PayConstant.deviceId = deviceId;//设备序列号
        PayConstant.key = key;//加密key
        PayConstant.deviceType = deviceType;//设备类型
        PayConstant.regCode = regCode;//注册码
        return instance;
    }

    /**
     * 开启服务
     */
    public void startService(OnPayLoginListener listener){
        PayConstant.payLoginListener = listener;
        Intent intent = new Intent(mContext, PayYchService.class);
        mContext.startService(intent);
    }

    private static YCHUtil mYCHUtil;//工具类

    /**
     * 提交支付订单
     */
    public void submitOrder(String transId, float price, OnSubmitOrderListener listener){
        PayConstant.submitOrderListener = listener;
        mYCHUtil.netSubmitOrder(transId, price, new OnSubmitOrderListener() {
            @Override
            public void onSubmitOrderSuccess(String qrCode, String orderId) {
                sendReceiverOrderResult(orderId);
                if(listener != null)
                    listener.onSubmitOrderSuccess(qrCode, orderId);
            }

            @Override
            public void onSubmitOrderFail() {
                if(listener != null)
                    listener.onSubmitOrderFail();
            }

            @Override
            public void onSubmitOrderResult(String orderId, boolean result) {
                if(listener != null)
                    listener.onSubmitOrderResult(orderId, result);
            }

        });
    }

    /**
     * 取消订单
     * @param orderId 订单号
     */
    public void cancelOrder(String orderId){
        mYCHUtil.netReturnPay(orderId, new OnPayYchListener() {
            @Override
            public void onSuccess(BaseResModel resModel) {
                Log.e("PayYchFactory", "取消订单成功");
            }

            @Override
            public void onFail() {
                Log.e("PayYchFactory", "取消订单失败");
            }
        });
    }

    /**
     * 发送广播查询支付结果
     * @param orderId 订单号
     */
    private void sendReceiverOrderResult(String orderId){
        Intent intent = new Intent(PayConstant.RECEIVER_PAY);
        intent.putExtra("command", "get_order_result");
        intent.putExtra("order_id", orderId);
        mContext.sendBroadcast(intent);
    }

    /**
     * 接口测试
     * @param deviceId 设备序列号
     */
    public void netTest(String key, String deviceId){
        try {
//            new YCHUtil().netRegister(key, "a1b78d53233244fabd13550cab76f69b053F");
//            new YCHUtil().netRegister(key, deviceId);
//            new YCHUtil().netDeviceInfo(key, deviceId);
//            new YCHUtil().netGetToken(key, "0181ccf2c2304899aaf34022324d99fd", deviceId, "101135317");
//            new YCHUtil().netConnectTest();
//            new YCHUtil().netTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化http
     */
    public void initHttp(boolean debug){
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setAgent(true);//有代理的情况能不能访问
        httpConfig.setDebug(debug);//是否debug模式 如果是debug模式打印log
        httpConfig.setTagName("dawn");//打印log的tagname

        //可以添加一些公共字段 每个接口都会带上
//        httpConfig.addCommonField("pf", "android");
//        httpConfig.addCommonField("version_code", String.valueOf(LAppUtil.getVersionCode(mContext.getApplicationContext())));

//        httpConfig.setAUTH_UM("dawn");
//        httpConfig.setAUTH_PW("dawn");

        //初始化HTTPCaller类
        HTTPCaller.getInstance().setHttpConfig(httpConfig);

    }
}
