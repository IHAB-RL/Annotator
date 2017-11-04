package com.tdg.android.annotator;

import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import static android.R.attr.data;


public class MainActivity extends AppCompatActivity implements Communicator{

    private String LOG = "MainActivity";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public NoScrollViewPager mViewPager;
    private AnnotationKeeper annotationKeeper;
    private FileWriter fileWriter;
    Communicator communicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (NoScrollViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        annotationKeeper = new AnnotationKeeper();
        fileWriter = new FileWriter();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    //@Override public void eventReset() {
        //FragmentManager manager = getFragmentManager();
        //FragmentNewSession fragmentNewSession = (FragmentNewSession) getFragmentManager().findFragmentById(R.id.fragment_newSession)
        //fragmentNewSession.reset();
    //}



    /*public void eventReset(){ // Code that defines the method

        }*/

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

    @Override
    public void beginAnnotation(String raterId, String subjectId, boolean isUebung) {
        annotationKeeper.reset();
        annotationKeeper.setRaterID(raterId);
        annotationKeeper.setSubjectID(subjectId);
        annotationKeeper.setUebung(isUebung);
        annotationKeeper.setTimeStart();
        mViewPager.setCurrentItem(1);

    }

    @Override
    public void finishAnnotation(String results, String freitext) {
        annotationKeeper.setAdditionalData(results);
        annotationKeeper.setFreiText(freitext);
        annotationKeeper.setTimeEnd();
        fileWriter.saveToFile(this, annotationKeeper.getFileName(),
                annotationKeeper.flushResults());
        annotationKeeper.reset();





        mViewPager.setCurrentItem(0);
    }

    @Override
    public void addAnnotation(int code) {
        annotationKeeper.addAnnotation(code);
        Log.i(LOG, "New annotation ["+annotationKeeper.getNumberOfAnnotations()+"]: "+code);
    }

    @Override
    public void removeLastAnnotation() {
        annotationKeeper.removeLastAnnotation();
        Log.i(LOG, "Annotation removed ["+annotationKeeper.getNumberOfAnnotations()+"]");
    }

    @Override
    public void onBackPressed() {
        // Back button disabled
    }



    /*
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
    */
}
