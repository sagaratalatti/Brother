package com.android.brother;

import com.android.brother.entities.RushEvent;
import com.android.brother.infrastructure.BrotherApplication;
import com.android.brother.services.RushEventService;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by sagar on 22-06-2017.
 */

public class OfflineRushEvent extends BaseInMemory {

    public OfflineRushEvent(BrotherApplication brotherApplication) {
        super(brotherApplication);
    }

    @Subscribe
    public void getCommunityRushEvent(RushEventService.SearchRushEventCommunity searchRushEventCommunity){
        RushEventService.SearchRushEventCommunityResponse searchRushEventCommunityResponse = new RushEventService.SearchRushEventCommunityResponse();
        searchRushEventCommunityResponse.communityRushEvents = new ArrayList<>();

        searchRushEventCommunityResponse.communityRushEvents.add(new RushEvent(1, "Rush Community Event 1",
                "22/06/2017", "1:23pm", "Nisarg Co-Operative", 19.208860, 19.208860, false, "This is the dead lock region!"));
        bus.post(searchRushEventCommunityResponse);
    }

    @Subscribe
    public void getSocialRushEvent(RushEventService.SearchRushEventSocial searchRushEventSocial){
        RushEventService.SearchRushEventSocialResponse searchRushEventSocialResponse = new RushEventService.SearchRushEventSocialResponse();
        searchRushEventSocialResponse.socialRushEvents = new ArrayList<>();

        searchRushEventSocialResponse.socialRushEvents.add(new RushEvent(1, "Rush Social Event 1",
                "22/06/2017", "1:23pm", "Whispering Winds", 29.559640, -95.265947, true, "This is another dead lock region!"));
        bus.post(searchRushEventSocialResponse);
    }


}
