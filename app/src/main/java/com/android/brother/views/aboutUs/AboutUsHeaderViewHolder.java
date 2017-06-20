package com.android.brother.views.aboutUs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.brother.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 20-06-2017.
 */

public class AboutUsHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.simple_header)
    TextView headerText;

    public AboutUsHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(String header){
        headerText.setText(header);
    }
}
