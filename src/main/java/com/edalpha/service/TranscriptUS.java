package com.edalpha.service;


import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaul on 6/20/17.
 */
@Named
public class TranscriptUS implements Transcript{

    @Inject
    TranscriptUtils transcriptUtils;


    public String getStudentName(String text){
        String regex = "Name(.)*";
        return  transcriptUtils.getPattern(regex, text, false).replaceAll("Name|name","").replaceAll(":","");

    }

    public String getBirthDate(String text){
        String birthDate  = transcriptUtils.getPattern("Birth(.)Date.*",text, false).replaceAll("(?i)Birth(.)Date","").replaceAll(":","");
        if(StringUtils.isBlank(birthDate)){
            birthDate  = transcriptUtils.getPattern("Date of Birth.*",text,false).replaceAll("(?i)Date of Birth","").replaceAll(":","");
        }
        return birthDate;
    }

    public String getMajor(String text){
        return  transcriptUtils.getPattern("Major(.)*",text, false).replaceAll("Major","").replace(":","");

    }

    public String getAcademicStanding(String text){
         return transcriptUtils.getPattern("Academic(.)Standing(.)*",text,false).replaceAll("Academic(.)Standing","").replaceAll(":","");
    }

    public String getDegreeDate(String text){
       return transcriptUtils.getPattern("Degree(.)Date(.)*",text, false).replaceAll("Degree(.)Date","").replaceAll(":","");
    }

    public String getOverallGpa(String text){
        String gpaString =  transcriptUtils.getPattern("Overall(.)*",text, false).replaceAll("Overall","");
        return transcriptUtils.getPattern("([0-3]\\.\\d+|4\\.00)$",gpaString,false);
    }

    public String getSchoolName(String text){
        String universityName = transcriptUtils.getPattern("[a-z ]*University[a-z ]*", text, true);
        return universityName;
    }




}

