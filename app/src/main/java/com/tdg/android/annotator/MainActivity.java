package com.tdg.android.annotator;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import static com.tdg.android.annotator.R.id.freitext;


public class MainActivity extends AppCompatActivity implements Communicator {

    private String LOG = "MainActivity";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public NoScrollViewPager mViewPager;
    private AnnotationKeeper annotationKeeper;
    private AnnotationStatistics annotationStatistics;
    private FileWriter fileWriter;
    private boolean wasTouched = false;
    private boolean permission_granted_WRITE_EXTERNAL = false;
    private Vibrator mVibration;
    private int mVibrationDuration_ms = 40;
    private final static int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 100, permissionCheck_Vibrate = 200;
    private boolean enableImmersive = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (NoScrollViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        annotationKeeper = new AnnotationKeeper(this);
        annotationStatistics = new AnnotationStatistics();
        fileWriter = new FileWriter();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        mVibration = ((Vibrator) getSystemService(VIBRATOR_SERVICE));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if ((position == 2) && (wasTouched)) {
                    annotationKeeper.setTimeEnd();
                    Log.i(LOG, "Endzeitpunkt wurde gesetzt.");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public void onResume() {
        setImmersive();
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permission_granted_WRITE_EXTERNAL = true;
                } else {
                    requestPermissions();
                }
            }
        }
    }

    public void requestPermissions() {
        ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
            MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
    }

    private void setImmersive() {
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && enableImmersive) {
            mViewPager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFragment(new FragmentNewSession(),
                getResources().getString(R.string.tab_New_Session));
        mSectionsPagerAdapter.addFragment(new FragmentAnnotation(),
                getResources().getString(R.string.tab_Annotation));
        mSectionsPagerAdapter.addFragment(new FragmentFinalise(),
                getResources().getString(R.string.tab_Finalise));
        mSectionsPagerAdapter.addFragment(new FragmentStatistics(),
                getResources().getString(R.string.tab_Statistics));
        viewPager.setAdapter(mSectionsPagerAdapter);
    }

    public int[] getHist() {
        return annotationStatistics.getHist(annotationKeeper.getListOfAnnotations());
    }

    @Override
    public void beginAnnotation(String raterId, String subjectId, boolean isUebung) {

        if (permission_granted_WRITE_EXTERNAL) {
            annotationKeeper.reset();
            annotationKeeper.setRaterID(raterId);
            annotationKeeper.setSubjectID(subjectId);
            annotationKeeper.setUebung(isUebung);
            annotationKeeper.setTimeStart();
            mViewPager.setCurrentItem(1);
            ((FragmentStatistics) mSectionsPagerAdapter.getFragmentFromPos(3)).updateColumns();
        } else {
            requestPermissions();
        }

        scrollUp();
    }

    @Override
    public void finishAnnotation(String results, String freitext) {
        if (wasTouched) {
            annotationKeeper.setAdditionalData(results);
            annotationKeeper.setFreiText(freitext);
            fileWriter.saveToFile(this, annotationKeeper.getFileName(),
                    annotationKeeper.flushResults());
            ((FragmentStatistics) mSectionsPagerAdapter.getFragmentFromPos(3)).updateColumns();
            annotationKeeper.reset();
            annotationStatistics.reset();
            ((FragmentNewSession) mSectionsPagerAdapter.getFragmentFromPos(0)).reset();
            mViewPager.setCurrentItem(0);
        }
    }

    @Override
    public void addAnnotation(int code) {
        if (wasTouched) {
            annotationKeeper.addAnnotation(code);
            vibrateFeedback();
            Log.i(LOG, "New annotation [" + annotationKeeper.getNumberOfAnnotations() + "]: " + code);
        }
    }

    @Override
    public void removeLastAnnotation() {
        if (wasTouched) {
            annotationKeeper.removeLastAnnotation();
            vibrateFeedback();
            Log.i(LOG, "Annotation removed [" + annotationKeeper.getNumberOfAnnotations() + "]");
        }
    }

    @Override
    public void onBackPressed() {
        // Back button disabled
    }

    @Override
    public void setWasTouched(boolean touched) {
        wasTouched = touched;
        ((FragmentFinalise) mSectionsPagerAdapter.getFragmentFromPos(2)).setTouched(wasTouched);
    }

    @Override
    public void setImmersiveMode() {
        setImmersive();
    }

    private void vibrateFeedback() {
        mVibration.vibrate(mVibrationDuration_ms);
    }

    public void scrollUp() {
        ((FragmentFinalise) mSectionsPagerAdapter.getFragmentFromPos(2)).scrollUp();
    }
}