package com.janek.TrelloProject.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MyFirstSpringControllerTest {

    @Test
    public void me() throws Exception{

        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", "89b04a6238e8705a5fad7a5a3462a2f8");
        parameters.put("token", "dbb7b0bef8bf41638301238d4702d28a3bc23fe79605e2775cbd1af3806243a4");

//        URL url = new URL("https://api.trello.com/1/members/me/?" + ParameterStringBuilder.getParamsString(parameters));
        String trelloAPIBaseURL = "https://api.trello.com/1";
        String trelloObjectGroup = "boards";
        String trelloObjectID = "t0skLxwd";

//        String trelloSecondaryObject = null;
        String trelloSecondaryObject = "lists";

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
        stringBuilder.append(ParameterStringBuilder.getParamsString(parameters));
        if(trelloSecondaryObject.equals("lists")){
            stringBuilder.append("&lists=all");
        }

        String trelloFullURL = stringBuilder.toString();
        URL url = new URL(trelloFullURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        System.out.println(content);

        HashMap<String,Object> result = null;
        HashMap<String, Object>[] resultArray = null;
        if(trelloSecondaryObject == null){
            result = new ObjectMapper().readValue(content.toString(), HashMap.class);
        } else {
            resultArray = new ObjectMapper().readValue(content.toString(), HashMap[].class);
        }

        con.disconnect();

    }
}