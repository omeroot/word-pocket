package com.yazlab.util;

import com.yazlab.dto.TextDTO;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Filter {
    CharArraySet stopSet = null;

    public Filter() {
        loadStopWords();
    }

    public TokenStream filter(String sentence) {
        String nonPuncSentence = filterPunctuation(sentence);
        return filterStopWords(nonPuncSentence);
    }

    public String filterPunctuation(String sentence) {
        return (sentence.replaceAll("[.,~!?;:\\-']", ""));
    }

    public TokenStream filterStopWords(String sentence) {
        Reader reader = new StringReader(sentence);
        StopAnalyzer analyzer = new StopAnalyzer(stopSet);
        TokenStream tokenStream = null;

        try {
            tokenStream = analyzer.tokenStream("sentence", reader);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                tokenStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            analyzer.close();
        }

        return tokenStream;
    }

    public void loadStopWords(){
        Scanner stopWordFile = null;
        List<String> stopWords = new ArrayList<String>();
        try {
            stopWordFile = new Scanner(new File("stopwords.txt"));
            while (stopWordFile.hasNext()){
                stopWords.add(stopWordFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        stopSet = new CharArraySet(Version.LUCENE_CURRENT,
                stopWords, false);
    }
}
