package com.edalpha.service;

import com.edalpha.model.Transcript;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector;

import javax.inject.Named;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaul on 6/20/17.
 */
@Named
public class TranscriptCreator {


    public Transcript createTranscript(String text){

        Transcript transcript = new Transcript();
        String name = getStudentName(text);
        String birthDate = getBirthDate(text);
        transcript.setStudentName(name);
        transcript.setBirthDate(birthDate);
        transcript.setDegreeDate(getDegreeDate(text));
        transcript.setMajor(getMajor(text));
        transcript.setAcademicStanding(getAcademicStanding(text));
        transcript.setOverallGpa(getOverallGpa(text));
        //transcript.setDegreeDate(getDegreeDate(text));
        return transcript;

    }


    public String getStudentName(String text){
        String regex = "Name(.)*";
        return  getPattern(regex, text).replaceAll("Name|name","").replaceAll(":","");

    }

    public String getBirthDate(String text){
        return getPattern("Birth(.)Date.*",text).replaceAll("Birth(.)Date","").replaceAll(":","");
    }

    public String getMajor(String text){
        return  getPattern("Major(.)*",text).replaceAll("Major","").replace(":","");

    }

    public String getAcademicStanding(String text){
         return getPattern("Academic(.)Standing(.)*",text).replaceAll("Academic(.)Standing","").replaceAll(":","");
    }

    public String getDegreeDate(String text){
       return getPattern("Degree(.)Date(.)*",text).replaceAll("Degree(.)Date","").replaceAll(":","");
    }

    public String getOverallGpa(String text){
        String gpaString =  getPattern("Overall(.)*",text).replaceAll("Overall","");
        return getPattern("([0-3]\\.\\d+|4\\.00)$",gpaString);
    }

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

