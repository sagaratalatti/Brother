package com.android.brother.views.aboutUs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.brother.activities.BaseActivity;
import com.android.brother.entities.EventCard;

import java.util.ArrayList;

/**
 * Created by sagar on 20-06-2017.
 */

public class AboutUsAdapter extends RecyclerView.Adapter {

    private final int VIEW_TYPE_MAIN_HEADER = 1;
    private final int VIEW_TYPE_SERVICE = 2;
    private final int VIEW_TYPE_BROTHERHOOD = 3;
    private final int VIEW_TYPE_SOCIAL = 4;
    private final int VIEW_TYPE_HEADER = 5;

    private LayoutInflater layoutInflater;
    private BaseActivity activity;
    private ArrayList<EventCard> communityServiceArrayList;
    private ArrayList<EventCard> brotherHoodArrayList;
    private ArrayList<EventCard> socialArrayList;

    private AboutUsListener listener;

    public AboutUsAdapter(BaseActivity activity, AboutUsListener listener) {
        this.activity = activity;
        this.listener = listener;
        layoutInflater = activity.getLayoutInflater();
        communityServiceArrayList = new ArrayList<>();
        brotherHoodArrayList = new ArrayList<>();
        socialArrayList = new ArrayList<>();
    }

    public ArrayList<EventCard> getCommunityServiceArrayList() {
        return communityServiceArrayList;
    }

    public ArrayList<EventCard> getBrotherHoodArrayList() {
        return brotherHoodArrayList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return VIEW_TYPE_MAIN_HEADER;
        }

        position --;

        if (communityServiceArrayList.size()>0){
            if (position == 0){
                return VIEW_TYPE_HEADER;
            }

            position --;
        }

        if (position<communityServiceArrayList.size()){
            return VIEW_TYPE_SERVICE;
        }

        position -= communityServiceArrayList.size();

        if (brotherHoodArrayList.size()>0){
            if (position == 0){
                return VIEW_TYPE_HEADER;
            }

            position --;
        }

        if (position<brotherHoodArrayList.size()){
            return VIEW_TYPE_BROTHERHOOD;
        }

        position -= brotherHoodArrayList.size();

        if (socialArrayList.size()>0){
            if (position == 0){
                return VIEW_TYPE_HEADER;
            }
            position --;
        }

        if (position<socialArrayList.size()){
            return VIEW_TYPE_SOCIAL;
        }

        position -= socialArrayList.size();

        throw new IllegalArgumentException("We are being asked for a viewType position " + position);
    }

    public ArrayList<EventCard> getSocialArrayList() {
        return socialArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface AboutUsListener{
        void onEventClicked(EventCard eventCard);
    }
}
