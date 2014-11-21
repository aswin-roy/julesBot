package com.zerokool.wAlphaTest.markovChain;

import com.zerokool.wAlphaTest.mongo.MongoOps;

import java.net.UnknownHostException;
import java.util.*;

/**
 *
 */
public class MarkovChain {

    Map<String, ArrayList<String>> markovChain;
    private boolean flag = true;
    private String reply = "";
    private String sample;

    public void trainMarkovChains(String text) throws UnknownHostException {

        ArrayList<String> keySet;
        ArrayList<String> refinedKeySet = new ArrayList<String>();
        ArrayList<String> allLines;
        Integer handle;
        ArrayList<String> arrayList;
        markovChain = new HashMap<String, ArrayList<String>>();

        allLines = new ArrayList<String>(Arrays.asList(text.split("\\.")));

        for (String eachLine : allLines) {
            keySet = new ArrayList<String>(Arrays.asList(eachLine.split(" ")));

            for (String oldKey : keySet) {
                refinedKeySet.add(oldKey.replaceAll("[^a-zA-Z0-9]", ""));
            }

            for (handle = 0; handle < refinedKeySet.size() - 2; handle++) {
                if (refinedKeySet.get(handle).trim().length() == 0) {
                    continue;
                }
                if (markovChain.containsKey(refinedKeySet.get(handle) + " " + refinedKeySet.get(handle + 1))) {
                    arrayList = markovChain.get(refinedKeySet.get(handle) + " " + refinedKeySet.get(handle + 1));
                } else {
                    arrayList = new ArrayList<String>();
                }
                arrayList.add(refinedKeySet.get(handle + 2));
                markovChain.put(refinedKeySet.get(handle) + " " + refinedKeySet.get(handle + 1), arrayList);
            }
        }

        MongoOps mongoOps = new MongoOps();
        mongoOps.storeChainsToMongo((HashMap<String, ArrayList<String>>) markovChain);

    }

    public String replyRandomly(String randomPiece, Map<String, ArrayList<String>> markovChains) {
        ArrayList<String> nextPieces = new ArrayList<String>();

        System.out.println("first piece : " + randomPiece);
        if (markovChains.keySet().contains(randomPiece)) {
            nextPieces = markovChains.get(randomPiece);
            System.out.println("First piece matches  " + nextPieces.toString());
        } else {
            System.out.println("I do not understand you!");
            System.exit(0);
        }

        while (flag) {
            sample = nextPieces.get(new Random().nextInt(nextPieces.size()));
            reply = reply.concat(sample);
            System.out.println("reply pieces: " + reply);

            addTokensToResult(markovChains);
        }

        return reply;
    }

    public void addTokensToResult(Map<String, ArrayList<String>> markovChains) {
        flag = false;

        for (String eachKey : markovChains.keySet()) {

            if (eachKey.contains(sample)) {
                System.out.println("each key : " + eachKey + "and sample : " + sample);
                flag = true;
                reply = reply.concat(eachKey);
                sample = markovChains.get(eachKey).get(new Random().nextInt(markovChains.get(eachKey).size()));
                break;
            }

        }
    }

}
