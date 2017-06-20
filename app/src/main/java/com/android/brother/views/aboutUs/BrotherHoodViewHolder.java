package com.android.brother.views.aboutUs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.brother.R;
import com.android.brother.entities.EventCard;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by sagar on 20-06-2017.
 */

public class BrotherHoodViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.event_card_description)
    TextView eventDescription;
    @BindView(R.id.event_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.event_card_imageView)
    ImageView eventsImage;
    @BindView(R.id.event_card_name)
    TextView eventName;
    @BindView(R.id.event_card_type)
    ImageView eventType;

    public BrotherHoodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(Context context, EventCard eventCard){
        itemView.setTag(eventCard);

        if (eventCard.isVideo()){
            eventType.setImageResource(R.mipmap.camera);
        } else {
            eventType.setImageResource(R.mipmap.video);
        }

        eventDescription.setText(eventCard.getEventDescription());
        eventName.setText(eventCard.getEventName());
        Picasso.with(context).load(eventCard.getEventImage()).fit().centerCrop()
                .into(eventsImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
