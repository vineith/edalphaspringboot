package com.edalpha.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by kaul on 9/23/17.
 */
@Entity
public class Word {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String word;
    private long synId;

    //private no arg constructor needed by JPA
    private Word(){

    }
    //getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getSynId() {
        return synId;
    }

    public void setSynId(long synId) {
        this.synId = synId;
    }

    //to string
    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", synId=" + synId +
                '}';
    }
}
