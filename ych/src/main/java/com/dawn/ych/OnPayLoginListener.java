package com.dawn.ych;

/**
 * 支付登录的监听
 */
public interface OnPayLoginListener {
    void onPayLoginStatus(boolean status);
    void onPayBindCode(String bindCode);//获取绑定二维码
    void onPayBindSuccess();//绑定成功
}
