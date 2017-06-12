package com.android.brother.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.brother.infrastructure.BrotherApplication;
import com.squareup.otto.Bus;

/**
 * Created by sagar on 12-06-2017.
 */

public class BaseFragment extends Fragment {

    protected Bus bus;
    protected BrotherApplication brotherApplication;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        brotherApplication = (BrotherApplication)getActivity().getApplication();
        bus = brotherApplication.getBus();
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
