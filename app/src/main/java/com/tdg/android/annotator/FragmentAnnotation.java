package com.tdg.android.annotator;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.os.Build.VERSION_CODES.M;

public class FragmentAnnotation extends Fragment implements Communicator{

    private String LOG = "FragmentAnnotation";
    private Button mButtonAnnotation1, mButtonAnnotation2, mButtonAnnotation3, mButtonAnnotation4,
            mButtonAnnotation5, mButtonAnnotation6, mButtonAnnotation7, mButtonAnnotation8,
            mButtonRemove;
    //private LinearLayout mLinearLayoutContainer;
    private View mainView;
    private Communicator communicator;
    //private int mScreenHeight, mScreenWidth;
    //private int mPadding = 0, mMargin = 0;
    //private int mButtonWidth, mButtonHeight;
    //private int mButtonRemoveWidth, mButtonRemoveHeight = 120;
    //private int mNumButtonsVertical = 4, mNumButtonsHorizontal = 2;
    private static int CODE_1 = 1, CODE_2 = 2, CODE_3 = 3, CODE_4 = 4,
            CODE_5 = 5, CODE_6 = 6, CODE_7 = 7, CODE_8 = 8;
    //private Resources mResources;
    //DisplayMetrics mMetrics;
    //private static int SCREEN_SIZE_HEIGHT;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.tab_annotation, container, false);


        //mResources = getActivity().getResources();
        //mMetrics = mResources.getDisplayMetrics();
        //SCREEN_SIZE_HEIGHT = mMetrics.heightPixels;

        mButtonAnnotation1 = (Button) mainView.findViewById(R.id.buttonAnnotation1);
        mButtonAnnotation2 = (Button) mainView.findViewById(R.id.buttonAnnotation2);
        mButtonAnnotation3 = (Button) mainView.findViewById(R.id.buttonAnnotation3);
        mButtonAnnotation4 = (Button) mainView.findViewById(R.id.buttonAnnotation4);
        mButtonAnnotation5 = (Button) mainView.findViewById(R.id.buttonAnnotation5);
        mButtonAnnotation6 = (Button) mainView.findViewById(R.id.buttonAnnotation6);
        mButtonAnnotation7 = (Button) mainView.findViewById(R.id.buttonAnnotation7);
        mButtonAnnotation8 = (Button) mainView.findViewById(R.id.buttonAnnotation8);
        mButtonRemove = (Button) mainView.findViewById(R.id.buttonRemove);
        //mLinearLayoutContainer = (LinearLayout) mainView.findViewById(R.id.linearLayoutContainer);

        //setButtonSizes();

        mButtonAnnotation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_1);
                //((MainActivity) getActivity()).addAnnotation(CODE_1);
            }
        });
        mButtonAnnotation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_2);
                //((MainActivity) getActivity()).addAnnotation(CODE_2);
            }
        });
        mButtonAnnotation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_3);
                //((MainActivity) getActivity()).addAnnotation(CODE_3);
            }
        });
        mButtonAnnotation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_4);
                //((MainActivity) getActivity()).addAnnotation(CODE_4);
            }
        });
        mButtonAnnotation5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_5);
                //((MainActivity) getActivity()).addAnnotation(CODE_5);
            }
        });
        mButtonAnnotation6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_6);
                //((MainActivity) getActivity()).addAnnotation(CODE_6);
            }
        });
        mButtonAnnotation7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_7);
                //((MainActivity) getActivity()).addAnnotation(CODE_7);
            }
        });
        mButtonAnnotation8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_8);
                //((MainActivity) getActivity()).addAnnotation(CODE_8);
            }
        });
        mButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLastAnnotation();
                //((MainActivity) getActivity()).removeLastAnnotation();
            }
        });

        return mainView;
    }

    @Override
    public void onResume() {
        mainView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onResume();
    }

    public void onAttach(Activity activity){
        communicator = (Communicator)activity;
        super.onAttach(activity);
    }

    /** Interface Methods **/

    public void beginAnnotation(String rId, String sId, boolean isuebung) {}

    public void finishAnnotation(String string1, String string2) {}

    public void addAnnotation(int Code){
        communicator.addAnnotation(Code);
    }

    public void removeLastAnnotation(){
        communicator.removeLastAnnotation();
    }

    /*
    private void getDimensions() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mScreenWidth = size.x;
        mScreenHeight = size.y - 2*getToolBarHeight();
    }

    public int getToolBarHeight() {
        int[] attrs = new int[] {R.attr.actionBarSize};
        TypedArray ta = getContext().obtainStyledAttributes(attrs);
        int toolBarHeight = ta.getDimensionPixelSize(0, -1);
        ta.recycle();
        return toolBarHeight;
    }

    private void setButtonSizes() {

        getDimensions();
        mButtonHeight = (getUsableHeight()-mButtonRemoveHeight)/mNumButtonsVertical + 10;
        mButtonWidth = (mScreenWidth-2*mMargin-(mNumButtonsHorizontal-1))/mNumButtonsHorizontal;
        mButtonRemoveWidth = mScreenWidth-2*mMargin;

        LinearLayout.LayoutParams lpButtonAnno1 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno1.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation1.setLayoutParams(lpButtonAnno1);

        LinearLayout.LayoutParams lpButtonAnno2 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno2.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation2.setLayoutParams(lpButtonAnno2);

        LinearLayout.LayoutParams lpButtonAnno3 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno3.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation3.setLayoutParams(lpButtonAnno3);

        LinearLayout.LayoutParams lpButtonAnno4 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno4.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation4.setLayoutParams(lpButtonAnno4);

        LinearLayout.LayoutParams lpButtonAnno5 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno5.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation5.setLayoutParams(lpButtonAnno5);

        LinearLayout.LayoutParams lpButtonAnno6 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno6.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation6.setLayoutParams(lpButtonAnno6);

        LinearLayout.LayoutParams lpButtonAnno7 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno7.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation7.setLayoutParams(lpButtonAnno7);

        LinearLayout.LayoutParams lpButtonAnno8 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno8.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation8.setLayoutParams(lpButtonAnno8);
    }

    public int getUsableHeight() {
        return getScreenHeight() -
                getStatusBarHeight() -
                (int) getActivity().getResources().getDimension(R.dimen.toolBarHeightWithPadding) -
                (int) getActivity().getResources().getDimension(R.dimen.actionBarHeight);
    }

    public int getStatusBarHeight() {

        return (int) (24 * mMetrics.density);
    }

    public static int getScreenHeight() {
        return SCREEN_SIZE_HEIGHT;
    }

    */

}
