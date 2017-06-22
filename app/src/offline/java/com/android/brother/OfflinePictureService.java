package com.android.brother;

import com.android.brother.entities.EventPicture;
import com.android.brother.infrastructure.BrotherApplication;
import com.android.brother.services.EventsPhotoService;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by sagar on 21-06-2017.
 */

public class OfflinePictureService extends BaseInMemory{

    public OfflinePictureService(BrotherApplication brotherApplication) {
        super(brotherApplication);
    }

    @Subscribe
    public void getCommunityPhotos(EventsPhotoService.SearchCommunityPhotoRequest request){
        EventsPhotoService.SearchCommunityPhotoResponse response = new EventsPhotoService.SearchCommunityPhotoResponse();
        response.communityPhotos = new ArrayList<>();

        response.communityPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 50 + "?d=identicon"));
        response.communityPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 51 + "?d=identicon"));
        response.communityPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 52 + "?d=identicon"));

        bus.post(response);
    }

    @Subscribe
    public void getBrotherPhotos(EventsPhotoService.SearchBrotherPhotoRequest request){
        EventsPhotoService.SearchBrotherPhotoResponse response = new EventsPhotoService.SearchBrotherPhotoResponse();
        response.brotherPhotos = new ArrayList<>();

        response.brotherPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 53 + "?d=identicon"));
        response.brotherPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 54 + "?d=identicon"));
        response.brotherPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 55 + "?d=identicon"));

        bus.post(response);
    }

    @Subscribe
    public void getSocialPhotos(EventsPhotoService.SearchSocialPhotoRequest request){
        EventsPhotoService.SearchSocialPhotoResponse response = new EventsPhotoService.SearchSocialPhotoResponse();
        response.socialPhotos = new ArrayList<>();

        response.socialPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 56 + "?d=identicon"));
        response.socialPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 57 + "?d=identicon"));
        response.socialPhotos.add(new EventPicture("http://www.gravatar.com/avatar/" + 58 + "?d=identicon"));

        bus.post(response);
    }
}
