package com.tdg.android.annotator;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class AnnotationKeeper {

    private ArrayList<DateAndAnnotation> listOfAnnotations;
    private String ID_RATER, ID_SUBJECT, ADDITIONAL_DATA, FREITEXT;
    private SimpleDateFormat DATE_FORMAT;
    private String TIME_START, TIME_END;

    public AnnotationKeeper() {
        reset();
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT);
    }

    public void setRaterID(String string) {
        ID_RATER = string;
    }

    public void setSubjectID(String string) {
        ID_SUBJECT = string;
    }

    public void setAdditionalData(String string) {
        ADDITIONAL_DATA = string;
    }

    public void setFreiText(String string) {
        FREITEXT = string;
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

    public void addAnnotation(int code) {
        listOfAnnotations.add(new DateAndAnnotation(generateTimeNowUTC(), code));
    }

    public void removeLastAnnotation() {
        if (getNumberOfAnnotations() > 0) {
            listOfAnnotations.remove(getNumberOfAnnotations() - 1);
        }
    }

    public String flushResults() {
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

    public String getFileName() {
        return generateTimeNowUTC()+"_"+ID_SUBJECT+"_"+ID_RATER+".txt";
    }

    public int getNumberOfAnnotations() {
        return listOfAnnotations.size();
    }

    public void reset() {
        listOfAnnotations = new ArrayList<>();
        setSubjectID("");
        setRaterID("");
        setAdditionalData("");
        setFreiText("");
    }

    private String generateTimeNowUTC() {
        Calendar dateTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        return DATE_FORMAT.format(dateTime.getTime());
    }
}
