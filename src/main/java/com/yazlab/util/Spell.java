package com.yazlab.util;


import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.stereotype.Component;
import zemberek.morphology.apps.TurkishWordParserGenerator;
import zemberek.morphology.parser.MorphParse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Spell {
    private TurkishWordParserGenerator parser;

    public Spell(){
        try {
            this.parser = TurkishWordParserGenerator.createWithDefaults();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> rootPluck(TokenStream tokenStream){
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        Pattern p = Pattern.compile("\\[\\((.*?)\\)");
        List<String> words = new ArrayList<>();

        try {
            tokenStream.reset();

            while(tokenStream.incrementToken()){
                String token = charTermAttribute.toString();
                List<MorphParse> parses = parser.getParser().parse(token);

                if(parses.size() > 0){
                    for (MorphParse parse : parses) {
                        String format = parse.formatNoEmpty();
                        if(format.indexOf("Noun") >= 0){
                            Matcher m = p.matcher(format);
                            m.find();
                            words.add(m.group(1));
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
