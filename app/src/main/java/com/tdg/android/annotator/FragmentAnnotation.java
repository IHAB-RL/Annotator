package com.tdg.android.annotator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class FragmentAnnotation extends Fragment {

    private String LOG = "FragmentAnnotation";
    private Button mButtonAnnotation1, mButtonAnnotation2, mButtonAnnotation3, mButtonAnnotation4,
            mButtonAnnotation5, mButtonAnnotation6, mButtonAnnotation7, mButtonAnnotation8,
            mButtonRemove;
    private LinearLayout mLinearLayoutContainer;
    private int mScreenHeight, mScreenWidth;
    private int mPadding = 0, mMargin = 0;
    private int mButtonWidth, mButtonHeight;
    private int mButtonRemoveWidth, mButtonRemoveHeight = 120;
    private int mNumButtonsVertical = 4, mNumButtonsHorizontal = 2;
    private String codeButton1 = "F-t-F DP_N PR_N", codeButton2 = "F-t-F DP_N PR_D",
            codeButton3 = "F-t-F DP_D PR_N", codeButton4 = "F-t-F DP_D PR_D",
            codeButton5 = "Gruppe PR_N", codeButton6 = "Gruppe PR_D",
            codeButton7 = "NichtSprache PR_N", codeButton8 = "NichtSprache PR_D";

    private Resources mResources;
    DisplayMetrics mMetrics;
    private static int SCREEN_SIZE_HEIGHT;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_annotation, container, false);

        mResources = getActivity().getResources();
        mMetrics = mResources.getDisplayMetrics();
        SCREEN_SIZE_HEIGHT = mMetrics.heightPixels;

        mButtonAnnotation1 = (Button) view.findViewById(R.id.buttonAnnotation1);
        mButtonAnnotation2 = (Button) view.findViewById(R.id.buttonAnnotation2);
        mButtonAnnotation3 = (Button) view.findViewById(R.id.buttonAnnotation3);
        mButtonAnnotation4 = (Button) view.findViewById(R.id.buttonAnnotation4);
        mButtonAnnotation5 = (Button) view.findViewById(R.id.buttonAnnotation5);
        mButtonAnnotation6 = (Button) view.findViewById(R.id.buttonAnnotation6);
        mButtonAnnotation7 = (Button) view.findViewById(R.id.buttonAnnotation7);
        mButtonAnnotation8 = (Button) view.findViewById(R.id.buttonAnnotation8);
        mButtonRemove = (Button) view.findViewById(R.id.buttonRemove);
        mLinearLayoutContainer = (LinearLayout) view.findViewById(R.id.linearLayoutContainer);

        setButtonSizes();

        mButtonAnnotation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).addAnnotation(codeButton1);
            }
        });
        mButtonAnnotation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).addAnnotation(codeButton2);
            }
        });
        mButtonAnnotation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).addAnnotation(codeButton3);
            }
        });
        mButtonAnnotation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).addAnnotation(codeButton4);
            }
        });
        mButtonAnnotation5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).addAnnotation(codeButton5);
            }
        });
        mButtonAnnotation6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).addAnnotation(codeButton6);
            }
        });
        mButtonAnnotation7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).addAnnotation(codeButton7);
            }
        });
        mButtonAnnotation8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).addAnnotation(codeButton8);
            }
        });
        mButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).removeLastAnnotation();
            }
        });

        Log.i(LOG, "Width: "+mButtonRemoveWidth+", Height: "+mButtonRemoveHeight);

        return view;
    }

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
        mButtonAnnotation1.setText(codeButton1);

        LinearLayout.LayoutParams lpButtonAnno2 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno2.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation2.setLayoutParams(lpButtonAnno2);
        mButtonAnnotation2.setText(codeButton2);

        LinearLayout.LayoutParams lpButtonAnno3 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno3.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation3.setLayoutParams(lpButtonAnno3);
        mButtonAnnotation3.setText(codeButton3);

        LinearLayout.LayoutParams lpButtonAnno4 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno4.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation4.setLayoutParams(lpButtonAnno4);
        mButtonAnnotation4.setText(codeButton4);

        LinearLayout.LayoutParams lpButtonAnno5 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno5.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation5.setLayoutParams(lpButtonAnno5);
        mButtonAnnotation5.setText(codeButton5);

        LinearLayout.LayoutParams lpButtonAnno6 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno6.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation6.setLayoutParams(lpButtonAnno6);
        mButtonAnnotation6.setText(codeButton6);

        LinearLayout.LayoutParams lpButtonAnno7 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno7.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation7.setLayoutParams(lpButtonAnno7);
        mButtonAnnotation7.setText(codeButton7);

        LinearLayout.LayoutParams lpButtonAnno8 = new LinearLayout.LayoutParams(mButtonWidth, mButtonHeight);
        lpButtonAnno8.setMargins(mPadding, mPadding, mPadding, mPadding);
        mButtonAnnotation8.setLayoutParams(lpButtonAnno8);
        mButtonAnnotation8.setText(codeButton8);
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

}