    public static void main(String[] args){

        String text="\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatech… 1/12\n" +
                "\n" +
                "Welcome\n" +
                "Karttikay Moudgil\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "http://www.gatech.edu/\n" +
                "https://buzzport.gatech.edu/render.userLayoutRootNode.uP?uP_root=root&uP_tparam=frm&frm=&uP_tparam=utf&utf=\n" +
                "http://www.gatech.edu/\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatech… 2/12\n" +
                "\n" +
                "Display Transcript   902797544 Karttikay Moudgil\n" +
                "May 22, 2017 10:25 pm\n" +
                "\n" +
                "  If you received a grade of \"NR\", your instructor did not report grades. Please contact the instructor for\n" +
                "assistance.\n" +
                "\n" +
                "This is NOT an official transcript. Courses which are in progress may also be included on this transcript.\n" +
                "\n" +
                "The \"PASSED HOURS\" column should be ignored. It is used for calculations by the Board of Regents and is\n" +
                "not a true indicator of passed hours. Students should view \"EARNED HOURS\" instead.\n" +
                "\n" +
                "The last column (\"R\") indicates repeated courses. **If an \"E\" is in the last column, the grade has been\n" +
                "excluded as a result of the grade substitution policy. All grades received at Georgia Tech are included in\n" +
                "GPA calculation with the exception of those removed via grade substitution. Click this Grade Substitution\n" +
                "link to review the policy.\n" +
                "\n" +
                "Institution Credit    Transcript Totals\n" +
                "\n" +
                "Transcript Data\n" +
                "STUDENT INFORMATION\n" +
                "\n" +
                "Name : Karttikay Moudgil\n" +
                "Birth Date: May 12, 1989\n" +
                "\n" +
                "Curriculum Information\n" +
                "\n" +
                "Current Program\n" +
                "\n" +
                "Major and Department: Chemistry, Sch/Chemistry and\n" +
                "Biochemistry\n" +
                "\n" +
                " \n" +
                "\n" +
                "***This is NOT an Official Transcript***\n" +
                "\n" +
                " \n" +
                "\n" +
                "DEGREES AWARDED\n" +
                "\n" +
                "Degree\n" +
                "\n" +
                "Awarded:\n" +
                "\n" +
                "Doctor of Philosophy Degree Date: May 07, 2016\n" +
                "\n" +
                "Curriculum Information\n" +
                "\n" +
                "Primary Degree\n" +
                "\n" +
                "Major: Chemistry\n" +
                "\n" +
                " \n" +
                "\n" +
                " \n" +
                "\n" +
                "INSTITUTION CREDIT      \u00ADTop\u00AD\n" +
                "\n" +
                "Term: Fall 2011\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 6371 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Organic Compounds A 3.000 12.00      \n" +
                "\n" +
                "CHEM 6483 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Chem\u00ADElectronic Material A 3.000 12.00      \n" +
                "\n" +
                "CHEM 8000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Seminar \u00AD Chemistry S 1.000 0.00   I  \n" +
                "\n" +
                "CHEM 8833 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Spec Top\u00ADOrganic Chem\n" +
                "Introduction to Mechanisms\n" +
                "\n" +
                "A 3.000 12.00      \n" +
                "\n" +
                "CHEM 8901 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Special Problems S 2.000 0.00      \n" +
                "\n" +
                "CHEM 8997 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Teaching Assistantship V 3.000 0.00      \n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 6.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 21.000 9.000 18.000 9.000 36.00 4.00\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "http://www.catalog.gatech.edu/rules/5c.php\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatech… 3/12\n" +
                "\n" +
                "Current Term: 21.000 9.000 18.000 9.000 36.00 4.00\n" +
                "Cumulative: 21.000 9.000 18.000 9.000 36.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Spring 2012\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 6372 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Physical Organic Chem A 3.000 12.00      \n" +
                "\n" +
                "CHEM 6373 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Organic Synthesis A 3.000 12.00      \n" +
                "\n" +
                "CHEM 6484 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Chem\u00ADOptical Org Mat V 3.000 0.00      \n" +
                "\n" +
                "CHEM 8000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Seminar \u00AD Chemistry S 1.000 0.00   I  \n" +
                "\n" +
                "CHEM 8997 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Teaching Assistantship V 3.000 0.00      \n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 8.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 21.000 6.000 15.000 6.000 24.00 4.00\n" +
                "Cumulative: 42.000 15.000 33.000 15.000 60.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Summer 2012\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 7001 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Intro to Research A 3.000 12.00      \n" +
                "\n" +
                "CHEM 8902 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Special Problems S 1.000 0.00      \n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 12.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 16.000 3.000 16.000 3.000 12.00 4.00\n" +
                "Cumulative: 58.000 18.000 49.000 18.000 72.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Fall 2012\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 6170 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Inorganic Chem I V 3.000 0.00      \n" +
                "\n" +
                "CHEM 8000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Seminar \u00AD Chemistry S 1.000 0.00   I  \n" +
                "\n" +
                "CHEM 8903 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Special Problems A 3.000 12.00      \n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 14.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt Passed Earned GPA Quality GPA\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatech… 4/12\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 21.000 3.000 18.000 3.000 12.00 4.00\n" +
                "Cumulative: 79.000 21.000 67.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Spring 2013\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 6750 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Prep&Reactions\u00ADPolymers V 3.000 0.00      \n" +
                "\n" +
                "CHEM 8000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Seminar \u00AD Chemistry S 1.000 0.00   I  \n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 17.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 21.000 0.000 18.000 0.000 0.00 0.00\n" +
                "Cumulative: 100.000 21.000 85.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Summer 2013\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 16.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 16.000 0.000 16.000 0.000 0.00 0.00\n" +
                "Cumulative: 116.000 21.000 101.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Fall 2013\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 21.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 21.000 0.000 21.000 0.000 0.00 0.00\n" +
                "Cumulative: 137.000 21.000 122.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Spring 2014\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 21.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatech… 5/12\n" +
                "\n" +
                "Hours Hours Hours Hours Points\n" +
                "Current Term: 21.000 0.000 21.000 0.000 0.00 0.00\n" +
                "Cumulative: 158.000 21.000 143.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Summer 2014\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 16.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 16.000 0.000 16.000 0.000 0.00 0.00\n" +
                "Cumulative: 174.000 21.000 159.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Fall 2014\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 21.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 21.000 0.000 21.000 0.000 0.00 0.00\n" +
                "Cumulative: 195.000 21.000 180.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Spring 2015\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 21.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 21.000 0.000 21.000 0.000 0.00 0.00\n" +
                "Cumulative: 216.000 21.000 201.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Summer 2015\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 16.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 16.000 0.000 16.000 0.000 0.00 0.00\n" +
                "Cumulative: 232.000 21.000 217.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatech… 6/12\n" +
                "\n" +
                "RELEASE: 8.7.1\n" +
                "\n" +
                "Term: Fall 2015\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 21.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 21.000 0.000 21.000 0.000 0.00 0.00\n" +
                "Cumulative: 253.000 21.000 238.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "Term: Spring 2016\n" +
                "\n" +
                "Major: Chemistry\n" +
                "Academic Standing: Good Standing\n" +
                "\n" +
                "Subject Course Campus Level Title Grade Credit\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "Start and\n" +
                "End Dates\n" +
                "\n" +
                "R\n" +
                "\n" +
                "CHEM 9000 Georgia\n" +
                "Tech\u00ADAtlanta\n" +
                "*\n" +
                "\n" +
                "GS Doctoral Thesis S 1.000 0.00   I  \n" +
                "\n" +
                "Term Totals (Graduate Semester)\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Current Term: 1.000 0.000 1.000 0.000 0.00 0.00\n" +
                "Cumulative: 254.000 21.000 239.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                "TRANSCRIPT TOTALS (GRADUATE SEMESTER)      \u00ADTop\u00AD\n" +
                "\n" +
                "  Attempt\n" +
                "Hours\n" +
                "\n" +
                "Passed\n" +
                "Hours\n" +
                "\n" +
                "Earned\n" +
                "Hours\n" +
                "\n" +
                "GPA\n" +
                "Hours\n" +
                "\n" +
                "Quality\n" +
                "Points\n" +
                "\n" +
                "GPA\n" +
                "\n" +
                "Total Institution: 254.000 21.000 239.000 21.000 84.00 4.00\n" +
                "Total Transfer: 0.000 0.000 0.000 0.000 0.00 0.00\n" +
                "Overall: 254.000 21.000 239.000 21.000 84.00 4.00\n" +
                "\n" +
                " \n" +
                "\n" +
                "Unofficial Transcript\n" +
                "\n" +
                " \n" +
                "\n" +
                "© 2017 Ellucian Company L.P. and its affiliates.\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatech… 7/12\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatech… 8/12\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatech… 9/12\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatec… 10/12\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatec… 11/12\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n" +
                "\n" +
                "5/22/2017 BuzzPort\n" +
                "\n" +
                "https://buzzport.gatech.edu/render.UserLayoutRootNode.uP?uP_tparam=utf&utf=%2Fcp%2Fip%2Flogin%3Fsys%3Dsct%26url%3Dhttps%3A%2F%2Foscar.gatec… 12/12\n" +
                "\n" +
                "Logout\n" +
                "\n" +
                "https://buzzport.gatech.edu/up/Logout?uP_tparam=frm&frm=\n" +
                "\n";
        TranscriptCreator transcriptCreator = new TranscriptCreator();
        //System.out.println("bdate:"+transcriptCreator.getBirthDate(text));

//        System.out.println("name:"+transcriptCreator.getStudentName(text));


//        System.out.println("degree date:"+transcriptCreator.getDegreeDate(text));

  //      System.out.println("major:"+transcriptCreator.getMajor(text));
    //    System.out.println("major:"+transcriptCreator.getAcademicStanding(text));
        System.out.println("gpa:"+transcriptCreator.getOverallGpa(text));


    }
}

