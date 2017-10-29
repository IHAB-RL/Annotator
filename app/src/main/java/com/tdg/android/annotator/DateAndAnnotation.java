package com.tdg.android.annotator;

import java.util.Date;

/**
 * Created by ulrikkowalk on 29.10.17.
 */

public class DateAndAnnotation {

    String date;
    int annotation;

    public DateAndAnnotation(String stringDate, int intAnnotation) {
        date = stringDate;
        annotation = intAnnotation;
    }

    public String getDate() {
        return date;
    }

    public int getAnnotation() {
        return annotation;
    }

}
