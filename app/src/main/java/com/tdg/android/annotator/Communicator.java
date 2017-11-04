package com.tdg.android.annotator;

/**
 * Created by ulrikkowalk on 04.11.17.
 */

public interface Communicator{

    public void beginAnnotation(String raterId, String subjectId, boolean isUebung);

    public void finishAnnotation(String charakterisierung, String freiText);

    public void addAnnotation(int Code);

    public void removeLastAnnotation();
}