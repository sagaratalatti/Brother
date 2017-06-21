package com.android.brother.offline;

import com.android.brother.infrastructure.BrotherApplication;

/**
 * Created by sagar on 12-06-2017.
 */

public class Module {

    public static void Register(BrotherApplication brotherApplication){
        new OfflineBrotherService(brotherApplication);
        new OfflineEventService(brotherApplication);
    }
}
