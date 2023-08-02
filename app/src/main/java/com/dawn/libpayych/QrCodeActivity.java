package com.dawn.libpayych;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.dawn.qr_code.LQrCodeUtil;
import com.dawn.ych.OnSubmitOrderListener;
import com.dawn.ych.PayFactory;

import java.util.UUID;

/**
 * 支付二维码页面
 */
public class QrCodeActivity extends Activity {
    private ImageView ivQrCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        initView();
        addListener();
    }
    private void initView(){
        ivQrCode = findViewById(R.id.iv_qr_code);
    }
    private void addListener(){
        getYCHQrCode();
    }

    private void getYCHQrCode(){
        PayFactory.getInstance(this).submitOrder(UUID.randomUUID() + "", 0.1f, new OnSubmitOrderListener() {
            @Override
            public void onSubmitOrderSuccess(String qrCode, String orderId) {
                Log.i("dawn", "qr code : " + qrCode + " orderId : " + orderId);
                if(ivQrCode != null)
                    ivQrCode.setImageBitmap(LQrCodeUtil.createQRCodeBitmap(qrCode, 400, 400));
            }

            @Override
            public void onSubmitOrderFail() {
                Log.i("dawn", "onSubmitOrderFail");
            }

            @Override
            public void onSubmitOrderResult(String orderId, boolean result) {
                Log.i("dawn", "onSubmitOrderResult orderId : " + orderId + " result : " + result);
                runOnUiThread(() -> {
                    Toast.makeText(QrCodeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }


        });
    }
}
