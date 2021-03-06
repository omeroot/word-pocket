package com.yazlab.dao;

import com.yazlab.dto.TextDTO;
import com.yazlab.serialize.TextSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TextDAO {

    @Autowired
    TextSer textSer;

    public Map<String, Integer> getWords(String token) {
        List<String> gg = new ArrayList<String>();
        Integer counter = 0;

        try {
            FileInputStream fileIn = new FileInputStream("db/" + token.toString() + ".ser");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            textSer = (TextSer) objIn.readObject();

            objIn.close();
            fileIn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<String,Integer>();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textSer.getWordList();
    }

    public void setWords(String token, Map<String, Integer> wordsMap) {
        FileOutputStream fs = null;

        try {
            fs = new FileOutputStream("db/" + token.toString() + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fs);

            TextSer s = new TextSer();
            s.setWordList(wordsMap);

            objectOutputStream.writeObject(s);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
