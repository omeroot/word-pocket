package com.yazlab.service;

import com.yazlab.dao.TextDAO;
import com.yazlab.dto.ResponseDTO;
import com.yazlab.dto.TextDTO;
import com.yazlab.dto.TokenDTO;
import com.yazlab.serialize.TextSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MainService {

    @Autowired
    TextDAO textDAO;

    @Autowired
    ResponseDTO responseDTO;

    public MainService() {

    }

    public ResponseDTO getWords(TokenDTO tokenDTO) {
        String token = tokenDTO.getUserToken();
        TextSer textSer = textDAO.getWords(token);
        List<String> gg = new ArrayList<String>();
        Integer counter = 0;

        for (Map.Entry<String, Integer> entry : textSer.getWordList().entrySet()) {
            if (counter == 5)
                break;
            gg.add(entry.getKey());
            counter++;
        }

        responseDTO.setErr(false);
        responseDTO.setWords(gg);

        return responseDTO;
    }

    public void setWords(TextDTO data) {
        Map<String, Integer> newMap = null;//TODO

        textDAO.setWords(data.getUserId(), newMap);
    }
}
