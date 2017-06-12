package com.android.brother.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.brother.infrastructure.BrotherApplication;
import com.squareup.otto.Bus;

/**
 * Created by sagar on 12-06-2017.
 */

public class BaseActivity extends AppCompatActivity {

    protected BrotherApplication brotherApplication;
    protected Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        brotherApplication = (BrotherApplication)getApplication();
        bus = brotherApplication.getBus();
        bus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
