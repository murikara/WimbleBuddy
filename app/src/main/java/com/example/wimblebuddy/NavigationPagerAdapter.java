package com.example.wimblebuddy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.wimblebuddy.fragment.HomeFragment;
import com.example.wimblebuddy.fragment.ProfileFragment;
import com.example.wimblebuddy.fragment.SearchFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class NavigationPagerAdapter extends FragmentStatePagerAdapter {

    public NavigationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    private final int HOME_POSITION = 0;
    private final int SEARCH_POSITION = 1;
    private final int PROFILE_POSITION = 2;
    //Show 3 total pages
    private final int LIMIT_ADAPTER = 3;

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch(position){
            case HOME_POSITION:
                return HomeFragment.newInstance(position + 1);
            case SEARCH_POSITION:
                return SearchFragment.newInstance();
            case PROFILE_POSITION:
                return ProfileFragment.newInstance();
        }
        return HomeFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return LIMIT_ADAPTER;
    }


}