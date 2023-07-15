package com.dawn.ych;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dawn.http.http.entity.HttpConfig;
import com.dawn.http.http.net.HTTPCaller;

/**
 * 支付工厂类
 */
public class PayYchFactory {
    private Context mContext;
    //单例模式
    private static PayYchFactory instance = null;
    private PayYchFactory(Context context){
        this.mContext = context;
        mYCHUtil = new YCHUtil();
    }
    public static PayYchFactory getInstance(Context context){
        if(instance == null){
            synchronized (PayYchFactory.class){
                if(instance == null){
                    instance = new PayYchFactory(context);
                }
            }
        }
        return instance;
    }

    /**
     * 初始化，必须调用
     * @param deviceId 设备序列号
     * @param key 加密key
     */
    public PayYchFactory initValue(String deviceId, String key){
        PayConstant.deviceId = deviceId;//设备序列号
        PayConstant.key = key;//加密key
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

    private YCHUtil mYCHUtil;//工具类

    /**
     * 提交支付订单
     */
    public void submitOrder(String transId, int price, OnSubmitOrderListener listener){
        mYCHUtil.netSubmitOrder(transId, price, listener);
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
    public void initHttp(){
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.setAgent(true);//有代理的情况能不能访问
        httpConfig.setDebug(true);//是否debug模式 如果是debug模式打印log
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
