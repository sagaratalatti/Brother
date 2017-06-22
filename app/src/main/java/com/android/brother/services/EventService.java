package com.android.brother.services;

import com.android.brother.entities.EventCard;

import java.util.List;

/**
 * Created by sagar on 21-06-2017.
 */

public class EventService {

    private EventService(){}

    public static class  SearchCommunityServiceRequest{

        public String firebaseUrl;

        public SearchCommunityServiceRequest(String fireBaseUrl) {
            this.firebaseUrl = fireBaseUrl;
        }
    }

    public static class SearchCommunityServiceResponse{

        public List<EventCard> communityService;
    }


    public static class SearchBrotherServiceRequest{

        public String firebaseUrl;

        public SearchBrotherServiceRequest(String firebaseUrl){
            this.firebaseUrl = firebaseUrl;
        }
    }

    public static class SearchBrotherServiceResponse{

        public List<EventCard> brotherService;
    }


    public static class SearchSocialServiceRequest{

        public String firebaseUrl;

        public SearchSocialServiceRequest(String firebaseUrl){
            this.firebaseUrl = firebaseUrl;
        }
    }

    public static class SearchSocialServiceRespons{

        public List<EventCard> socialService;
    }
}
