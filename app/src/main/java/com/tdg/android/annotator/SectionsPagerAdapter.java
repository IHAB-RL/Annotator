package com.tdg.android.annotator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ulrikkowalk on 26.10.17.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private String LOG = "SectionsPagerAdapter";
    private List<Fragment> mListOfFragments = new ArrayList<>();
    private List<String> mListOfFragmentTitles = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String string) {
        mListOfFragments.add(fragment);
        mListOfFragmentTitles.add(string);
    }

    public void addFragmentAt(Fragment fragment, String string, int pos) {
        mListOfFragments.add(pos, fragment);
        mListOfFragmentTitles.add(pos, string);
    }

    public void removeFragment(int num) {
        mListOfFragments.remove(num);
        mListOfFragmentTitles.remove(num);
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
