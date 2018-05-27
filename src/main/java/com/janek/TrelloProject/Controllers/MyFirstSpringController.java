package com.janek.TrelloProject.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController("myTrello/")
public class MyFirstSpringController {

    @Value("${trelloproject.key}")
    private String key;

    @Value("${trelloproject.token}")
    private String token;

    @Value("${trelloproject.base}")
    private String trelloAPIBaseURL;

    private String buildTrelloApiUrl(String trelloObjectGroup, String trelloObjectID, String trelloSecondaryObject){
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
        stringBuilder.append("/");
        stringBuilder.append(trelloObjectID);
        if(trelloSecondaryObject != null){
            stringBuilder.append("/");
            stringBuilder.append(trelloSecondaryObject);
        }
        stringBuilder.append("?");
        String paramsString = null;
        try {
            paramsString = ParameterStringBuilder.getParamsString(parameters);
        } catch (Exception e){

        }
        stringBuilder.append(paramsString);
        return stringBuilder.toString();
    }

    private HashMap<String,Object> getObjectHashMap(String trelloObjectGroup, String trelloObjectID, String trelloSecondaryObject){
        URL url = null;
        try{
            url = new URL(buildTrelloApiUrl(trelloObjectGroup,trelloObjectID,trelloSecondaryObject));
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
            new BufferedReader(new InputStreamReader(con.getInputStream()));
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
//
//        HashMap<String, Object>[] resultArray = null;
//        try {
//            if (trelloSecondaryObject == null) {
//                resultArray = new HashMap<String, Object>[];
//                resultArray[0] = new ObjectMapper().readValue(content.toString(), HashMap.class);
//            } else {
//                resultArray = new ObjectMapper().readValue(content.toString(), HashMap[].class);
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return content.toString();
        return null;
    }

    @GetMapping("boards")
    private String getAllMyBoards(){
        //loop through boards
        return null;
    }

    @GetMapping("lists")
    public String getAllMyLists(){
        //loop through boards adding lists
        return null;
    }

}
