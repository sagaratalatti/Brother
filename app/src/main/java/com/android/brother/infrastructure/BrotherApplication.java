package com.android.brother.infrastructure;

import android.app.Application;

import com.android.brother.offline.Module;
import com.squareup.otto.Bus;

/**
 * Created by sagar on 12-06-2017.
 */

public class BrotherApplication extends Application {

    private Bus bus;

    public BrotherApplication() {
        this.bus = new Bus();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Module.Register(this);
    }

    public Bus getBus() {
        return bus;
    }
}
