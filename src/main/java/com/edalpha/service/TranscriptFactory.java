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
    TranscriptUS transcriptUS;

    public TranscriptModel getTranscript(TranscriptType transcriptType, String text){

        TranscriptModel transcript = new TranscriptModel();
        if(transcriptType.equals(TranscriptType.TRANSCRIPT_US)){

            String name = transcriptUS.getStudentName(text);
            String birthDate = transcriptUS.getBirthDate(text);
            transcript.setStudentName(name);
            transcript.setBirthDate(birthDate);
            transcript.setDegreeDate(transcriptUS.getDegreeDate(text));
            transcript.setMajor(transcriptUS.getMajor(text));
            transcript.setAcademicStanding(transcriptUS.getAcademicStanding(text));
            transcript.setOverallGpa(transcriptUS.getOverallGpa(text));
            transcript.setSchoolName(transcriptUS.getSchoolName(text));
        }
        if(transcriptType.equals(TranscriptType.TRANSCRIPT_MUMBAI)){

        }

        return transcript;


    }



}
