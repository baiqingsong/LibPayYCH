package com.dawn.libpayych;

import android.app.Application;
import com.dawn.ych.PayYchFactory;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        PayYchFactory.getInstance(this).initHttp();
    }


}
