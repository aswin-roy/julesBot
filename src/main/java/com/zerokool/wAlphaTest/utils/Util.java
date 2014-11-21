package com.zerokool.wAlphaTest.utils;

import java.util.Random;

/**
 *
 */
public class Util {
    public String getRandomString(String text){
        String randomWords[];
        String randomWord;
        Integer handle;
        randomWords = text.split(" ");
        handle= new Random().nextInt(randomWords.length - 1);
        randomWord = randomWords[handle] + " "
                + randomWords[handle + 1] ;
        return randomWord;
    }
}
