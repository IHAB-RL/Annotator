package com.tdg.android.annotator;

import java.util.ArrayList;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class AnnotationKeeper {

    ArrayList<String> listOfAnnotations;
    private String ID_RATER, ID_SUBJECT;
    private SimpleDateFormat DATE_FORMAT;
    private String TIME_START, TIME_END;

    public AnnotationKeeper() {
        clearAllAnnotations();
        ID_RATER = "";
        ID_SUBJECT = "";
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT);
    }

    public void setRaterID(String string) {
        ID_RATER = string;
    }

    public void setSubjectID(String string) {
        ID_SUBJECT = string;
    }

    public void setTimeStart() {
        TIME_START = generateTimeNowUTC();
    }

    public void setTimeEnd() {
        TIME_END = generateTimeNowUTC();
    }

    public String getRaterID() {
        return ID_RATER;
    }

    public String getSubjectID() {
        return ID_SUBJECT;
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

    public String flushAnnotations() {
        String streamOfAnnotations = "";

        streamOfAnnotations += "Rater ID: ";
        streamOfAnnotations += ID_RATER;
        streamOfAnnotations += "\n";
        streamOfAnnotations += "Subject ID: ";
        streamOfAnnotations += ID_SUBJECT;
        streamOfAnnotations += "\n";
        streamOfAnnotations += "Number of Ratings: ";
        streamOfAnnotations += ""+getNumberOfAnnotations();
        streamOfAnnotations += "\n";
        streamOfAnnotations += "Time Start: ";
        streamOfAnnotations += TIME_START;
        streamOfAnnotations += "\n";
        streamOfAnnotations += "Time End: ";
        streamOfAnnotations += TIME_END;
        streamOfAnnotations += "\n";
        streamOfAnnotations += "-----------------------------";
        streamOfAnnotations += "\n";

        for (String annotation : listOfAnnotations) {
            streamOfAnnotations += annotation;
            streamOfAnnotations += "\n";
        }

        streamOfAnnotations += "\n";
        streamOfAnnotations += "-----------------------------";

        return streamOfAnnotations;
    }

    public String getFileName() {
        return generateTimeNowUTC()+"_"+ID_SUBJECT+"_"+ID_RATER+".txt";
    }

    public int getNumberOfAnnotations() {
        return listOfAnnotations.size();
    }

    public void clearAllAnnotations() {
        listOfAnnotations = new ArrayList<>();
    }

    private String generateTimeNowUTC() {
        Calendar dateTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return DATE_FORMAT.format(dateTime.getTime());
    }
}
