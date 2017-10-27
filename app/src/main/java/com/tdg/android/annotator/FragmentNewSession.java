package com.tdg.android.annotator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.tdg.android.annotator.MainActivity.mViewPager;


public class FragmentNewSession extends Fragment {

    private String LOG = "FragmentNewSession";
    private Button buttonUebung, buttonMessung, buttonBeginn;
    private TextView textViewCodenummerProband, textViewCodenummerRater;
    private EditText editTextProband, editTextRater;
    private String raterID = "", subjectID = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_new_session, container, false);

        buttonUebung = (Button) view.findViewById(R.id.buttonUebung);
        buttonMessung = (Button) view.findViewById(R.id.buttonMessung);
        buttonBeginn = (Button) view.findViewById(R.id.buttonBeginn);

        textViewCodenummerProband = (TextView) view.findViewById(R.id.textViewCodenummerProband);
        textViewCodenummerRater = (TextView) view.findViewById(R.id.textViewCodenummerRater);

        editTextProband = (EditText) view.findViewById(R.id.editTextProband);
        editTextProband.setText(subjectID);
        editTextRater = (EditText) view.findViewById(R.id.editTextRater);
        editTextRater.setText(raterID);

        buttonUebung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableAnnotation();
            }
        });

        buttonMessung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableAnnotation();
            }
        });

        buttonBeginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                subjectID = editTextProband.getText().toString();
                raterID = editTextRater.getText().toString();

                ((MainActivity) getActivity()).beginAnnotation(raterID, subjectID);
                ((MainActivity) getActivity()).mViewPager.setCurrentItem(1);
            }
        });

        return view;
    }

    private void disableAnnotation() {
        editTextRater.setEnabled(false);
        editTextRater.setBackgroundColor(Color.LTGRAY);
        editTextProband.setEnabled(false);
        editTextProband.setBackgroundColor(Color.LTGRAY);
        textViewCodenummerRater.setEnabled(false);
        textViewCodenummerProband.setEnabled(false);
    }

    private void enableAnnotation() {
        editTextRater.setEnabled(true);
        editTextRater.setBackgroundColor(Color.WHITE);
        editTextProband.setEnabled(true);
        editTextProband.setBackgroundColor(Color.WHITE);
        textViewCodenummerRater.setEnabled(true);
        textViewCodenummerProband.setEnabled(true);
    }

}
