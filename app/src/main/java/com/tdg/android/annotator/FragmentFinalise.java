package com.tdg.android.annotator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static android.R.id.button1;


public class FragmentFinalise extends Fragment {

    private String LOG = "FragmentFinalise";
    private Button buttonFinalise;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_finalise, container, false);

        buttonFinalise = (Button) view.findViewById(R.id.buttonFinalise);

        buttonFinalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Finalised.", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
