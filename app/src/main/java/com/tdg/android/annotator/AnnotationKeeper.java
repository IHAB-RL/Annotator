package com.tdg.android.annotator;

import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class AnnotationKeeper {

    private String LOG = "AnnotationKeeper";
    private ArrayList<DateAndAnnotation> listOfAnnotations;
    private String ID_RATER, ID_SUBJECT, ADDITIONAL_DATA, FREITEXT;
    private SimpleDateFormat DATE_FORMAT;
    private String TIME_START, TIME_END;
    private boolean isUebung;

    AnnotationKeeper() {
        reset();
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT);
    }

    void setRaterID(String string) {
        ID_RATER = string;
    }

    void setSubjectID(String string) {
        ID_SUBJECT = string;
    }

    void setAdditionalData(String string) {
        ADDITIONAL_DATA = string;
    }

    void setFreiText(String string) {
        FREITEXT = string;
    }

    void setTimeStart() {
        TIME_START = generateTimeNowUTC();
    }

    void setTimeEnd() {
        TIME_END = generateTimeNowUTC();
    }

    void setUebung(boolean uebung) {
        isUebung = uebung;
    }

    public String getRaterID() {
        return ID_RATER;
    }

    public String getSubjectID() {
        return ID_SUBJECT;
    }

    public ArrayList<DateAndAnnotation> getListOfAnnotations() {
        return listOfAnnotations;
    }

    void addAnnotation(int code) {
        listOfAnnotations.add(new DateAndAnnotation(generateTimeNowUTC(), code));
    }

    void removeLastAnnotation() {
        if (getNumberOfAnnotations() > 0) {
            listOfAnnotations.remove(getNumberOfAnnotations() - 1);
        }
    }

    String flushResults() {
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
        streamOfAnnotations += "Additional Data:";
        streamOfAnnotations += "\n";
        streamOfAnnotations += ADDITIONAL_DATA;
        streamOfAnnotations += "\n";
        streamOfAnnotations += "Free Text:";
        streamOfAnnotations += FREITEXT;
        streamOfAnnotations += "\n";
        streamOfAnnotations += "Time Start: ";
        streamOfAnnotations += TIME_START;
        streamOfAnnotations += "\n";
        streamOfAnnotations += "Time End: ";
        streamOfAnnotations += TIME_END;
        streamOfAnnotations += "\n";
        streamOfAnnotations += "-----------------------------";
        streamOfAnnotations += "\n";

        for (DateAndAnnotation annotation : listOfAnnotations) {
            streamOfAnnotations += annotation.date;
            streamOfAnnotations += ": " + annotation.annotation;
            streamOfAnnotations += "\n";
        }

        streamOfAnnotations += "-----------------------------";

        return streamOfAnnotations;
    }

    String getFileName() {
        return generateTimeNowUTC()+"_"+ID_SUBJECT+"_"+ID_RATER+".txt";
    }

    int getNumberOfAnnotations() {
        return listOfAnnotations.size();
    }

    void reset() {
        listOfAnnotations = new ArrayList<>();
        setSubjectID("");
        setRaterID("");
        setAdditionalData("");
        setFreiText("");
        isUebung = false;
    }

    private String generateTimeNowUTC() {
        Calendar dateTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return DATE_FORMAT.format(dateTime.getTime());
    }
}
