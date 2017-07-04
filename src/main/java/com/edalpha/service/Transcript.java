package com.edalpha.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaul on 7/3/17.
 */
public interface Transcript {

    public String getStudentName(String text);

    public String getBirthDate(String text);

    public String getMajor(String text);

    public String getAcademicStanding(String text);

    public String getDegreeDate(String text);

    public String getOverallGpa(String text);


}
