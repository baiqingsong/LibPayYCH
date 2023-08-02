package com.dawn.libpayych;

import android.app.Application;
import com.dawn.ych.PayFactory;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        PayFactory.getInstance(this).initHttp(true);
    }


}
