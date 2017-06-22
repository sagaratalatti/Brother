package com.android.brother.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.brother.R;
import com.android.brother.entities.EventCard;
import com.android.brother.entities.EventPicture;
import com.android.brother.fragments.EventPhotoFragment;
import com.android.brother.services.EventsPhotoService;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 21-06-2017.
 */

public class PhotoPagerActivity extends BaseActivity {

    private ArrayList<EventPicture> mEventPhotos;

    @BindView(R.id.event_picture_viewPager)
    ViewPager viewPager;

    public static final String EXTRA_CARD_INFO = "EXTRA_CARD_INFO";

    public static Intent newIntent(Context context, EventCard eventCard){
        Intent intent = new Intent(context, PhotoPagerActivity.class);
        intent.putExtra(EXTRA_CARD_INFO, eventCard.getEventId());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pager);
        ButterKnife.bind(this);
        mEventPhotos = new ArrayList<>();
        int cardIndex = getIntent().getIntExtra(EXTRA_CARD_INFO, 0);

        switch (cardIndex){
            case 1: bus.post(new EventsPhotoService.SearchCommunityPhotoRequest("Hello"));
                break;
            case 3: bus.post(new EventsPhotoService.SearchBrotherPhotoRequest("Hello"));
                break;
            case 5: bus.post(new EventsPhotoService.SearchSocialPhotoRequest("Hello"));
                break;

        }

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                EventPicture eventPicture = mEventPhotos.get(position);
                return EventPhotoFragment.newInstance(eventPicture);
            }

            @Override
            public int getCount() {
                return mEventPhotos.size();
            }
        });
    }

    @Subscribe
    public void getCommunityPhotos(EventsPhotoService.SearchCommunityPhotoResponse response){
        mEventPhotos.clear();
        mEventPhotos.addAll(response.communityPhotos);
    }

    @Subscribe
    public void getBrotherPhotos(EventsPhotoService.SearchBrotherPhotoResponse response){
        mEventPhotos.clear();
        mEventPhotos.addAll(response.brotherPhotos);
    }

    @Subscribe
    public void getSocialPhotos(EventsPhotoService.SearchSocialPhotoResponse response){
        mEventPhotos.clear();
        mEventPhotos.addAll(response.socialPhotos);
    }


}
