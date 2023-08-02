# LibPayYCH
 油菜花支付的引用

## PayFactory
支付工厂类，用于创建支付对象
* getInstance 获取支付的实例
* initValue 初始化支付参数
  * deviceId 设备序列号
  * key 加密key
  * deviceType 设备类型
  * regCode 注册码
* startService 开启服务
* submitOrder 提交订单
* cancelOrder 取消订单


## OnPayLoginListener
支付登录监听器
* onPayLoginStatus 登录成功
* onPayBindCode 获取绑定二维码，用于绑定设备
* onPayBindSuccess 绑定成功

## OnSubmitOrderListener
支付监听器
* onSubmitOrderSuccess 获取支付二维码成功
* onSubmitOrderFail 获取支付二维码失败
* onSubmitOrderResult 支付结果
