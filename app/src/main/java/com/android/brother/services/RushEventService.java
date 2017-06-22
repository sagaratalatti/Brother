package com.android.brother.services;

import com.android.brother.entities.RushEvent;

import java.util.List;

/**
 * Created by sagar on 22-06-2017.
 */

public class RushEventService {

    private RushEventService(){}

    public static class SearchRushEventCommunity{
        public String firebaseUrl;

        public SearchRushEventCommunity(String firebaseUrl) {
            this.firebaseUrl = firebaseUrl;
        }
    }

    public static class SearchRushEventCommunityResponse{
        public List<RushEvent> communityRushEvents;
    }

    public static class SearchRushEventSocial {
        public String firebaseUrl;

        public SearchRushEventSocial(String firebaseUrl) {
            this.firebaseUrl = firebaseUrl;
        }
    }

    public static class SearchRushEventSocialResponse{
        public List<RushEvent> socialRushEvents;
    }
}
