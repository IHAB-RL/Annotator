package com.tdg.android.annotator;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Collections;

import static android.R.id.button1;


public class FragmentFinalise extends Fragment implements Communicator {

    private String LOG = "FragmentFinalise";
    private Button buttonFinalise;
    private RadioGroup radioLichtverhaeltnisse, radioLautstaerke, radioRaumbeschreibung,
        radioHoersituation;
    private int resLichtverhaeltnisse, resLautstaerke, resRaumbeschreibung, resHoersituation;
    private String stringFreiText;
    private EditText freiText;
    private Communicator communicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_finalise, container, false);

        buttonFinalise = (Button) view.findViewById(R.id.buttonFinalise);
        buttonFinalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gatherResults();
            }
        });

        freiText = (EditText) view.findViewById(R.id.freitext);
        stringFreiText = "";

        radioLichtverhaeltnisse = (RadioGroup) view.findViewById(R.id.radioLichtverhaeltnisse);
        radioLautstaerke = (RadioGroup) view.findViewById(R.id.radioLautstaerke);
        radioHoersituation = (RadioGroup) view.findViewById(R.id.radioHoersituation);
        radioRaumbeschreibung = (RadioGroup) view.findViewById(R.id.radioRaumbeschreibung);

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

        return view;
    }

    public void onAttach(Activity activity){
        communicator = (Communicator)activity;
        super.onAttach(activity);
    }

    private void gatherResults() {
        getFreiText();
        String charakterisierung = resLichtverhaeltnisse+", "+resLautstaerke+", "+
                resRaumbeschreibung+", "+resHoersituation;

        finishAnnotation(charakterisierung, stringFreiText);
        //((MainActivity) getActivity()).finishAnnotation(charakterisierung, stringFreiText);
    }

    private void getFreiText() {
        stringFreiText = formatText(freiText.getText().toString());
    }

    private String formatText(String string) {
        return string.replaceAll(System.lineSeparator(), " ");
    }

    /** Interface Methods **/

    public void finishAnnotation(String sCharakter, String sFreitext) {
        communicator.finishAnnotation(sCharakter, sFreitext);
    }

    public void beginAnnotation(String raterId, String subjectId, boolean isUebung) {}

    public void addAnnotation(int Code){}

    public void removeLastAnnotation(){}
}
