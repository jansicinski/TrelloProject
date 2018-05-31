package com.janek.TrelloProject.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janek.TrelloProject.Exceptions.NullTrelloObjectIdException;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) //PRIVATE
@AllArgsConstructor
@ToString
public class TrelloAPI {
    @Value("${trelloproject.key}")
    private  String key;

    @Value("${trelloproject.token}")
    private  String token;

    @Value("${trelloproject.base}")
    private  String trelloAPIBaseURL;

    private  String buildTrelloApiUrl(String trelloObjectGroup, String trelloObjectId, String trelloSecondaryObject){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", key);
        parameters.put("token", token);
        if(trelloSecondaryObject.equals("lists")){
            parameters.put("lists", "all");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(trelloAPIBaseURL);
        stringBuilder.append("/");
        stringBuilder.append(trelloObjectGroup);
        if(trelloObjectId != null) {
            stringBuilder.append("/");
            stringBuilder.append(trelloObjectId);
        }
        if(trelloSecondaryObject != null){
            stringBuilder.append("/");
            stringBuilder.append(trelloSecondaryObject);
        }
        stringBuilder.append("?");
        String paramsString = null;
        try {
            paramsString = ParameterStringBuilder.getParamsString(parameters);
        } catch (Exception e){
            e.printStackTrace();
        }

        stringBuilder.append(paramsString);
        return stringBuilder.toString();
    }

    public  HashMap<String,Object> getObjectHashMap(String trelloObjectGroup, String trelloSecondaryObject){
        HashMap<String, Object> result = null;
        String objectString = getObjectString(trelloObjectGroup,null,trelloSecondaryObject);
        try {
            result = new ObjectMapper().readValue(objectString, HashMap.class);
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    private  String getObjectString (String trelloObjectGroup, String trelloObjectId, String trelloSecondaryObject){
        URL url = null;
        try{
            url = new URL(buildTrelloApiUrl(trelloObjectGroup,trelloObjectId,trelloSecondaryObject));
        } catch (Exception e){
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try{
            con = (HttpURLConnection) url.openConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            con.setRequestMethod("GET");
        } catch (Exception e){
            e.printStackTrace();
        }
        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (Exception e){
            e.printStackTrace();
        }
        String inputLine;
        StringBuffer content = new StringBuffer();
        try {
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        String result = content.toString();
        return result;
    }

    public  HashMap<String,Object>[] getObjectHashMap(String trelloObjectGroup, String trelloObjectId, String trelloSecondaryObject){
        try {
            if (trelloObjectId == null || trelloObjectId.isEmpty()) {
                throw new NullTrelloObjectIdException("trelloObjectId cannot be null!");
            }
        } catch (NullTrelloObjectIdException e){
            e.printStackTrace();
        }

        HashMap<String, Object>[] resultArray = null;
        try {
            resultArray = new ObjectMapper().readValue(getObjectString(trelloObjectGroup,trelloObjectId,trelloSecondaryObject), HashMap[].class);
        } catch (Exception e){
            e.printStackTrace();
        }

        return resultArray;
    }
}
