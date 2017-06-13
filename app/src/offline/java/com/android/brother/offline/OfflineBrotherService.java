package com.android.brother.offline;

import com.android.brother.entities.Brother;
import com.android.brother.infrastructure.BrotherApplication;
import com.android.brother.services.BrotherServices;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by sagar on 12-06-2017.
 */

public class OfflineBrotherService extends BaseInMemory {

    public OfflineBrotherService(BrotherApplication brotherApplication) {
        super(brotherApplication);
    }

    @Subscribe
    public void getBrothers(BrotherServices.SearchBrotherRequest searchBrotherRequest){
        BrotherServices.SearchBrotherResponse searchBrotherResponse = new BrotherServices.SearchBrotherResponse();
        searchBrotherResponse.brotherList = new ArrayList<>();

        for (int i = 0; i < 32; i++){
            searchBrotherResponse.brotherList.add(new Brother(i, "Brother" + i,
                    "Brother" + i + "Brother" + i + " Joined for Communication",
                    "http://www.gravatar.com/avatar/" + i + "?d=identicon", "Android Developer",
                    "April 2017", "I <3 Programming" ));
        }
        bus.post(searchBrotherResponse);
    }
}
