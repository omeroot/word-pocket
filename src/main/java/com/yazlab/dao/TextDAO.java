package com.yazlab.dao;


import com.yazlab.serialize.TextSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class TextDAO {

    @Autowired
    TextSer textSer;

    public List<String> getWords(String token) {
        List<String> gg = null;
        Integer counter = 0;

        try {
            FileInputStream fileIn = new FileInputStream("db/" + token + ".ser");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            textSer = (TextSer) objIn.readObject();

            for (Map.Entry<String, Integer> entry : textSer.getWordList().entrySet()) {
                if (counter == 5)
                    break;
                gg.add(entry.getKey());
                counter++;
            }

            objIn.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return gg;
    }
}
