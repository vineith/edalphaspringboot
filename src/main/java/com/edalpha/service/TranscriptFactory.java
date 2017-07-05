package com.edalpha.service;

import com.edalpha.model.TranscriptModel;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by kaul on 7/3/17.
 */
@Named
public class TranscriptFactory {

    @Inject
    TranscriptGatech transcriptGatech;

    public TranscriptModel getTranscript(TranscriptType transcriptType, String text){

        TranscriptModel transcript = new TranscriptModel();
        if(transcriptType.equals(TranscriptType.TRANSCRIPT_GATECH)){


            String name = transcriptGatech.getStudentName(text);
            String birthDate = transcriptGatech.getBirthDate(text);
            transcript.setStudentName(name);
            transcript.setBirthDate(birthDate);
            transcript.setDegreeDate(transcriptGatech.getDegreeDate(text));
            transcript.setMajor(transcriptGatech.getMajor(text));
            transcript.setAcademicStanding(transcriptGatech.getAcademicStanding(text));
            transcript.setOverallGpa(transcriptGatech.getOverallGpa(text));
            //transcript.setDegreeDate(getDegreeDate(text));
        }

        return transcript;


    }



}
