package com.android.brother.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.brother.fragments.AboutUsFragment;
import com.android.brother.fragments.MeetBroFragment;
import com.android.brother.fragments.RushFragment;

/**
 * Created by sagar on 08-06-2017.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;
        switch (position){
            case 0: returnFragment = AboutUsFragment.newInstance();
                break;

            case 1: returnFragment = MeetBroFragment.newInstance();
                break;

            case 2: returnFragment = RushFragment.newInstance();
                break;

            default: return null;
        }
        return returnFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;
        switch (position){
            case 0: title = "About Us";
                break;

            case 1: title = "Meet A Bro";
                break;

            case 2: title = "Rush";
                break;

            default: title = null;
        }
        return title;
    }
}
