package com.android.brother.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.brother.R;
import com.android.brother.activities.BaseActivity;
import com.android.brother.entities.EventCard;
import com.android.brother.services.EventService;
import com.android.brother.views.aboutUs.AboutUsAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 08-06-2017.
 */

public class AboutUsFragment extends BaseFragment implements AboutUsAdapter.AboutUsListener{

    private static final String TAG = AboutUsFragment.class.getSimpleName();

    private ArrayList<EventCard> serviceCards;
    private ArrayList<EventCard> brotherCards;
    private ArrayList<EventCard> socialCards;

    @BindView(R.id.about_us_recyclerView)
    RecyclerView recyclerView;

    AboutUsAdapter aboutUsAdapter;

    public static AboutUsFragment newInstance(){
        return new AboutUsFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, rootView);
        aboutUsAdapter = new AboutUsAdapter((BaseActivity)getActivity(), this);
        serviceCards = aboutUsAdapter.getCommunityServiceArrayList();
        brotherCards = aboutUsAdapter.getBrotherHoodArrayList();
        socialCards = aboutUsAdapter.getSocialArrayList();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setUpAdapter();
        bus.post(new EventService.SearchCommunityServiceRequest("Hello"));
        bus.post(new EventService.SearchBrotherServiceRequest("Hello"));
        bus.post(new EventService.SearchSocialServiceRequest("Hello"));
        return rootView;
    }

    @Override
    public void onEventClicked(EventCard eventCard) {
        if (!eventCard.isVideo()){
            Log.i(TAG, eventCard.getEventName() + "is slide show");
        } else {
            Log.i(TAG, eventCard.getEventName() + "is Video");
        }
    }

    private void setUpAdapter(){
        if (isAdded()){
            recyclerView.setAdapter(aboutUsAdapter);
        }
    }

    @Subscribe
    public void getCommunityEvents(EventService.SearchCommunityServiceResponse response){
        serviceCards.clear();
        serviceCards.addAll(response.communityService);
    }

    @Subscribe
    public void getBrotherEvents(EventService.SearchBrotherServiceResponse response){
        serviceCards.clear();
        serviceCards.addAll(response.brotherService);
    }

    @Subscribe
    public void getSocialEvents(EventService.SearchSocialServiceRespons response){
        serviceCards.clear();
        serviceCards.addAll(response.socialService);
    }

}
