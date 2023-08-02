package com.dawn.libpayych;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.dawn.qr_code.LQrCodeUtil;

public class BindActivity extends Activity {
    private ImageView ivQrCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        initView();
        addListener();
    }

    private void initView(){
        ivQrCode = findViewById(R.id.iv_qr_code);
    }
    private void addListener(){
        if(ivQrCode != null)
            ivQrCode.setImageBitmap(LQrCodeUtil.createQRCodeBitmap(Constants.bindCode, 400, 400));
    }
}
