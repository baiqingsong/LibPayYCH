package com.dawn.ych;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

public class PayYchService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver();
        Log.i("dawn", "pay ych service on create");
        mYCHUtil = new YCHUtil();
        sendRegisterCommand();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mReceiver != null)
            unregisterReceiver(mReceiver);
    }

    private PayReceiver mReceiver;

    /**
     * 注册广播
     */
    private void registerReceiver() {
        mReceiver = new PayReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PayConstant.RECEIVER_PAY);
        registerReceiver(mReceiver, intentFilter);
    }

    private YCHUtil mYCHUtil;//工具类
    private final static int h_register_fail = 0x101;//注册失败
    private final static int h_device_info_fail = 0x102;//获取设备信息失败
    private final static int h_connect_test_fail = 0x103;//连接测试失败
    private final static int h_login_fail = 0x104;//登录失败
    private final static int h_heart_cycle = 0x110;//心跳周期
    private final static int h_heart_fail = 0x105;//多次心跳失败
    private final static int h_cycle_order_result = 0x106;//轮询订单结果
    private final static int h_order_result_cancel = 0x107;//订单取消
    private final static int h_device_info_cycle = 0x108;//循环查询设备信息
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case h_register_fail:
                    sendRegisterCommand();
                    break;
                case h_device_info_fail:
                    sendDeviceInfoCommand(false);
                    break;
                case h_connect_test_fail:
                    sendConnectTestCommand();
                    break;
                case h_login_fail:
                    sendLoginCommand();
                    break;
                case h_heart_cycle:
                    sendHeartCycleCommand();
                    break;
                case h_heart_fail://多次心跳失败
                    mHandler.removeMessages(h_heart_cycle);
                    sendRegisterCommand();//重新注册
                    break;
                case h_cycle_order_result://轮询订单结果
                    String orderId = (String) msg.obj;
                    sendOrderResultCommand(orderId);
                    break;
                case h_order_result_cancel://订单取消
                    mHandler.removeMessages(h_cycle_order_result);
                    break;
                case h_device_info_cycle://循环查询设备信息
                    sendDeviceInfoCommand(true);
                    break;
            }
        }
    };

    /**
     * 发送注册指令
     */
    private void sendRegisterCommand(){
        mYCHUtil.netRegister(new OnPayYchListener() {
            @Override
            public void onSuccess(BaseResModel resModel) {
                if(resModel == null)
                    return;
                if(resModel instanceof ResRegisterModel){
                    try{
                        ResRegisterModel resRegisterModel = (ResRegisterModel) resModel;
                        if(resRegisterModel.getData() == null)
                            return;

                        PayConstant.deviceSn = resRegisterModel.getData().getDeviceName();
                        sendDeviceInfoCommand(false);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFail() {
                mHandler.sendEmptyMessageDelayed(h_register_fail, 1000 * 30);//30秒后重新注册
            }
        });
    }

    /**
     * 发送设备信息指令
     */
    private void sendDeviceInfoCommand(boolean cycleBind){
        mYCHUtil.netDeviceInfo(new OnPayYchListener() {
            @Override
            public void onSuccess(BaseResModel resModel) {
                if(resModel == null)
                    return;
                if(resModel instanceof ResDeviceInfoModel){
                    try{
                        ResDeviceInfoModel resDeviceInfoModel = (ResDeviceInfoModel) resModel;
                        if(resDeviceInfoModel.getData() == null)
                            return;
                        if(cycleBind){//是否查询绑定状态

                            PayConstant.isBind = resDeviceInfoModel.getData().isHasBinding();
                            if(PayConstant.isBind){
                                if(PayConstant.payLoginListener != null)
                                    PayConstant.payLoginListener.onPayBindSuccess();
                                sendConnectTestCommand();
                            }else{
                                mHandler.sendEmptyMessageDelayed(h_device_info_cycle, 5000);//5秒后重新获取设备信息
                            }
                        }else{
                            PayConstant.base_new_url = resDeviceInfoModel.getData().getBizServer();
                            PayConstant.new_key = resDeviceInfoModel.getData().getAppKey();
                            PayConstant.isBind = resDeviceInfoModel.getData().isHasBinding();
                            if(PayConstant.isBind){
                                sendConnectTestCommand();
                            }else{
                                if(PayConstant.payLoginListener != null)
                                    PayConstant.payLoginListener.onPayBindCode(PayConstant.deviceSn);
                                mHandler.sendEmptyMessageDelayed(h_device_info_cycle, 5000);//5秒后重新获取设备信息
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFail() {
                mHandler.sendEmptyMessageDelayed(h_device_info_fail, 1000 * 30);//30秒后重新获取设备信息
            }
        });
    }

    /**
     * 发送连接测试指令
     */
    private void sendConnectTestCommand(){
        mYCHUtil.netConnectTest(new OnPayYchListener() {
            @Override
            public void onSuccess(BaseResModel resModel) {
                sendLoginCommand();
            }

            @Override
            public void onFail() {
                mHandler.sendEmptyMessageDelayed(h_connect_test_fail, 1000 * 30);//30秒后重新获取连接测试
            }
        });
    }

    /**
     * 发送登录指令
     */
    private void sendLoginCommand(){
        mYCHUtil.netGetToken(new OnPayYchListener() {
            @Override
            public void onSuccess(BaseResModel resModel) {
                if(resModel == null)
                    return;
                if(resModel instanceof ResTokenModel){
                    try{
                        ResTokenModel resTokenModel = (ResTokenModel) resModel;
                        if(resTokenModel.getData() == null)
                            return;
                        PayConstant.token = resTokenModel.getData().getToken();
                        if(PayConstant.payLoginListener != null)
                            PayConstant.payLoginListener.onPayLoginStatus(true);
                        sendHeartCycleCommand();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFail() {
                mHandler.sendEmptyMessageDelayed(h_login_fail, 1000 * 30);//30秒后重新登录
            }
        });
    }

    /**
     * 发送心跳指令
     */
    private void sendHeartCycleCommand(){
        mYCHUtil.netHeart(new OnPayYchListener() {
            @Override
            public void onSuccess(BaseResModel resModel) {
                if(resModel == null)
                    return;
                mHandler.removeMessages(h_heart_cycle);
                mHandler.sendEmptyMessageDelayed(h_heart_cycle, 1000 * 60);//1分钟后重新发送心跳
            }

            @Override
            public void onFail() {
                mHandler.removeMessages(h_heart_cycle);
                mHandler.sendEmptyMessageDelayed(h_heart_cycle, 1000 * 30);//30秒后重新发送心跳
            }
        });
    }

    /**
     * 发送订单结果指令
     */
    private void sendOrderResultCommand(String orderId){
        mYCHUtil.netGetPayResult(orderId, new OnPayYchListener() {
            @Override
            public void onSuccess(BaseResModel resModel) {
                if(resModel == null)
                    return;
                try{
                    ResPayResultModel resPayResultModel = (ResPayResultModel) resModel;
                    if(PayConstant.pay_result_paying.equals(resPayResultModel.getData().getStatus())){//支付状态为支付中
                        mHandler.removeMessages(h_cycle_order_result);
                        Message msg = new Message();
                        msg.what = h_cycle_order_result;
                        msg.obj = orderId;
                        mHandler.sendMessageDelayed(msg, 1000);//1秒后重新发送订单结果
                    }else if(PayConstant.pay_result_complete.equals(resPayResultModel.getData().getStatus())) {//支付状态为支付成功
                        mHandler.removeMessages(h_cycle_order_result);
                        if(PayConstant.submitOrderListener != null)
                            PayConstant.submitOrderListener.onSubmitOrderResult(orderId, true);
                    }
                    Log.e("dawn", "订单号：" + orderId + "订单结果：" + resPayResultModel);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail() {
                mHandler.removeMessages(h_cycle_order_result);
                Message msg = new Message();
                msg.what = h_cycle_order_result;
                msg.obj = orderId;
                mHandler.sendMessageDelayed(msg, 1000);//30秒后重新发送订单结果
            }
        });
    }


    public class PayReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent == null)
                return;
            String command = intent.getStringExtra("command");
            if(TextUtils.isEmpty(command))
                return;
            if("get_order_result".equals(command)){//循环查询结果
                String orderId = intent.getStringExtra("order_id");
                Message msg = new Message();
                msg.what = h_cycle_order_result;
                msg.obj = orderId;
                mHandler.removeMessages(h_cycle_order_result);
                mHandler.sendMessageDelayed(msg, 1000);//1秒后发送订单结果
                mHandler.removeMessages(h_order_result_cancel);
                mHandler.sendEmptyMessageDelayed(h_order_result_cancel, 90 * 1000);//90秒后取消订单结果查询
            }
        }
    }
}
