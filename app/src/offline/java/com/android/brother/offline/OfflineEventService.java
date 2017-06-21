package com.android.brother.offline;

import com.android.brother.entities.EventCard;
import com.android.brother.infrastructure.BrotherApplication;
import com.android.brother.services.EventService;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by sagar on 21-06-2017.
 */

public class OfflineEventService extends BaseInMemory {

    public OfflineEventService(BrotherApplication brotherApplication) {
        super(brotherApplication);
    }

    @Subscribe
    public void searchCommunityCards(EventService.SearchCommunityServiceRequest request){
        EventService.SearchCommunityServiceResponse response = new EventService.SearchCommunityServiceResponse();
        response.communityService = new ArrayList<>();

        response.communityService.add(new EventCard(1,
                "Community Event 1", "Community Event 1's Description",
               "http://www.gravatar.com/avatar/" + 1 + "?d=identicon", false,
                "null"));

        response.communityService.add(new EventCard(2,
                "Community Event 2", "Community Event 2's Description",
                "http://www.gravatar.com/avatar/" + 2 + "?d=identicon", false,
                "null"));

        bus.post(response);
    }

    @Subscribe
    public void searchBrotherCards(EventService.SearchBrotherServiceRequest request){
        EventService.SearchBrotherServiceResponse response = new EventService.SearchBrotherServiceResponse();
        response.brotherService = new ArrayList<>();

        response.brotherService.add(new EventCard(3,
                "Brother Event 1", "Brother Event 1's Description",
                "http://www.gravatar.com/avatar/" + 3 + "?d=identicon", false,
                "null"));

        response.brotherService.add(new EventCard(4,
                "Brother Event 2", "Brother Event 2's Description",
                "http://www.gravatar.com/avatar/" + 4 + "?d=identicon", true,
                "null"));

        bus.post(response);
    }

    @Subscribe
    public void searchSocialCards(EventService.SearchSocialServiceRequest request){
        EventService.SearchSocialServiceRespons response = new EventService.SearchSocialServiceRespons();
        response.socialService = new ArrayList<>();

        response.socialService.add(new EventCard(5,
                "Social Event 1", "Social Event 1's Description",
                "http://www.gravatar.com/avatar/" + 5 + "?d=identicon", false,
                "null"));

        response.socialService.add(new EventCard(6,
                "Social Event 2", "Social Event 2's Description",
                "http://www.gravatar.com/avatar/" + 6 + "?d=identicon", true,
                "null"));

        bus.post(response);
    }
}
