package com.yazlab.service;

import com.yazlab.dao.TextDAO;
import com.yazlab.dto.ResponseDTO;
import com.yazlab.dto.TextDTO;
import com.yazlab.dto.TokenDTO;
import com.yazlab.serialize.TextSer;
import com.yazlab.util.Filter;
import com.yazlab.util.Spell;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.TokenStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MainService {
    private static final Logger logger = Logger.getLogger(MainService.class);

    @Autowired
    TextDAO textDAO;

    @Autowired
    ResponseDTO responseDTO;

    @Autowired
    Filter filter;

    @Autowired
    Spell spell;

    @Autowired
    TextSer textSer;

    public MainService() {

    }

    public ResponseDTO getWords(TokenDTO tokenDTO) {
        String token = tokenDTO.getUserToken();
        Map<String, Integer> wordMap = textDAO.getWords(token);
        List<String> piece = new ArrayList<>();

        logger.info("[" + token + "] [STORED WORDS] " + wordMap);

        Set<Map.Entry<String, Integer>> set = wordMap.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        for(int i = 0; i< list.size(); i++){
            String p = String.valueOf(list.get(i).getKey());
            piece.add(p);
            if(i == 4) break;
        }

        logger.info("[" + token + "] [SORTED WORDS]" + piece);
        responseDTO.setErr(false);
        responseDTO.setWords(piece);

        return responseDTO;
    }

    public ResponseDTO setWords(TextDTO data) {
        TokenStream tokenStream = filter.filter(data.getSentence());

        List<String> root = spell.rootPluck(tokenStream);

        Map<String, Integer> preMap = textDAO.getWords(data.getUserId());

        for (String single : root) {
            if (preMap.containsKey(single)) {
                Integer freq = (Integer) preMap.get(single);
                preMap.replace(single, freq, new Integer(freq.intValue() + 1));
            } else {
                preMap.put(single, 1);
            }
        }

        textDAO.setWords(data.getUserId(), preMap);

        return responseDTO;
    }
}
