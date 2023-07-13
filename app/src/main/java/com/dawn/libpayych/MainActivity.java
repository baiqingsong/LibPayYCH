package com.dawn.libpayych;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.dawn.ych.PayYchFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PayYchFactory.getInstance(this).netTest("SN111111");
    }

}