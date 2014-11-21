package com.zerokool.wAlphaTest.mongo;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MongoOps {

    public void storeChainsToMongo(HashMap<String, ArrayList<String>> markovChain) throws UnknownHostException {

        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        DB db = mongoClient.getDB( "julesDb" );
        DBCollection coll = db.getCollection("lessons");
        BasicDBObject doc;

        for (String string:markovChain.keySet()) {
            doc = new BasicDBObject("key", string)
                    .append("value",markovChain.get(string));
            coll.insert(doc);
        }
    }

    public Map<String, ArrayList<String>> getFromMongo() throws UnknownHostException {
        Map<String, ArrayList<String>> markovChain = new HashMap<String, ArrayList<String>>();

        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        DB db = mongoClient.getDB( "julesDb" );
        DBCollection coll = db.getCollection("lessons");
        DBObject doc;
        DBCursor cursor;

        cursor = coll.find();

        while (cursor.hasNext()){
            doc=cursor.next();
            markovChain.put(doc.get("key").toString(), (ArrayList<String>) doc.get("value"));
        }

        System.out.println("Chains: " + markovChain.toString());
        return markovChain;
    }
}
