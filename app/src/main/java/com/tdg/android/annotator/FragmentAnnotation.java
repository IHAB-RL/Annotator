package com.tdg.android.annotator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class FragmentAnnotation extends Fragment implements Communicator {

    private String LOG = "FragmentAnnotation";
    private RelativeLayout mButtonAnnotation1, mButtonAnnotation2, mButtonAnnotation3, mButtonAnnotation4,
            mButtonAnnotation5, mButtonAnnotation6, mButtonAnnotation7, mButtonAnnotation8;
    private Button mButtonRemove;
    private View mainView;
    private Communicator communicator;
    private static int CODE_1 = 1, CODE_2 = 2, CODE_3 = 3, CODE_4 = 4,
            CODE_5 = 5, CODE_6 = 6, CODE_7 = 7, CODE_8 = 8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.tab_annotation, container, false);

        mButtonAnnotation1 = (RelativeLayout) mainView.findViewById(R.id.buttonAnnotation1);
        mButtonAnnotation2 = (RelativeLayout) mainView.findViewById(R.id.buttonAnnotation2);
        mButtonAnnotation3 = (RelativeLayout) mainView.findViewById(R.id.buttonAnnotation3);
        mButtonAnnotation4 = (RelativeLayout) mainView.findViewById(R.id.buttonAnnotation4);
        mButtonAnnotation5 = (RelativeLayout) mainView.findViewById(R.id.buttonAnnotation5);
        mButtonAnnotation6 = (RelativeLayout) mainView.findViewById(R.id.buttonAnnotation6);
        mButtonAnnotation7 = (RelativeLayout) mainView.findViewById(R.id.buttonAnnotation7);
        mButtonAnnotation8 = (RelativeLayout) mainView.findViewById(R.id.buttonAnnotation8);
        mButtonRemove = (Button) mainView.findViewById(R.id.buttonRemove);

        mButtonAnnotation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_1);
            }
        });
        mButtonAnnotation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_2);
            }
        });
        mButtonAnnotation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_3);
            }
        });
        mButtonAnnotation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_4);
            }
        });
        mButtonAnnotation5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_5);
            }
        });
        mButtonAnnotation6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_6);
            }
        });
        mButtonAnnotation7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_7);
            }
        });
        mButtonAnnotation8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnotation(CODE_8);
            }
        });
        mButtonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLastAnnotation();
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

    public void onAttach(Activity activity) {
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

    /**
     * Interface Methods
     **/

    public void beginAnnotation(String rId, String sId, boolean isuebung) {
    }

    public void finishAnnotation(String string1, String string2) {
    }

    public void addAnnotation(int Code) {
        communicator.addAnnotation(Code);
    }

    public void removeLastAnnotation() {
        communicator.removeLastAnnotation();
    }

    public void setWasTouched(boolean touched) {}

    public void setImmersiveMode(){}
}
