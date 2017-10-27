package com.tdg.android.annotator;

import java.util.ArrayList;

/**
 * Created by ulrikkowalk on 27.10.17.
 */

public class AnnotationKeeper {

    ArrayList<String> listOfAnnotations;
    private String raterID, subjectID;

    public AnnotationKeeper() {
        clearAllAnnotations();
        raterID = "";
        subjectID = "";
    }

    public void setRaterID(String string) {
        raterID = string;
    }

    public void setSubjectID(String string) {
        subjectID = string;
    }

    public String getRaterID() {
        return raterID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void addAnnotation(String string) {
        listOfAnnotations.add(string);
    }

    public void addAnnotation(ArrayList<String> listOfStrings) {
        for (String annotation : listOfStrings) {
            listOfAnnotations.add(annotation);
        }
    }

    public void removeLastAnnotation() {
        if (getNumberOfAnnotations() > 0) {
            listOfAnnotations.remove(getNumberOfAnnotations() - 1);
        }
    }

    public int getNumberOfAnnotations() {
        return listOfAnnotations.size();
    }

    public void clearAllAnnotations() {
        listOfAnnotations = new ArrayList<>();
    }
}
