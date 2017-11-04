package com.tdg.android.annotator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FragmentStatistics extends Fragment {

    private String LOG = "FragmentStatistics";
    private int numCategories = 8;
    int[] histData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_statistics, container, false);

        //histData = ((MainActivity) getActivity()).getHistData();

        float sumHistData = 0.00000001f;
        float maxHistData = 0.00000001f;
        for (int iCat = 0; iCat < numCategories; iCat++) {
            sumHistData += histData[iCat];
            if (histData[iCat] > maxHistData) {
                maxHistData = histData[iCat];
            }
        }

        for (int it = 0; it < numCategories; it++) {
            Log.e(LOG, ""+(int) (histData[it]/maxHistData*100));
        }

        ProgressBar pb = (ProgressBar) view.findViewById(R.id.progressBar_1);
        pb.setProgress((int) (histData[0]/maxHistData*100));
        pb = (ProgressBar) view.findViewById(R.id.progressBar_2);
        pb.setProgress((int) (histData[1]/maxHistData*100));
        pb = (ProgressBar) view.findViewById(R.id.progressBar_3);
        pb.setProgress((int) (histData[2]/maxHistData*100));
        pb = (ProgressBar) view.findViewById(R.id.progressBar_4);
        pb.setProgress((int) (histData[3]/maxHistData*100));
        pb = (ProgressBar) view.findViewById(R.id.progressBar_5);
        pb.setProgress((int) (histData[4]/maxHistData*100));
        pb = (ProgressBar) view.findViewById(R.id.progressBar_6);
        pb.setProgress((int) (histData[5]/maxHistData*100));
        pb = (ProgressBar) view.findViewById(R.id.progressBar_7);
        pb.setProgress((int) (histData[6]/maxHistData*100));
        pb = (ProgressBar) view.findViewById(R.id.progressBar_8);
        pb.setProgress((int) (histData[7]/maxHistData*100));


        TextView tv = (TextView) view.findViewById(R.id.percentCategory_1);
        tv.setText("" + (int) (histData[0]*100f/sumHistData) + "%");
        tv = (TextView) view.findViewById(R.id.percentCategory_2);
        tv.setText("" + (int) (histData[1]*100f/sumHistData) + "%");
        tv = (TextView) view.findViewById(R.id.percentCategory_3);
        tv.setText("" + (int) (histData[2]*100f/sumHistData) + "%");
        tv = (TextView) view.findViewById(R.id.percentCategory_4);
        tv.setText("" + (int) (histData[3]*100f/sumHistData) + "%");
        tv = (TextView) view.findViewById(R.id.percentCategory_5);
        tv.setText("" + (int) (histData[4]*100f/sumHistData) + "%");
        tv = (TextView) view.findViewById(R.id.percentCategory_6);
        tv.setText("" + (int) (histData[5]*100f/sumHistData) + "%");
        tv = (TextView) view.findViewById(R.id.percentCategory_7);
        tv.setText("" + (int) (histData[6]*100f/sumHistData) + "%");
        tv = (TextView) view.findViewById(R.id.percentCategory_8);
        tv.setText("" + (int) (histData[7]*100f/sumHistData) + "%");

        return view;
    }

}
