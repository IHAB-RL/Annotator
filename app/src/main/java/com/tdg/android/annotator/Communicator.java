package com.tdg.android.annotator;

/**
 * Created by ulrikkowalk on 04.11.17.
 */

public interface Communicator{

    void beginAnnotation(String raterId, String subjectId, boolean isUebung);

    void finishAnnotation(String charakterisierung, String freiText);

    void addAnnotation(int Code);

    void removeLastAnnotation();

    void setWasTouched(boolean wasTouched);

    void setImmersiveMode();

}