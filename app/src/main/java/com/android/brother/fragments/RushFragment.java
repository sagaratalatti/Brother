package com.android.brother.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.brother.R;

/**
 * Created by sagar on 08-06-2017.
 */

public class RushFragment extends BaseFragment {

    public static RushFragment newInstance(){
        return new RushFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rush, container, false);
        return rootView;
    }
}