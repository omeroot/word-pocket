package com.yazlab.dto;

import org.springframework.stereotype.Component;

public class TextDTO{
    private String userId;
    private String sentence;

    public TextDTO(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
