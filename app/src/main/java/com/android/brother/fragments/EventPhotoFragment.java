package com.android.brother.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.brother.R;
import com.android.brother.entities.EventPicture;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by sagar on 21-06-2017.
 */

public class EventPhotoFragment extends BaseFragment {

    @BindView(R.id.fragment_event_image)
    ImageView photo;
    @BindView(R.id.fragment_event_progress)
    ProgressBar progressBar;

    public static final String EVENT_PHOTO_STRING = "EVENT_PHOTO_STRING";

    private String photoStringUrl;

    public static EventPhotoFragment newInstance(EventPicture eventPicture){
        Bundle bundle = new Bundle();
        bundle.putString(EVENT_PHOTO_STRING, eventPicture.getPictureUrl());
        EventPhotoFragment eventPhotoFragment = new EventPhotoFragment();
        eventPhotoFragment.setArguments(bundle);
        return eventPhotoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoStringUrl = getArguments().getString(EVENT_PHOTO_STRING);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_picture, container, false);
        ButterKnife.bind(this, rootView);

        Picasso.with(getActivity()).load(photoStringUrl)
                .fit()
                .centerCrop()
                .into(photo, new Callback() {
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
