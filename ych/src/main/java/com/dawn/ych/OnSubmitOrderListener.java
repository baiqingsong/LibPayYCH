package com.dawn.ych;

/**
 * 提交订单的监听
 */
public interface OnSubmitOrderListener {
    void onSubmitOrderSuccess(String qrCode, String orderId);
    void onSubmitOrderFail();
    void onSubmitOrderResult(String orderId, boolean result);
}
