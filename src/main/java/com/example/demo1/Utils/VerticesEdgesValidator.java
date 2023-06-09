package com.example.demo1.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cristescu Cristian
 * This class is used to validate the edges inserted into the text area.
 */
public class VerticesEdgesValidator {
    /**
     * This method takes a string s, and verifies if the string has the structure: "string1"-"string2"
     * @param s the string that needs to be validated
     * @return true if the string structure is valid, false otherwise
     */
    public boolean validate(String s){
        String[] lines = s.split("\n");
        for(String line : lines){
            String[] rows = line.split("-");
            if(rows.length != 2){
                return false;
            }
        }
        Map<String, String> edges = new HashMap<>();
        for(String line : lines){
            String[] rows = line.split("-");
            if(countOccurrences(rows[0], rows[1], edges)>0){
                return false;
            }
            edges.put(rows[0], rows[1]);
        }
        for(String edge : edges.keySet()){
            if(countOccurrences(edge, edges.get(edge), edges)>1){
                return false;
            }
        }
        return true;
    }

    /**
     * This private method counts the occurrences of an edge from, and helps in the validation process
     * @param key this is the source of the edge
     * @param value this is the target of the edge
     * @param edges this is the list of edges
     * @return return the number of occurrences of the given edge
     */
    private int countOccurrences(String key, String value, Map<String, String> edges){
        int count = 0;
        for(String s : edges.keySet()){
            if(s.equals(key) && value.equals(edges.get(key))){
                count++;
            }
        }
        return count;
    }
}
