package com.android.brother.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.brother.R;
import com.android.brother.entities.Brother;
import com.android.brother.fragments.BrotherDetailFragment;
import com.android.brother.services.BrotherServices;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 13-06-2017.
 */

public class BrotherPagerActivity extends BaseActivity {

    @BindView(R.id.brother_viewPager)
    ViewPager viewPager;
    public static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";
    private ArrayList<Brother> brotherArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brother_pager);
        ButterKnife.bind(this);
        brotherArrayList = new ArrayList<>();
        bus.post(new BrotherServices.SearchBrotherRequest("https"));
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Brother brother = brotherArrayList.get(position);
                return BrotherDetailFragment.newInstance(brother);
            }

            @Override
            public int getCount() {
                return brotherArrayList.size();
            }
        });

        Brother brother =  getIntent().getParcelableExtra(BROTHER_EXTRA_INFO);
        int brotherId = brother.getBrotherId();
        for (int i=0; i<brotherArrayList.size(); i++){
            if (brotherArrayList.get(i).getBrotherId() == brotherId){
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Subscribe
    public void  getBrothers(BrotherServices.SearchBrotherResponse searchBrotherResponse){
        brotherArrayList.clear();
        brotherArrayList.addAll(searchBrotherResponse.brotherList);
    }

    public static Intent newIntent(Context context, Brother brother){
        Intent intent = new Intent(context, BrotherPagerActivity.class);
        intent.putExtra(BROTHER_EXTRA_INFO, brother);
        return intent;
    }
}
