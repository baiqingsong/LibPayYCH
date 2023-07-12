package com.dawn.ych;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.text.TextUtils;

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


    public class PayReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent == null)
                return;
            String command = intent.getStringExtra("command");
            if(TextUtils.isEmpty(command))
                return;

        }
    }
}
