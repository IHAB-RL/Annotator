package com.tdg.android.annotator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import static com.tdg.android.annotator.R.id.editTextProband;

public class FragmentFinalise extends Fragment implements Communicator {

    private String LOG = "FragmentFinalise";
    private Button buttonFinalise;
    private ScrollView mScrollView;
    private RadioGroup radioLichtverhaeltnisse, radioLautstaerke, radioRaumbeschreibung,
        radioHoersituation;
    private int resLichtverhaeltnisse, resLautstaerke, resRaumbeschreibung, resHoersituation;
    private String stringFreiText;
    private EditText freiText;
    private Communicator communicator;
    private boolean wasTouched = false;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.tab_finalise, container, false);

        mScrollView = (ScrollView) mRootView.findViewById(R.id.scrollView);

        buttonFinalise = (Button) mRootView.findViewById(R.id.buttonFinalise);
        buttonFinalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImmersiveMode();
                if (wasTouched) {
                    gatherResults();
                    setWasTouched(false);
                    resetFields();
                }
            }
        });

        freiText = (EditText) mRootView.findViewById(R.id.freitext);
        stringFreiText = "";

        radioLichtverhaeltnisse = (RadioGroup) mRootView.findViewById(R.id.radioLichtverhaeltnisse);
        radioLautstaerke = (RadioGroup) mRootView.findViewById(R.id.radioLautstaerke);
        radioHoersituation = (RadioGroup) mRootView.findViewById(R.id.radioHoersituation);
        radioRaumbeschreibung = (RadioGroup) mRootView.findViewById(R.id.radioRaumbeschreibung);

        radioLichtverhaeltnisse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                resLichtverhaeltnisse = checkedId;
            }
        });
        radioLautstaerke.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                resLautstaerke = checkedId;
            }
        });
        radioRaumbeschreibung.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                resRaumbeschreibung = checkedId;
            }
        });
        radioHoersituation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                resHoersituation = checkedId;
            }
        });

        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect measureRect = new Rect(); //you should cache this, onGlobalLayout can get called often
                mRootView.getWindowVisibleDisplayFrame(measureRect);
                int keypadHeight = mRootView.getRootView().getWidth() - measureRect.right;

                if (keypadHeight > 0) {
                } else {
                    setImmersiveMode();
                }
            }
        });

        return mRootView;
    }

    public void onAttach(Activity activity){
        communicator = (Communicator)activity;
        super.onAttach(activity);
    }

    private void gatherResults() {
        getFreiText();
        String charakterisierung = resLichtverhaeltnisse + "; " + resLautstaerke + "; " +
                resRaumbeschreibung + "; " + resHoersituation;
        finishAnnotation(charakterisierung, stringFreiText);
    }

    private void getFreiText() {
        stringFreiText = formatText(freiText.getText().toString());
    }

    private String formatText(String string) {
        return string.replaceAll(System.lineSeparator(), " ");
    }

    public void setTouched(boolean touched) {
        wasTouched = touched;
    }

    private void resetFields() {
        radioHoersituation.clearCheck();
        radioLichtverhaeltnisse.clearCheck();
        radioRaumbeschreibung.clearCheck();
        radioLautstaerke.clearCheck();
        freiText.setText("");
    }

    /** Interface Methods **/

    public void finishAnnotation(String sCharakter, String sFreitext) {
        communicator.finishAnnotation(sCharakter, sFreitext);
    }

    public void beginAnnotation(String raterId, String subjectId, boolean isUebung) {}

    public void addAnnotation(int Code){}

    public void removeLastAnnotation(){}

    public void setWasTouched(boolean touched){
        communicator.setWasTouched(touched);
    }

    public void setImmersiveMode(){ communicator.setImmersiveMode(); }

    public void scrollUp() {
        mScrollView.smoothScrollTo(0,0);
    }
}