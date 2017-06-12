package com.android.brother.offline;

import com.android.brother.infrastructure.BrotherApplication;
import com.squareup.otto.Bus;

/**
 * Created by sagar on 12-06-2017.
 */

public class BaseInMemory {

    protected final Bus bus;
    protected final BrotherApplication brotherApplication;

    public BaseInMemory(BrotherApplication brotherApplication) {
        this.brotherApplication = brotherApplication;
        this.bus = brotherApplication.getBus();
    }
}
