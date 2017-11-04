package com.tdg.android.annotator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentNewSession extends Fragment {

    private String LOG = "FragmentNewSession";
    private Button buttonUebung, buttonMessung, buttonBeginn;
    private TextView textViewCodenummerProband, textViewCodenummerRater, textViewFehlerhaftProband,
        textViewFehlerhaftRater;
    private EditText editTextProband, editTextRater;
    private String raterID = "", subjectID = "";
    private boolean keepResults = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_new_session, container, false);

        buttonUebung = (Button) view.findViewById(R.id.buttonUebung);
        buttonMessung = (Button) view.findViewById(R.id.buttonMessung);
        buttonBeginn = (Button) view.findViewById(R.id.buttonBeginn);

        textViewCodenummerProband = (TextView) view.findViewById(R.id.textViewCodenummerProband);
        textViewCodenummerRater = (TextView) view.findViewById(R.id.textViewCodenummerRater);

        textViewFehlerhaftProband = (TextView) view.findViewById(R.id.textViewFehlerhaftProband);
        textViewFehlerhaftRater = (TextView) view.findViewById(R.id.textViewFehlerhaftRater);

        editTextProband = (EditText) view.findViewById(R.id.editTextProband);
        editTextProband.setText(subjectID);
        editTextRater = (EditText) view.findViewById(R.id.editTextRater);
        editTextRater.setText(raterID);

        buttonUebung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keepResults = false;
                disableInputFields();
                textViewFehlerhaftProband.setVisibility(View.INVISIBLE);
                textViewFehlerhaftRater.setVisibility(View.INVISIBLE);
                ((MainActivity) getActivity()).keepResults(false);
            }
        });

        buttonMessung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keepResults = true;
                enableInputFields();
                enableStartButton();
                ((MainActivity) getActivity()).keepResults(true);
            }
        });

        buttonBeginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                subjectID = editTextProband.getText().toString();
                raterID = editTextRater.getText().toString();

                if (!checkFields() && keepResults) {
                    Toast.makeText(getActivity(), R.string.toast_PleaseFillForms,
                            Toast.LENGTH_SHORT).show();
                } else {
                    disableInputFields();
                    disableStartButton();
                    if (keepResults) {
                        disableExperimentButton();
                    }
                    //disableTestButton();
                    ((MainActivity) getActivity()).beginAnnotation(raterID, subjectID);
                    ((MainActivity) getActivity()).mViewPager.setCurrentItem(1);
                }
            }
        });

        return view;
    }

    private void disableInputFields() {
        editTextRater.setEnabled(false);
        editTextRater.setBackgroundColor(Color.LTGRAY);
        editTextProband.setEnabled(false);
        editTextProband.setBackgroundColor(Color.LTGRAY);
        textViewCodenummerRater.setEnabled(false);
        textViewCodenummerProband.setEnabled(false);
    }

    private void enableInputFields() {
        editTextRater.setEnabled(true);
        editTextRater.setBackgroundColor(Color.WHITE);
        editTextProband.setEnabled(true);
        editTextProband.setBackgroundColor(Color.WHITE);
        textViewCodenummerRater.setEnabled(true);
        textViewCodenummerProband.setEnabled(true);
    }

    private void disableStartButton() {
        buttonBeginn.setEnabled(false);
    }

    private void enableStartButton() {
        buttonBeginn.setEnabled(true);
    }

    private void disableTestButton() {
        buttonUebung.setEnabled(false);
    }

    private void enableTestButton() {
        buttonUebung.setEnabled(true);
    }

    private void disableExperimentButton() {
        buttonMessung.setEnabled(false);
    }

    private void enableExperimentButton() {
        buttonMessung.setEnabled(true);
    }

    private boolean checkFields() {

        boolean result = true;

        if (editTextRater.getText().toString().equals("")) {
            if (keepResults) {
                textViewFehlerhaftRater.setVisibility(View.VISIBLE);
            }
            result = false;
        } else {
            textViewFehlerhaftRater.setVisibility(View.INVISIBLE);
        }

        if (editTextProband.getText().toString().equals("")) {
            if (keepResults) {
                textViewFehlerhaftProband.setVisibility(View.VISIBLE);
            }
            result = false;
        } else {
            textViewFehlerhaftProband.setVisibility(View.INVISIBLE);
        }

        return result;
    }


}
