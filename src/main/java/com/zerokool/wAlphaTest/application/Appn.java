package com.zerokool.wAlphaTest.application;

import com.zerokool.wAlphaTest.markovChain.MarkovChain;
import com.zerokool.wAlphaTest.mongo.MongoOps;
import com.zerokool.wAlphaTest.utils.Util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Application Class
 */
public class Appn {

    public static void main(String args[]) throws UnknownHostException {

        String text = "How are you  ";
        String randomPiece;
        String answer;

        MarkovChain markovChain = new MarkovChain();
        markovChain.trainMarkovChains(text);

        MongoOps mongoOps = new MongoOps();
        Map<String, ArrayList<String>> markovChains = mongoOps.getFromMongo();

        Util util = new Util();
        randomPiece = util.getRandomString(text);

        answer = markovChain.replyRandomly(randomPiece,markovChains);
        System.out.println("Reply : " + answer);
    }
}
