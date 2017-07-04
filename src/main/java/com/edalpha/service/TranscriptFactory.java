package com.edalpha.service;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaul on 7/3/17.
 */
public class TranscriptFactory {

    @Inject
    TranscriptGatech transcriptGatech;
    private String text;

    public Transcript getTranscript(String transcriptType){


        if(transcriptType.equalsIgnoreCase(TranscriptType.TRANSCRIPT_GATECH.name())){

            Transcript transcript = new TranscriptGatech();
            String name = transcriptGatech.getStudentName(text);
            String birthDate = transcriptGatech.getBirthDate(text);
            transcript.setStudentName(name);
            transcript.setBirthDate(birthDate);
            transcript.setDegreeDate(getDegreeDate(text));
            transcript.setMajor(getMajor(text));
            transcript.setAcademicStanding(getAcademicStanding(text));
            transcript.setOverallGpa(getOverallGpa(text));
            //transcript.setDegreeDate(getDegreeDate(text));
            return transcript;
        }



    }


}
