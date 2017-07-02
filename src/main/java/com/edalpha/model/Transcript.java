package com.edalpha.model;


/**
 * Created by kaul on 6/19/17.
 */
public class Transcript {

    private Long id;
    private String studentName;
    private String schoolName;
    private String birthDate;
    private String degreeDate;
    private String major;
    private String overallGpa;



    private String academicStanding;

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegreeDate() {
        return degreeDate;
    }

    public void setDegreeDate(String degreeDate) {
        this.degreeDate = degreeDate;
    }
    public String getAcademicStanding() {
        return academicStanding;
    }

    public void setAcademicStanding(String academicStanding) {
        this.academicStanding = academicStanding;
    }

    public String getOverallGpa() {
        return overallGpa;
    }

    public void setOverallGpa(String overallGpa) {
        this.overallGpa = overallGpa;
    }

    //tostring

    @Override
    public String toString() {
        return "Transcript{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", degreeDate='" + degreeDate + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
