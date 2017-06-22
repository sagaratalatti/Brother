package com.android.brother.views.rush;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.brother.R;
import com.android.brother.activities.BaseActivity;
import com.android.brother.entities.RushEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 21-06-2017.
 */

public class RushAdapter extends RecyclerView.Adapter {

    private final int VIEW_TYPE_HEADER = 1;
    public static final int VIEW_TYPE_EXPANDABLE_HEADER = 2;
    public static final int VIEW_TYPE_EXPANDABLE_CHILD = 3;
    private final int VIEW_TYPE_FOOTER = 4;


    private List<Item> data;
    private BaseActivity activity;
    private LayoutInflater layoutInflater;
    private OnRushListener listener;

    public RushAdapter(BaseActivity activity, OnRushListener listener) {
        this.activity = activity;
        this.listener = listener;
        layoutInflater = activity.getLayoutInflater();
        data = new ArrayList<>();
    }

    public List<Item> getData() {
        return data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return VIEW_TYPE_HEADER;
        }

        position --;

        if (position<data.size()){
            return data.get(position).type;
        }

        position -= data.size();

        if (position<data.size()){
            return VIEW_TYPE_FOOTER;
        }

        position --;

        throw new IllegalArgumentException("We are being for a viewType at position " + position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View expandableHeaderView = layoutInflater.inflate(R.layout.expandable_header, parent, false);
        View rushView = layoutInflater.inflate(R.layout.rush_event, parent, false);
        View rushHeaderView = layoutInflater.inflate(R.layout.header_rush, parent, false);
        View footerView = layoutInflater.inflate(R.layout.footer_rush_fragment, parent, false);

        switch (viewType){
            case VIEW_TYPE_HEADER:
                return new RushHeaderHolder(rushHeaderView);
            case VIEW_TYPE_EXPANDABLE_CHILD:
                final RushEventsHolder holder = new RushEventsHolder(rushView);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RushEvent rushEvent = (RushEvent)holder.itemView.getTag();
                        listener.onRushClicked(rushEvent);
                    }
                });
                return holder;
            case VIEW_TYPE_FOOTER:
                return new RushFooterHolder(footerView);
            case VIEW_TYPE_EXPANDABLE_HEADER:
                return new RushExpandableHeaderHolder(expandableHeaderView);
        }

        throw new IllegalArgumentException(viewType + "is not supported in this adapter");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RushExpandableHeaderHolder){
            position --;

            final Item item = data.get(position);
            final RushExpandableHeaderHolder itemController = (RushExpandableHeaderHolder)holder;

            itemController.refferalItem = item;
            itemController.headerName.setText(item.header);

            if (item.invisibleChildren == null){
                itemController.headerToggle.setImageResource(R.mipmap.close);
            } else {
                itemController.headerToggle.setImageResource(R.mipmap.open);
            }

            itemController.headerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (item.invisibleChildren == null){
                        item.invisibleChildren = new ArrayList<>();
                        int count = 0;
                        int position = data.indexOf(itemController.refferalItem);

                        while (data.size()>position+1 && data.get(position + 1).type == VIEW_TYPE_EXPANDABLE_CHILD){
                            item.invisibleChildren.add(data.remove(position+1));
                            count++;
                        }
                        notifyItemRangeRemoved(position+1, count);
                        itemController.headerToggle.setImageResource(R.mipmap.open);
                    } else {
                        int position = data.indexOf(itemController.refferalItem);
                        int index = position+1;

                        for (Item item1 : item.invisibleChildren){
                            data.add(index, item1);
                            index++;
                        }
                        notifyItemRangeRemoved(position+1, index-position-1);
                        itemController.headerToggle.setImageResource(R.mipmap.close);
                        item.invisibleChildren = null;
                    }
                }
            });
        } else if (holder instanceof RushEventsHolder){
            position --;
            RushEventsHolder holder1 = (RushEventsHolder)holder;
            holder1.populate(data.get(position).rushEvent);
        } else if (holder instanceof RushHeaderHolder){
            RushHeaderHolder rushHeaderHolder = (RushHeaderHolder)holder;
        } else if (holder instanceof RushFooterHolder){
            ((RushFooterHolder) holder).populate(activity);
        }
    }

    @Override
    public int getItemCount() {
        int count = 2;

        if (data.size()>0){
            count += data.size();
        }

        return count;
    }

    public interface OnRushListener{
        void onRushClicked(RushEvent rushEvent);
    }
}
