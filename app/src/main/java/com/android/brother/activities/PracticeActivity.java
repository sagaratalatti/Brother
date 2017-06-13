package com.android.brother.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.brother.R;
import com.android.brother.entities.Brother;
import com.android.brother.fragments.BrotherDetailFragment;

/**
 * Created by sagar on 13-06-2017.
 */

public class PracticeActivity extends BaseActivity {

    public static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.practice_fragment);
        if (fragment == null){
            //fragment = BrotherDetailFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.practice_fragment, fragment).commit();
        }
    }

    public static Intent newIntent (Context context, Brother brother){
        Intent intent = new Intent(context, PracticeActivity.class);
        intent.putExtra(BROTHER_EXTRA_INFO, brother);
        return intent;
    }
}
