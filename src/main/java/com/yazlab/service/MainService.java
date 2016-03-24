package com.yazlab.service;

import com.yazlab.dao.TextDAO;
import com.yazlab.dto.TextDTO;
import com.yazlab.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    TextDAO textDAO;

    public MainService(){

    }

    public TextDTO getWords(TokenDTO tokenDTO){
        TextDTO data = null;
        String token = tokenDTO.getUserToken();
        List<String> gFrequency = textDAO.getWords(token);

        return data;
    }

    public void setWords(TextDTO data){

    }
}
