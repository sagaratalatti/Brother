package com.android.brother.services;

import com.android.brother.entities.Brother;

import java.util.List;

/**
 * Created by sagar on 12-06-2017.
 */

public class BrotherServices {

    public BrotherServices(){}

    public static class SearchBrotherRequest {
        public String firebaseUrl;

        public SearchBrotherRequest(String firebaseUrl) {
            this.firebaseUrl = firebaseUrl;
        }
    }

    public static class SearchBrotherResponse {
        public List<Brother> brotherList;
    }
}
