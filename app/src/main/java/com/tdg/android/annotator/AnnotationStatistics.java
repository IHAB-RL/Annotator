package com.tdg.android.annotator;

import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.value;

/**
 * Created by ulrikkowalk on 29.10.17.
 */

public class AnnotationStatistics {

    int numCategories = 8;
    int[] histogram = new int[numCategories];;

    public AnnotationStatistics() {
    }

    public int[] getHist(ArrayList<DateAndAnnotation> listOfAnnotations) {

        for (DateAndAnnotation item : listOfAnnotations) {
            switch (item.getAnnotation()) {
                case 1:
                    histogram[0]++;
                    break;
                case 2:
                    histogram[1]++;
                    break;
                case 3:
                    histogram[2]++;
                    break;
                case 4:
                    histogram[3]++;
                    break;
                case 5:
                    histogram[4]++;
                    break;
                case 6:
                    histogram[5]++;
                    break;
                case 7:
                    histogram[6]++;
                    break;
                case 8:
                    histogram[7]++;
                    break;
            }
        }
        return histogram;
    }
}