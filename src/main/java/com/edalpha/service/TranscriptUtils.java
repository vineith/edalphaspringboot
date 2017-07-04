package com.edalpha.service;

import javax.inject.Named;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaul on 7/3/17.
 */

@Named
public class TranscriptUtils {

    // generic method
    public String getPattern(String regex, String text){

        String matchedString = "";
        Pattern p = Pattern.compile(regex);//"name: '([^']*)'");
        Matcher matcher = p.matcher(text);

        while(matcher.find()){
            matchedString = matcher.group();
        }

        return matchedString.trim();
    }
}
