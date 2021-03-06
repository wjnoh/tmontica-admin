package com.internship.tmontica_admin.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.tmontica_admin.util.exception.UtilException;
import com.internship.tmontica_admin.util.exception.UtilExceptionType;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();
    public static String getJsonElementValue(String json, String key) {

        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(json);
        } catch (IOException e){
            e.printStackTrace();
            throw new UtilException(UtilExceptionType.JSON_PARSING_EXCEPTION);
        }

        JsonNode keyNode = jsonNode.get(key);
        return keyNode.textValue();
    }

//    public static void main(String[] args){
//
//        System.out.println(getJsonElementValue("{\"id\":\"useridid\"}", "id"));
//    }
}
