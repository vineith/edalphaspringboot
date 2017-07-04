package com.edalpha.service;


import javax.inject.Inject;
import javax.inject.Named;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaul on 6/20/17.
 */
@Named
public class TranscriptGatech implements Transcript{

    @Inject
    TranscriptUtils transcriptUtils;


    public String getStudentName(String text){
        String regex = "Name(.)*";
        return  transcriptUtils.getPattern(regex, text).replaceAll("Name|name","").replaceAll(":","");

    }

    public String getBirthDate(String text){
        return transcriptUtils.getPattern("Birth(.)Date.*",text).replaceAll("Birth(.)Date","").replaceAll(":","");
    }

    public String getMajor(String text){
        return  transcriptUtils.getPattern("Major(.)*",text).replaceAll("Major","").replace(":","");

    }

    public String getAcademicStanding(String text){
         return transcriptUtils.getPattern("Academic(.)Standing(.)*",text).replaceAll("Academic(.)Standing","").replaceAll(":","");
    }

    public String getDegreeDate(String text){
       return transcriptUtils.getPattern("Degree(.)Date(.)*",text).replaceAll("Degree(.)Date","").replaceAll(":","");
    }

    public String getOverallGpa(String text){
        String gpaString =  transcriptUtils.getPattern("Overall(.)*",text).replaceAll("Overall","");
        return transcriptUtils.getPattern("([0-3]\\.\\d+|4\\.00)$",gpaString);
    }




}

