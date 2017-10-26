package com.tdg.android.annotator;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.R.attr.button;
import static android.R.attr.width;
import static android.R.id.button1;


public class FragmentAnnotation extends Fragment {

    private String LOG = "FragmentAnnotation";
    private Button mButtonAnnotation1, mButtonAnnotation2, mButtonAnnotation3, mButtonAnnotation4,
            mButtonAnnotation5, mButtonAnnotation6, mButtonAnnotation7, mButtonAnnotation8;
    private LinearLayout mLinearLayoutMain;
    private int mScreenHeight, mScreenWidth;
    private int mPadding = 4, mMargin = 4;
    private int mButtonWidth, mButtonHeight;
    private int mNumButtonsVertical = 4, mNumButtonsHorizontal = 2;
    private DisplayMetrics mMetrics;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_annotation, container, false);
        mMetrics = new DisplayMetrics();

        getDimensions();
        mButtonHeight = (mScreenHeight-2*mMargin-(mNumButtonsVertical-1)*mPadding)/mNumButtonsVertical;
        mButtonWidth = (mScreenWidth-2*mMargin-(mNumButtonsHorizontal-1))/mNumButtonsHorizontal;

        mButtonAnnotation1 = (Button) view.findViewById(R.id.buttonAnnotation1);
        mButtonAnnotation2 = (Button) view.findViewById(R.id.buttonAnnotation2);
        mButtonAnnotation3 = (Button) view.findViewById(R.id.buttonAnnotation3);
        mButtonAnnotation4 = (Button) view.findViewById(R.id.buttonAnnotation4);
        mButtonAnnotation5 = (Button) view.findViewById(R.id.buttonAnnotation5);
        mButtonAnnotation6 = (Button) view.findViewById(R.id.buttonAnnotation6);
        mButtonAnnotation7 = (Button) view.findViewById(R.id.buttonAnnotation7);
        mButtonAnnotation8 = (Button) view.findViewById(R.id.buttonAnnotation8);
        mLinearLayoutMain = (LinearLayout) view.findViewById(R.id.linearLayoutAnnotationMain);

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


}
