package com.edalpha.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by kaul on 9/9/17.
 */

@Entity
public class GradeConvertor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String country;
    private Long scaleMin;
    private Long scaleMax;
    private String description;
    private String us_grade;


    //private no arg constructor needed by JPA
    private GradeConvertor(){

    }

    //getters and setters
    public Long getId() {
        return id;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUs_grade() {
        return us_grade;
    }

    public void setUs_grade(String us_grade) {
        this.us_grade = us_grade;
    }

    public Long getScaleMin() {
        return scaleMin;
    }

    public void setScaleMin(Long scaleMin) {
        this.scaleMin = scaleMin;
    }

    public Long getScaleMax() {
        return scaleMax;
    }

    public void setScaleMax(Long scaleMax) {
        this.scaleMax = scaleMax;
    }


}
