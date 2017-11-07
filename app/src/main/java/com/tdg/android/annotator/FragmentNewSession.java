package com.tdg.android.annotator;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.data;

public class FragmentNewSession extends Fragment implements Communicator{

    private String LOG = "FragmentNewSession";
    private Button buttonUebung, buttonMessung, buttonBeginn;
    private TextView textViewCodenummerProband, textViewCodenummerRater, textViewFehlerhaftProband,
        textViewFehlerhaftRater, textViewKeinButton;
    private EditText editTextProband, editTextRater;
    private String raterID = "", subjectID = "";
    private boolean isUebung = false, wasTouched = false;
    Communicator communicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_new_session, container, false);
        view.setId(R.id.fragment_newSession);

        buttonUebung = (Button) view.findViewById(R.id.buttonUebung);
        buttonMessung = (Button) view.findViewById(R.id.buttonMessung);
        buttonBeginn = (Button) view.findViewById(R.id.buttonBeginn);

        textViewCodenummerProband = (TextView) view.findViewById(R.id.textViewCodenummerProband);
        textViewCodenummerRater = (TextView) view.findViewById(R.id.textViewCodenummerRater);

        textViewKeinButton = (TextView) view.findViewById(R.id.textViewKeinButton);
        textViewFehlerhaftProband = (TextView) view.findViewById(R.id.textViewFehlerhaftProband);
        textViewFehlerhaftRater = (TextView) view.findViewById(R.id.textViewFehlerhaftRater);

        editTextProband = (EditText) view.findViewById(R.id.editTextProband);
        editTextProband.setText(subjectID);
        editTextRater = (EditText) view.findViewById(R.id.editTextRater);
        editTextRater.setText(raterID);

        buttonUebung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setImmersiveMode();

                if (!isUebung || !wasTouched) {
                    buttonUebung.getBackground().setColorFilter(ContextCompat.getColor(getActivity(),
                            R.color.colorButtonPressed), PorterDuff.Mode.OVERLAY);
                    buttonMessung.getBackground().clearColorFilter();
                    isUebung = true;
                    wasTouched = true;
                }
            }
        });

        buttonMessung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setImmersiveMode();

                if (isUebung || !wasTouched) {
                    buttonMessung.getBackground().setColorFilter(ContextCompat.getColor(getActivity(),
                            R.color.colorButtonPressed), PorterDuff.Mode.OVERLAY);
                    buttonUebung.getBackground().clearColorFilter();
                    isUebung = false;
                    wasTouched = true;
                }
            }
        });

        buttonBeginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setImmersiveMode();

                if (checkFields()) {
                    disableInputFields();
                    disableButtonUebung();
                    disableButtonMessung();
                    disableButtonStart();
                    setWasTouched(true);

                    subjectID = editTextProband.getText().toString();
                    raterID = editTextRater.getText().toString();

                    beginAnnotation(raterID, subjectID, isUebung);
                }
            }
        });

        return view;
    }

    public void onAttach(Activity activity){
        communicator = (Communicator)activity;
        super.onAttach(activity);
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

    private void disableButtonStart() {
        buttonBeginn.setEnabled(false);
    }

    private void enableButtonStart() {
        buttonBeginn.setEnabled(true);
    }

    private void disableButtonUebung() {
        buttonUebung.setEnabled(false);
    }

    private void enableButtonUebung() {
        buttonUebung.setEnabled(true);
    }

    private void disableButtonMessung() {
        buttonMessung.setEnabled(false);
    }

    private void enableButtonMessung() {
        buttonMessung.setEnabled(true);
    }

    private boolean checkFields() {

        boolean result = true;

        if (editTextRater.getText().toString().equals("")) {
            textViewFehlerhaftRater.setVisibility(View.VISIBLE);
            result = false;
        } else {
            textViewFehlerhaftRater.setVisibility(View.INVISIBLE);
        }

        if (editTextProband.getText().toString().equals("")) {
                textViewFehlerhaftProband.setVisibility(View.VISIBLE);
            result = false;
        } else {
            textViewFehlerhaftProband.setVisibility(View.INVISIBLE);
        }

        if (!wasTouched) {
            textViewKeinButton.setVisibility(View.VISIBLE);
            result = false;
        } else {
            textViewKeinButton.setVisibility(View.INVISIBLE);
        }

        return result;
    }

    private void resetInputFields() {
        editTextProband.setText("");
        editTextRater.setText("");
        raterID = "";
        subjectID = "";
    }

    public void reset() {

        isUebung = false;
        wasTouched = false;
        buttonMessung.getBackground().clearColorFilter();
        buttonUebung.getBackground().clearColorFilter();
        enableButtonStart();
        enableButtonMessung();
        enableButtonUebung();
        enableInputFields();
        resetInputFields();
    }

    /** Interface Methods **/

    public void beginAnnotation(String rId, String sId, boolean isuebung) {
        communicator.beginAnnotation(rId, sId, isuebung);
    }

    public void finishAnnotation(String string1, String string2) {}

    public void addAnnotation(int Code){}

    public void removeLastAnnotation(){}

    public void setWasTouched(boolean touched) {
        communicator.setWasTouched(touched);
    }

    public void setImmersiveMode(){ communicator.setImmersiveMode(); }

}
