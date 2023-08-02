package com.dawn.ych;

class PayConstant {
    public static final String RECEIVER_PAY = "receiver_pay_ych";//支付服务的广播
    public static final String base_url = "https://zh.rapa.vip";//服务器地址
//    public static final String base_url = "https://yzy.gzych.vip";//服务器地址
    public static final String register_url = base_url + "/YzyIot/api/v1/Device/RegistWithProduct";//注册地址
    public static final String device_info_url = base_url + "/YzyIot/api/v1/Device/QueryDevice";//设备信息地址
    public static final String connect_test_url = "/Hardware/api/v1.0/Pay/ConnectTest";//连接测试地址
    public static final String get_token_url = "/Hardware/api/v1.0/Public/LoginToken";//获取token地址
    public static final String submit_order_url = "/Hardware/api/v1/Order/SubmitOrder";//提交订单地址
    public static final String cancel_pay_url = "/Hardware/api/v1/Order/CancelPay";//取消支付地址
    public static final String order_result_url = "/Hardware/api/v1/Order/BuyResult";//订单结果地址
    public static final String get_heart_url = "/Hardware/api/v1.0/Public/Heart";//心跳地址
    public static String deviceId;//设备id
    public static String key;//加密key
    public static String token;//登录token
    public static String base_new_url;//新服务器地址
    public static String deviceSn;//设备序列号,绑定的设备编号,绑定的设备序列号
    public static boolean isBind;//是否绑定
    public static String new_key;//新加密key
    public static String deviceType;//设备类型
//    public static String deviceType = "0x0001000c";//设备类型
    public static String regCode;//注册码
//    public static String regCode = "7NcuKJS63ki12NVMUBx66w";//注册码

    public static OnPayLoginListener payLoginListener;//支付登录的监听
    public static OnSubmitOrderListener submitOrderListener;//提交订单的监听

    public static final String pay_result_cancel = "Canceled";//取消支付
    public static final String pay_result_complete = "Completed";//支付成功
    public static final String pay_result_paying = "Paying";//支付中
    public static final String pay_result_return = "Returned";//已退货
    public static final String pay_result_returning = "Returning";//退货中
    public static final String pay_result_unpaid = "Unpaid";//待支付

}
