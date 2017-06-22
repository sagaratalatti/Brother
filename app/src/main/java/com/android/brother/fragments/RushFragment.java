package com.android.brother.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.brother.R;
import com.android.brother.activities.BaseActivity;
import com.android.brother.entities.RushEvent;
import com.android.brother.services.RushEventService;
import com.android.brother.views.rush.Item;
import com.android.brother.views.rush.RushAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 08-06-2017.
 */

public class RushFragment extends BaseFragment implements RushAdapter.OnRushListener {

    public static RushFragment newInstance(){
        return new RushFragment();
    }

    @BindView(R.id.rush_recyclerView)
    RecyclerView recyclerView;

    private RushAdapter rushAdapter;
    private ArrayList<RushEvent> socialEvents;
    private ArrayList<RushEvent> communityEvents;

    private Item social;
    private Item community;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rush, container, false);
        ButterKnife.bind(this, rootView);
        rushAdapter = new RushAdapter((BaseActivity)getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        socialEvents = new ArrayList<>();
        communityEvents = new ArrayList<>();
        List<Item> data = rushAdapter.getData();
        social = new Item(RushAdapter.VIEW_TYPE_EXPANDABLE_HEADER, "Social Events");
        social.invisibleChildren = new ArrayList<>();
        community = new Item(RushAdapter.VIEW_TYPE_EXPANDABLE_CHILD, "Community Events");
        community.invisibleChildren = new ArrayList<>();
        bus.post(new RushEventService.SearchRushEventCommunity("Hello"));
        bus.post(new RushEventService.SearchRushEventSocial("Hello"));
        setUpAdapter();
        data.add(community);
        data.add(social);
        return rootView;
    }

    private void setUpAdapter(){
        if (isAdded()){
            recyclerView.setAdapter(rushAdapter);
        }
    }

    @Override
    public void onRushClicked(RushEvent rushEvent) {

    }

    @Subscribe
    public void getServiceEvents(RushEventService.SearchRushEventCommunityResponse response){
        communityEvents.clear();
        communityEvents.addAll(response.communityRushEvents);
        for (RushEvent rushEvent : communityEvents){
            community.invisibleChildren.add(new Item(RushAdapter.VIEW_TYPE_EXPANDABLE_CHILD, rushEvent));
        }
    }

    @Subscribe
    public void getSocialEvents(RushEventService.SearchRushEventSocialResponse response){
        socialEvents.clear();
        socialEvents.addAll(response.socialRushEvents);
        for (RushEvent rushEvent : socialEvents){
            social.invisibleChildren.add(new Item(RushAdapter.VIEW_TYPE_EXPANDABLE_CHILD, rushEvent));
        }
    }
}
