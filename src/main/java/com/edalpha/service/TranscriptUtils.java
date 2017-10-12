package com.edalpha.service;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaul on 7/3/17.
 */

@Named
public class TranscriptUtils {

    // generic method
    public String getPattern(String regex, String text, boolean firstOccurence){

        String matchedString = "";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);//"name: '([^']*)'");
        Matcher matcher = p.matcher(text);

        while(matcher.find()){
            matchedString = matcher.group();
            if(firstOccurence){
                break;
            }

        }

        return matchedString.trim();
    }

    // generic method
    public List<String> getAllMatches(String regex, String text){

        String matchedString = "";
        List<String> matchedStrings = new ArrayList<>();
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);//"name: '([^']*)'");
        Matcher matcher = p.matcher(text);

        while(matcher.find()){
            matchedString = matcher.group().trim();
            matchedStrings.add(matchedString);

        }

        return matchedStrings;
    }
}
