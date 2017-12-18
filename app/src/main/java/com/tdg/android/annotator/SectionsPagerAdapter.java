package com.tdg.android.annotator;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.tdg.android.annotator.R.id.container;

/**
 * Created by ulrikkowalk on 26.10.17.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter implements Communicator{

    private String LOG = "SectionsPagerAdapter";
    private List<Fragment> mListOfFragments = new ArrayList<>();
    private List<String> mListOfFragmentTitles = new ArrayList<>();
    private final SparseArray<WeakReference<Fragment>> instantiatedFragments = new SparseArray<>();
    private Communicator communicator;

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
    public Object instantiateItem(final ViewGroup container, final int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        instantiatedFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Nullable
    public Fragment getFragment(final int position) {
        final WeakReference<Fragment> wr = instantiatedFragments.get(position);
        if (wr != null) {
            return wr.get();
        } else {
            return null;
        }
    }

    public Fragment getFragmentFromPos(int pos) {
        return mListOfFragments.get(pos);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        instantiatedFragments.remove(position);
        super.destroyItem(container, position, object);
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

    public void finishAnnotation(String sCharakter, String sFreitext) {}

    public void beginAnnotation(String raterId, String subjectId, boolean isUebung) {}

    public void addAnnotation(int Code){}

    public void removeLastAnnotation(){}

    public void setWasTouched(boolean touched){}

    public void setImmersiveMode(){}

    @Override
    public void scrollUp() { communicator.scrollUp(); }

}
