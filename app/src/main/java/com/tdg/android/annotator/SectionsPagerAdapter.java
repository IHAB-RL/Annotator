package com.tdg.android.annotator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulrikkowalk on 26.10.17.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private String LOG = "com.tdg.android.annotator.SectionsPagerAdapter";
    private List<Fragment> mListOfFragments = new ArrayList<>();
    private List<String> mListOfFragmentTitles = new ArrayList<>();



    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String string) {
        mListOfFragments.add(fragment);
        mListOfFragmentTitles.add(string);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListOfFragmentTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mListOfFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListOfFragments.size();
    }
}
