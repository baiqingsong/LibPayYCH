package com.dawn.libpayych;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dawn.ych.OnPayLoginListener;
import com.dawn.ych.OnSubmitOrderListener;
import com.dawn.ych.PayYchFactory;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PayYchFactory.getInstance(this).initValue(SystemUtil.getDeviceId(), "3A5B6BF5FAB891783A5B6CF4FAF79275").startService(new OnPayLoginListener() {
            @Override
            public void onPayLoginStatus(boolean status) {
                if(status) {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show());
                }
            }
        });
//        PayYchFactory.getInstance(this).netTest("3A5B6BF5FAB891783A5B6CF4FAF79275", SystemUtil.getDeviceId());
    }

    public void paySubmitOrder(View view){
        PayYchFactory.getInstance(this).submitOrder(UUID.randomUUID() + "", 1, new OnSubmitOrderListener() {
            @Override
            public void onSubmitOrderSuccess(String qrCode, String orderId) {

            }

            @Override
            public void onSubmitOrderFail() {

            }

            @Override
            public void onSubmitOrderResult(boolean result) {

            }
        });
    }

}