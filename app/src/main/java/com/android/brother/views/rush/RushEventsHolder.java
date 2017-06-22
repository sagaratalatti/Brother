package com.android.brother.views.rush;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.brother.R;
import com.android.brother.entities.RushEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 21-06-2017.
 */

public class RushEventsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rush_event_name)
    TextView rushName;
    @BindView(R.id.rush_event_date)
    TextView rushDate;
    @BindView(R.id.rush_event_time)
    TextView rushTime;
    @BindView(R.id.rush_event_location)
    TextView rushLocation;

    public RushEventsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(RushEvent rushEvent){
        itemView.setTag(rushEvent);
        rushName.setText(rushEvent.getEventName());
        rushDate.setText(rushEvent.getEventDate());
        rushTime.setText(rushEvent.getEventTime());
        rushTime.setText(rushEvent.getEventLocation());
    }


}
