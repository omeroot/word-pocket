package com.yazlab.dto;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ResponseDTO {
    private Boolean Err;
    private String Msg;
    private List<String> words;

    public ResponseDTO(){
        this.Msg = "";
        this.Err = false;
        this.words = new ArrayList<>();
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
