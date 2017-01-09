package com.idea.l.rxjavaandmvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.idea.l.rxjavaandmvp.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-09
 */
public class ContentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<? extends Fragment> mFragments;
    public ContentPagerAdapter(FragmentManager fm, ArrayList<? extends Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }



    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
