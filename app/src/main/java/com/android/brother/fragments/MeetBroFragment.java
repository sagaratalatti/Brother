package com.android.brother.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.brother.R;
import com.android.brother.activities.BaseActivity;
import com.android.brother.entities.Brother;
import com.android.brother.services.BrotherServices;
import com.android.brother.views.meetBrother.MeetBroAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 08-06-2017.
 */

public class MeetBroFragment extends BaseFragment implements MeetBroAdapter.onBrotherClickListener{

    private static final String TAG = MeetBroFragment.class.getSimpleName();

    @BindView(R.id.meet_bro_recyclerView)
    RecyclerView recyclerView;

    private MeetBroAdapter meetBroAdapter;
    private ArrayList<Brother> brotherArrayList;

    public static MeetBroFragment newInstance(){
        return new MeetBroFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meet_bro, container, false);
        ButterKnife.bind(this, rootView);
        meetBroAdapter = new MeetBroAdapter((BaseActivity) getActivity(), this);
        brotherArrayList = meetBroAdapter.getBrotherArrayList();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        bus.post(new BrotherServices.SearchBrotherRequest("http"));
        setMeetBroAdapter();
        return rootView;
    }

    private void setMeetBroAdapter(){
        if (isAdded()){
            recyclerView.setAdapter(meetBroAdapter);
        }
    }

    @Subscribe
    public void getBrothers (BrotherServices.SearchBrotherResponse searchBrotherResponse){
        brotherArrayList.clear();
        brotherArrayList.addAll(searchBrotherResponse.brotherList);
    }

    @Override
    public void onBrotherClicked(Brother brother) {
        Log.i(TAG, brother.getBrotherName());
    }
}
