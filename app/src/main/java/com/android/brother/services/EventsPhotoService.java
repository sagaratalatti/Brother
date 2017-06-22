package com.android.brother.services;

import com.android.brother.entities.EventPicture;

import org.w3c.dom.ls.LSInput;

import java.util.List;

/**
 * Created by sagar on 21-06-2017.
 */

public class EventsPhotoService {

    private EventsPhotoService(){}

    public static class SearchCommunityPhotoRequest{

        public String firerbaseUrl;

        public SearchCommunityPhotoRequest(String firebaseUrl){
            this.firerbaseUrl = firebaseUrl;
        }
    }

    public static class SearchCommunityPhotoResponse{

        public List<EventPicture> communityPhotos;
    }

    public static class SearchBrotherPhotoRequest{
        public String firebaseUrl;

        public SearchBrotherPhotoRequest(String firebaseUrl) {
            this.firebaseUrl = firebaseUrl;
        }
    }

    public static class SearchBrotherPhotoResponse{

        public List<EventPicture> brotherPhotos;
    }

    public static class SearchSocialPhotoRequest{
        public String firebaseUrl;

        public SearchSocialPhotoRequest(String firebaseUrl) {
            this.firebaseUrl = firebaseUrl;
        }
    }

    public static class SearchSocialPhotoResponse{

        public List<EventPicture> socialPhotos;
    }


}
