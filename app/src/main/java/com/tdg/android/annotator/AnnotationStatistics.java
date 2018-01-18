package com.tdg.android.annotator;

import java.util.ArrayList;

/**
 * Created by ulrikkowalk on 29.10.17.
 */

public class AnnotationStatistics {

    private int numCategories = 8;
    private int[] histogram;

    AnnotationStatistics() {
        reset();
    }

    void reset() {
        histogram = new int[numCategories];
        for (int i = 0; i < numCategories; i++) {
            histogram[i] = 0;
        }
    }

    int[] getHist(ArrayList<DateAndAnnotation> listOfAnnotations) {

        reset();

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
