package com.android.brother.views.meetBrother;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.brother.R;
import com.android.brother.activities.BaseActivity;
import com.android.brother.entities.Brother;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar on 08-06-2017.
 */

public class MeetBroAdapter extends RecyclerView.Adapter<MeetBroViewHolder> implements View.OnClickListener {

    private LayoutInflater inflater;
    private BaseActivity baseActivity;
    private onBrotherClickListener onBrotherClickListener;
    private ArrayList<Brother> brotherArrayList;

    public MeetBroAdapter(BaseActivity baseActivity, MeetBroAdapter.onBrotherClickListener onBrotherClickListener) {
        this.baseActivity = baseActivity;
        this.onBrotherClickListener = onBrotherClickListener;
        this.inflater = baseActivity.getLayoutInflater();
        brotherArrayList = new ArrayList<>();
    }

    public ArrayList<Brother> getBrotherArrayList(){
        return brotherArrayList;
    }

    @Override
    public MeetBroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View brotherView = inflater.inflate(R.layout.bro_item_layout, parent, false);
        brotherView.setOnClickListener(this);
        return new MeetBroViewHolder(brotherView);
    }

    @Override
    public void onBindViewHolder(MeetBroViewHolder holder, int position) {
        holder.populate(baseActivity, brotherArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return brotherArrayList.size();
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() instanceof  Brother){
            Brother brother = (Brother)view.getTag();
            onBrotherClickListener.onBrotherClicked(brother);
        }
    }

    public interface onBrotherClickListener{
        void onBrotherClicked(Brother brother);
    }
}
