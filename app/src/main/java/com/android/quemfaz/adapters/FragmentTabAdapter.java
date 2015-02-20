package com.android.quemfaz.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by nicolle on 20/02/15.
 */
public class FragmentTabAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;

    public FragmentTabAdapter (FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> titles){
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        if (fragments.size() > position){
            return fragments.get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {

        if (fragments != null) {
            return fragments.size();
        } else {
            return 0;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (titles.size() > position){
            return titles.get(position);
        } else {
            return null;
        }
    }
}
