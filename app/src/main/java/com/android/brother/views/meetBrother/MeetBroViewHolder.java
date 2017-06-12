package com.android.brother.views.meetBrother;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.brother.R;
import com.android.brother.entities.Brother;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 08-06-2017.
 */

public class MeetBroViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.bro_image)
    ImageView profileImage;
    @BindView(R.id.bro_item_progress)
    ProgressBar progressBar;

    public MeetBroViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(Context context, Brother brother){
        itemView.setTag(brother);
        Picasso.with(context).load(brother.getBrotherPicture()).fit().centerCrop().into(profileImage, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });


    }
}
