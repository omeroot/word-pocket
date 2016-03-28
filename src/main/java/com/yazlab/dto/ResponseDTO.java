package com.yazlab.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseDTO {
    private Boolean Err;
    private String Msg;
    private List<String> words;

    public ResponseDTO(){
        this.Msg = "";
        this.Err = false;
        this.words = null;
    }

    public Boolean getErr() {
        return Err;
    }

    public void setErr(Boolean err) {
        Err = err;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
