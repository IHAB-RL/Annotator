package com.tdg.android.annotator;


import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private String LOG = "MainActivity";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static ViewPager mViewPager;
    private AnnotationKeeper annotationKeeper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        annotationKeeper = new AnnotationKeeper();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void setupViewPager(ViewPager viewPager) {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFragment(new FragmentNewSession(), "New Session");
        mSectionsPagerAdapter.addFragment(new FragmentAnnotation(), "Annotation");
        mSectionsPagerAdapter.addFragment(new FragmentFinalise(), "Finalise");
        viewPager.setAdapter(mSectionsPagerAdapter);
    }

    public void setAnnotationIDs(String raterId, String subjectId) {
        annotationKeeper.setRaterID(raterId);
        annotationKeeper.setSubjectID(subjectId);

        Log.i(LOG, "Rater ID set to: "+raterId+", Subject ID set to: "+subjectId);
    }

    public void addAnnotation(String string) {
        annotationKeeper.addAnnotation(string);
        Log.i(LOG, "New annotation ["+annotationKeeper.getNumberOfAnnotations()+"]: "+string);
    }

    public void removeLastAnnotation() {
        annotationKeeper.removeLastAnnotation();
        Log.i(LOG, "Annotation removed ["+annotationKeeper.getNumberOfAnnotations()+"]");
    }

    @Override
    public void onBackPressed() {
        // Back button disabled
    }
}
