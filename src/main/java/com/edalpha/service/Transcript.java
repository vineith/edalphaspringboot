package com.edalpha.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaul on 7/3/17.
 */
public interface Transcript {

    String getStudentName(String text);

    String getBirthDate(String text);

    String getMajor(String text);

    String getAcademicStanding(String text);

    String getDegreeDate(String text);

    String getOverallGpa(String text);

    String getSchoolName(String text);
}
