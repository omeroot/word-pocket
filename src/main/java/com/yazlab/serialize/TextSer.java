package com.yazlab.serialize;


import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;

@Repository
public class TextSer implements Serializable{
    private String userId;
    private Map<String, Integer> wordList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getWordList() {
        return wordList;
    }

    public void setWordList(Map<String, Integer> wordList) {
        this.wordList = wordList;
    }
}
