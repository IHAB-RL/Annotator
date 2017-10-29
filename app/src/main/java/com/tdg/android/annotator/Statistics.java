package com.tdg.android.annotator;

import java.util.ArrayList;

import static android.R.attr.value;

/**
 * Created by ulrikkowalk on 29.10.17.
 */

public class Statistics {

    ArrayList<Integer> histogram = new ArrayList<>();

    public Statistics() {}

    public ArrayList<Integer> hist(ArrayList<Integer> listOfElements) {
        ArrayList<Integer> histogram = new ArrayList<>();

        for (Integer value : listOfElements) {
            if (!isElement(value)) {
                histogram.add(value);
            }
        }

        return histogram;
    }

    private boolean isElement(int value) {
        boolean result = false;
        for (int iItem = 0; iItem < histogram.size(); iItem++) {
            if (value == histogram.get(iItem)) {
                return true;
            }
        }
        return false;
    }

}
