package com.android.brother.views.rush;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.brother.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 21-06-2017.
 */

public class RushExpandableHeaderHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.expandable_header_name)
    TextView headerName;
    @BindView(R.id.expandable_header_buttonToggle)
    ImageView headerToggle;
    @BindView(R.id.expandable_header_layout)
    View headerLayout;

    public Item refferalItem;

    public RushExpandableHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}
