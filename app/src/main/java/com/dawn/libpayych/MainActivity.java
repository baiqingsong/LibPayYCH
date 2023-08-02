package com.dawn.libpayych;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dawn.ych.OnPayLoginListener;
import com.dawn.ych.PayFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String deviceType = "0x0001000c";
        String regCode = "7NcuKJS63ki12NVMUBx66w";
        PayFactory.getInstance(this).initValue(SystemUtil.getDeviceId(), "3A5B6BF5FAB891783A5B6CF4FAF79275", deviceType, regCode)
                .startService(new OnPayLoginListener() {
                    @Override
                    public void onPayLoginStatus(boolean status) {
                        if(status) {
                            runOnUiThread(() -> Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show());
                        }else{
                            runOnUiThread(() -> Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show());
                        }
                    }

                    @Override
                    public void onPayBindCode(String bindCode) {
                        Constants.bindCode = bindCode;
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "获取绑定二维码成功", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onPayBindSuccess() {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "绑定成功", Toast.LENGTH_SHORT).show());
                    }
                });
//        PayYchFactory.getInstance(this).netTest("3A5B6BF5FAB891783A5B6CF4FAF79275", SystemUtil.getDeviceId());
    }

    public void jumpToPay(View view){
        Intent intent = new Intent(this, QrCodeActivity.class);
        startActivity(intent);
    }

    public void jumpToBind(View view){
        Intent intent = new Intent(this, BindActivity.class);
        startActivity(intent);
    }

}