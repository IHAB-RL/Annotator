package com.tdg.android.annotator;

import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String LOG = "MainActivity";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public ViewPager mViewPager;
    private AnnotationKeeper annotationKeeper;
    FileWriter fileWriter;
    private boolean keepResults = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        annotationKeeper = new AnnotationKeeper();
        // Just in case someone forgets to hit "Begin"
        annotationKeeper.setTimeStart();
        fileWriter = new FileWriter();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void setupViewPager(ViewPager viewPager) {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFragment(new FragmentNewSession(),
                getResources().getString(R.string.tab_New_Session));
        mSectionsPagerAdapter.addFragment(new FragmentAnnotation(),
                getResources().getString(R.string.tab_Annotation));
        mSectionsPagerAdapter.addFragment(new FragmentFinalise(),
                getResources().getString(R.string.tab_Finalise));
        viewPager.setAdapter(mSectionsPagerAdapter);
    }

    public void beginAnnotation(String raterId, String subjectId) {
        annotationKeeper.reset();
        annotationKeeper.setRaterID(raterId);
        annotationKeeper.setSubjectID(subjectId);
        annotationKeeper.setTimeStart();
    }

    public void finishAnnotation(String results, String freitext) {
        if (keepResults) {
            annotationKeeper.setAdditionalData(results);
            annotationKeeper.setFreiText(freitext);
            annotationKeeper.setTimeEnd();
            mViewPager.setCurrentItem(0);
            fileWriter.saveToFile(this, annotationKeeper.getFileName(),
                    annotationKeeper.flushResults());
            buildStatistics();
            annotationKeeper.reset();
        } else {
            Toast.makeText(this, R.string.toast_No_Data_kept, Toast.LENGTH_SHORT).show();
            //mViewPager.setCurrentItem(0);
            buildStatistics();
            annotationKeeper.reset();
        }
    }

    public void addAnnotation(int code) {
        annotationKeeper.addAnnotation(code);
        Log.i(LOG, "New annotation ["+annotationKeeper.getNumberOfAnnotations()+"]: "+code);
    }

    public void removeLastAnnotation() {
        annotationKeeper.removeLastAnnotation();
        Log.i(LOG, "Annotation removed ["+annotationKeeper.getNumberOfAnnotations()+"]");
    }

    public void keepResults(boolean keep) {
        keepResults = keep;
    }

    @Override
    public void onBackPressed() {
        // Back button disabled
    }

    private void buildStatistics() {
        if (mSectionsPagerAdapter.getCount() > 3) {
            mViewPager.setAdapter(null);
            mSectionsPagerAdapter.removeFragment(3);
            mSectionsPagerAdapter.addFragment(new FragmentStatistics(),
                    getResources().getString(R.string.tab_Statistics));
            mViewPager.setOffscreenPageLimit(3);
            mSectionsPagerAdapter.notifyDataSetChanged();
            mViewPager.setAdapter(mSectionsPagerAdapter);
        } else {
            mViewPager.setAdapter(null);
            mSectionsPagerAdapter.addFragment(new FragmentStatistics(),
                    getResources().getString(R.string.tab_Statistics));
            mViewPager.setOffscreenPageLimit(3);
            mSectionsPagerAdapter.notifyDataSetChanged();
            mViewPager.setAdapter(mSectionsPagerAdapter);
        }
    }

    public int[] getHistData() {
        AnnotationStatistics Statistics = new AnnotationStatistics();
        return Statistics.getHist(annotationKeeper.getListOfAnnotations());
    }
}
