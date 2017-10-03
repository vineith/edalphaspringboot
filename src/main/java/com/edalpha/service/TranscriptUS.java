package com.edalpha.service;


import com.edalpha.model.Word;
import com.edalpha.repository.WordRepository;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaul on 6/20/17.
 */
@Named
public class TranscriptUS implements Transcript{

    @Inject
    TranscriptUtils transcriptUtils;
    @Inject
    WordRepository wordRepository;


    public String getStudentName(String text){
        //String regex = "Name(.)*";
        String regex = "^[a-zA-Z\\\\s]*$";
        //return  transcriptUtils.getPattern(regex, text, false).replaceAll("Name|name","").replaceAll(":","");
        return transcriptUtils.getPattern(regex, text, false);

    }

    public String getBirthDate(String text){

        String birthDate = transcriptUtils.getPattern("[a-zA-z\\s:]*[0-9]{2}-[A-Z]{3}-[0-9]{4}",text, false);
        if(StringUtils.isBlank(birthDate)){
            //May 12, 1989
            birthDate = transcriptUtils.getPattern("[a-zA-z\\s?:\\s+]*[A-Za-z]{3} [0-9]{2},\\s*[0-9]{4}",text, false);
        }
        if(StringUtils.isBlank(birthDate)){
            //9/16/1982
            birthDate = transcriptUtils.getPattern("[a-zA-z\\s?:\\s+]*[0-9]{2}/[0-9]{2}/[0-9]{4}",text, false);
        }
        birthDate = birthDate.toUpperCase();
        if(birthDate.contains("BIRTH")){
            birthDate = transcriptUtils.getPattern("[0-9]{2}-[A-Z]{3}-[0-9]{4}|[A-Za-z]{3} [0-9]{2},\\s*[0-9]{4}", birthDate, true);
            return birthDate;
        }
        else{
            birthDate= "";
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
        List<Word> words = wordRepository.getWordBySynonymIds("university");
        String universityName = "";
        List<String> probableMatches = new ArrayList<>();
        //iterate over all words
        for (Word word:words) {
            universityName = transcriptUtils.getPattern("[a-zA-Z ]*(?i)"+word.getWord()+"(?i)[a-zA-Z ]*", text, true);
            if(!StringUtils.isBlank(universityName)){
                probableMatches.add(universityName);
            }
        }

        //find the match
        if(probableMatches.size() > 1) {
            for (String probable : probableMatches) {
                if(probable.contains("of") || probable.contains("OF")){
                    universityName = probable;
                }

            }
        }
        else{
            universityName = probableMatches.size() >0 ? probableMatches.get(0): "";
            if(universityName.split(" ").length > 5){
                universityName = "";
            }
        }


        return universityName;
    }




}

