package com.android.brother.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.brother.R;
import com.android.brother.entities.Brother;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by sagar on 12-06-2017.
 */

public class BrotherDetailFragment extends BaseFragment {

    @BindView(R.id.fragment_bro_image)
    ImageView profileImage;
    @BindView(R.id.fragment_bro_item_progress)
    ProgressBar progressBar;
    @BindView(R.id.fragment_bro_name)
    TextView name;
    @BindView(R.id.fragment_bro_major)
    TextView major;
    @BindView(R.id.fragment_bro_crossed)
    TextView crossed;
    @BindView(R.id.fragment_bro_funFact)
    TextView funFact;
    @BindView(R.id.fragment_bro_joined_text)
    TextView whyJoined;

    private Brother brother;
    public static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";

    public static BrotherDetailFragment newInstance(Brother brother){
        Bundle arguments = new Bundle();
        arguments.putParcelable(BROTHER_EXTRA_INFO, brother);
        BrotherDetailFragment brotherDetailFragment = new BrotherDetailFragment();
        brotherDetailFragment.setArguments(arguments);
        return brotherDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        brother = getArguments().getParcelable(BROTHER_EXTRA_INFO);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brother_details, container, false);
        ButterKnife.bind(this, rootView);
        name.setText(brother.getBrotherName());
        major.setText(getString(R.string.major_intro, brother.getBrotherMajor()));
        funFact.setText(getString(R.string.fun_fact, brother.getBrotherFunFact()));
        crossed.setText(getString(R.string.crossed_semester, brother.getBrotherCrossSemester()));
        whyJoined.setText(brother.getWhyJoined());
        Picasso.with(getActivity()).load(brother.getBrotherPicture()).fit().centerCrop().into(profileImage, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(GONE);
            }

            @Override
            public void onError() {

            }
        });

        return rootView;
    }
}
