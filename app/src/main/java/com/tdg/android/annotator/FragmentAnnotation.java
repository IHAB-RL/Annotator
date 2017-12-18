package com.tdg.android.annotator;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.Context.VIBRATOR_SERVICE;

public class FragmentAnnotation extends Fragment implements Communicator {

    private String LOG = "FragmentAnnotation";
    private RelativeLayout mButtonAnnotation1, mButtonAnnotation2, mButtonAnnotation3, mButtonAnnotation4,
            mButtonAnnotation5, mButtonAnnotation6, mButtonAnnotation7, mButtonAnnotation8;
    private TextView mAnnoText1, mAnnoText2, mAnnoText3, mAnnoText4, mAnnoText5, mAnnoText6,
            mAnnoText7, mAnnoText8;
    private Button mButtonRemove;
    private View mainView;
    private Communicator communicator;
    private static int CODE_1 = 1, CODE_2 = 2, CODE_3 = 3, CODE_4 = 4,
            CODE_5 = 5, CODE_6 = 6, CODE_7 = 7, CODE_8 = 8;
    private boolean showAnnoText = true;

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
        mAnnoText1 = (TextView) mainView.findViewById(R.id.annoText1);
        mAnnoText2 = (TextView) mainView.findViewById(R.id.annoText2);
        mAnnoText3 = (TextView) mainView.findViewById(R.id.annoText3);
        mAnnoText4 = (TextView) mainView.findViewById(R.id.annoText4);
        mAnnoText5 = (TextView) mainView.findViewById(R.id.annoText5);
        mAnnoText6 = (TextView) mainView.findViewById(R.id.annoText6);
        mAnnoText7 = (TextView) mainView.findViewById(R.id.annoText7);
        mAnnoText8 = (TextView) mainView.findViewById(R.id.annoText8);

        if (showAnnoText) {
            mAnnoText1.setText(R.string.category_1);
            mAnnoText2.setText(R.string.category_2);
            mAnnoText3.setText(R.string.category_3);
            mAnnoText4.setText(R.string.category_4);
            mAnnoText5.setText(R.string.category_5);
            mAnnoText6.setText(R.string.category_6);
            mAnnoText7.setText(R.string.category_7);
            mAnnoText8.setText(R.string.category_8);
        } else {
            mAnnoText1.setText("");
            mAnnoText2.setText("");
            mAnnoText3.setText("");
            mAnnoText4.setText("");
            mAnnoText5.setText("");
            mAnnoText6.setText("");
            mAnnoText7.setText("");
            mAnnoText8.setText("");
        }

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
        setImmersiveMode();
        super.onResume();
    }

    public void onAttach(Activity activity) {
        communicator = (Communicator) activity;
        setImmersiveMode();
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

    public void setImmersiveMode() { communicator.setImmersiveMode(); }

    public void scrollUp() {};
}
