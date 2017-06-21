package com.android.brother.views.aboutUs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.brother.R;
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
        View eventCardView = layoutInflater.inflate(R.layout.event_card, parent, false);
        View headerView = layoutInflater.inflate(R.layout.simple_header, parent, false);

        if (viewType == VIEW_TYPE_MAIN_HEADER){
            return  new AboutUsMainHeaderViewHolder(layoutInflater, parent);
        } else if (viewType == VIEW_TYPE_SERVICE){
            final CommunityServiceViewHolder communityServiceViewHolder = new CommunityServiceViewHolder(eventCardView);
            communityServiceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventCard eventCard = (EventCard) communityServiceViewHolder.itemView.getTag();
                    listener.onEventClicked(eventCard);
                }
            });

            return communityServiceViewHolder;
        }

        else if (viewType == VIEW_TYPE_BROTHERHOOD){
            final  BrotherHoodViewHolder brotherHoodViewHolder = new BrotherHoodViewHolder(eventCardView);
            brotherHoodViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventCard eventCard = (EventCard) brotherHoodViewHolder.itemView.getTag();
                    listener.onEventClicked(eventCard);
                }
            });

            return brotherHoodViewHolder;
        }

        else if (viewType == VIEW_TYPE_SOCIAL){
            final  SocialViewHolder socialViewHolder = new SocialViewHolder(eventCardView);
            socialViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventCard eventCard = (EventCard) socialViewHolder.itemView.getTag();
                    listener.onEventClicked(eventCard);
                }
            });

            return socialViewHolder;
        }

        else if (viewType == VIEW_TYPE_HEADER){
            return new AboutUsHeaderViewHolder(eventCardView);
        }

        throw new IllegalArgumentException(viewType + "is not supported in this adapter");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AboutUsMainHeaderViewHolder){
            AboutUsMainHeaderViewHolder aboutUsMainHeaderViewHolder = (AboutUsMainHeaderViewHolder)  holder;
        }

        if (holder instanceof  CommunityServiceViewHolder){
            position --;
            if (communityServiceArrayList.size()>0){
                position--;
            }
            EventCard eventCard = communityServiceArrayList.get(position);
            ((CommunityServiceViewHolder) holder).populate(activity, eventCard);
        }

        if (holder instanceof BrotherHoodViewHolder){
            position --;
            if (communityServiceArrayList.size()>0){
                position--;
                position -= communityServiceArrayList.size();
            }

            if (brotherHoodArrayList.size()>0){
                position --;
            }
            EventCard eventCard = brotherHoodArrayList.get(position);
            ((BrotherHoodViewHolder)holder).populate(activity, eventCard);
        }

        if (holder instanceof SocialViewHolder){
            position --;
            if (brotherHoodArrayList.size()>0){
                position --;
                position -= brotherHoodArrayList.size();
            }

            if (socialArrayList.size()>0){
                position --;
            }
            EventCard eventCard = socialArrayList.get(position);
            ((SocialViewHolder)holder).populate(activity, eventCard);
        }

        if(holder instanceof AboutUsHeaderViewHolder){
            AboutUsHeaderViewHolder aboutUsHeaderViewHolder = (AboutUsHeaderViewHolder)holder;
            int servicePosition = 1;
            int brotherhoodPosition = servicePosition + communityServiceArrayList.size() + 1;
            int socialPosition = brotherhoodPosition + brotherHoodArrayList.size() + 1;

            if (position == servicePosition){
                aboutUsHeaderViewHolder.populate("Community Service Events");
            }

            if (position == brotherhoodPosition){
                aboutUsHeaderViewHolder.populate("Brotherhood Event");
            }

            if (position == socialPosition){
                aboutUsHeaderViewHolder.populate("Social Events");
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = 1;

        if (communityServiceArrayList.size()>0){
            count += 1 + communityServiceArrayList.size();
        }

        if (brotherHoodArrayList.size()>0){
            count += 1 + brotherHoodArrayList.size();
        }

        if (socialArrayList.size()>0){
            count += 1 + socialArrayList.size();
        }

        return count;
    }

    public interface AboutUsListener{
        void onEventClicked(EventCard eventCard);
    }
}
