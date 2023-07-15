package com.dawn.ych;

/**
 * 油菜花支付的回调
 */
interface OnPayYchListener {
    void onSuccess(BaseResModel resModel);//请求成功
    void onFail();//请求失败
}
