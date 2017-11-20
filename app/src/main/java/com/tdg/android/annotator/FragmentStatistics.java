package com.tdg.android.annotator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentStatistics extends Fragment {

    private String LOG = "FragmentStatistics";
    private int sumHist = 0;
    int[] histData;
    float[] histFraction;
    private TextView tvCat1, tvCat2, tvCat3, tvCat4, tvCat5, tvCat6, tvCat7, tvCat8, tvInsgesamt;
    private View regCol1, progCol1, regCol2, progCol2, regCol3, progCol3, regCol4, progCol4,
        regCol5, progCol5, regCol6, progCol6, regCol7, progCol7, regCol8, progCol8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_new_statistics, container, false);

        tvCat1 = (TextView) view.findViewById(R.id.stat_label_perc1);
        tvCat2 = (TextView) view.findViewById(R.id.stat_label_perc2);
        tvCat3 = (TextView) view.findViewById(R.id.stat_label_perc3);
        tvCat4 = (TextView) view.findViewById(R.id.stat_label_perc4);
        tvCat5 = (TextView) view.findViewById(R.id.stat_label_perc5);
        tvCat6 = (TextView) view.findViewById(R.id.stat_label_perc6);
        tvCat7 = (TextView) view.findViewById(R.id.stat_label_perc7);
        tvCat8 = (TextView) view.findViewById(R.id.stat_label_perc8);
        tvInsgesamt = (TextView) view.findViewById(R.id.textInsgesamt);

        regCol1 = view.findViewById(R.id.statistics_reg_col1);
        regCol2 = view.findViewById(R.id.statistics_reg_col2);
        regCol3 = view.findViewById(R.id.statistics_reg_col3);
        regCol4 = view.findViewById(R.id.statistics_reg_col4);
        regCol5 = view.findViewById(R.id.statistics_reg_col5);
        regCol6 = view.findViewById(R.id.statistics_reg_col6);
        regCol7 = view.findViewById(R.id.statistics_reg_col7);
        regCol8 = view.findViewById(R.id.statistics_reg_col8);

        progCol1 = view.findViewById(R.id.statistics_prog_col1);
        progCol2 = view.findViewById(R.id.statistics_prog_col2);
        progCol3 = view.findViewById(R.id.statistics_prog_col3);
        progCol4 = view.findViewById(R.id.statistics_prog_col4);
        progCol5 = view.findViewById(R.id.statistics_prog_col5);
        progCol6 = view.findViewById(R.id.statistics_prog_col6);
        progCol7 = view.findViewById(R.id.statistics_prog_col7);
        progCol8 = view.findViewById(R.id.statistics_prog_col8);

        resetColumns();

        return view;
    }

    public void updateColumns() {
        getHist();
        setFracs();
        setColumns();
    }

    public void resetColumns() {
        histData = new int[] {0,0,0,0,0,0,0,0};
        histFraction = new float[] {0f,0f,0f,0f,0f,0f,0f,0f};
        setFracs();
        setColumns();
    }

    private void setColumns() {

        progCol1.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1-histFraction[0]));
        progCol2.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1-histFraction[1]));
        progCol3.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1-histFraction[2]));
        progCol4.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1-histFraction[3]));
        progCol5.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1-histFraction[4]));
        progCol6.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1-histFraction[5]));
        progCol7.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1-histFraction[6]));
        progCol8.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 1-histFraction[7]));

        regCol1.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, histFraction[0]));
        regCol2.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, histFraction[1]));
        regCol3.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, histFraction[2]));
        regCol4.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, histFraction[3]));
        regCol5.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, histFraction[4]));
        regCol6.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, histFraction[5]));
        regCol7.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, histFraction[6]));
        regCol8.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT, histFraction[7]));

        tvCat1.setText(""+histData[0]);
        tvCat2.setText(""+histData[1]);
        tvCat3.setText(""+histData[2]);
        tvCat4.setText(""+histData[3]);
        tvCat5.setText(""+histData[4]);
        tvCat6.setText(""+histData[5]);
        tvCat7.setText(""+histData[6]);
        tvCat8.setText(""+histData[7]);
        tvInsgesamt.setText(getResources().getString(R.string.menu_insgesamt)+": "+sumHist);
    }

    private void getHist() {
        histData = ((MainActivity) getActivity()).getHist();
    }

    public void setFracs() {
        int maxHist = 0;
        for (int data : histData) {
            if (data > maxHist) {
                maxHist = data;
            }
            sumHist += data;
        }

        for (int iData = 0; iData < histData.length; iData++) {
            histFraction[iData] = (float) histData[iData]/maxHist;
        }
    }
}
