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
        mSectionsPagerAdapter.addFragment(new FragmentNewSession(), "New Session");
        mSectionsPagerAdapter.addFragment(new FragmentAnnotation(), "Annotation");
        mSectionsPagerAdapter.addFragment(new FragmentFinalise(), "Finalise");
        viewPager.setAdapter(mSectionsPagerAdapter);
    }

    public void beginAnnotation(String raterId, String subjectId) {
        annotationKeeper.setRaterID(raterId);
        annotationKeeper.setSubjectID(subjectId);
        annotationKeeper.setTimeStart();
    }

    public boolean finishAnnotation(String results, String freitext) {
        if (keepResults) {
            annotationKeeper.setAdditionalData(results);
            annotationKeeper.setFreiText(freitext);
            annotationKeeper.setTimeEnd();
            mViewPager.setCurrentItem(0);
            return fileWriter.saveToFile(this, annotationKeeper.getFileName(),
                    annotationKeeper.flushResults());
        } else {
            annotationKeeper.reset();
            Toast.makeText(this, R.string.toast_No_Data_kept, Toast.LENGTH_SHORT).show();
            mViewPager.setCurrentItem(0);
            return false;
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
}
