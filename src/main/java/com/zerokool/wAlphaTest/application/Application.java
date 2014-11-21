//package com.zerokool.wAlphaTest.application;
//
//import com.wolfram.alpha.*;
//
///**
// *
// */
//public class Application {
//
//    private static String appId = "HKA8YW-4YYWJAHW5J";
//
//    public static void main(String[] args){
//
//        String inputText = "what";
//
//        WAEngine engine = new WAEngine();
//        engine.setAppID(appId);
//        engine.addFormat("plaintext");
//
//        WAQuery query = engine.createQuery();
//        query.setInput(inputText);
//
//        try {
//
//            WAQueryResult queryResult = engine.performQuery(query);
//
//            if (queryResult.isError()) {
//                System.out.println("Query error");
//                System.out.println("  error code: " + queryResult.getErrorCode());
//                System.out.println("  error message: " + queryResult.getErrorMessage());
//            } else if (!queryResult.isSuccess()) {
//                System.out.println("Query was not understood; no results available.");
//            } else {
//                // Got a result.
//                System.out.println("Successful query. Pods follow:\n");
//                for (WAPod pod : queryResult.getPods()) {
//                    if (!pod.isError()) {
//                        System.out.println(pod.getTitle());
//                        System.out.println("------------");
//                        for (WASubpod subpod : pod.getSubpods()) {
//                            for (Object element : subpod.getContents()) {
//                                if (element instanceof WAPlainText) {
//                                    System.out.println(((WAPlainText) element).trainMarkovChains());
//                                    System.out.println("");
//                                }
//                            }
//                        }
//                        System.out.println("");
//                    }
//                }
//            }
//        }
//
//        catch (WAException e){
//            e.printStackTrace();
//        }
//    }
//}
