package com.janek.TrelloProject.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janek.TrelloProject.Enums.TrelloObject;
import com.janek.TrelloProject.Errors.NullTrelloObjectIdException;
import javafx.util.Pair;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.janek.TrelloProject.Enums.TrelloObject.*;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) //PRIVATE
@AllArgsConstructor
@ToString
public class TrelloApi {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloApi.class);

    @Value("${trelloproject.key}")
    private String key;

    @Value("${trelloproject.token}")
    private String token;

    @Value("${trelloproject.base}")
    private String trelloAPIBaseURL;

    private String buildTrelloApiUrl(String trelloObjectGroup, String trelloObjectId, String trelloSecondaryObject){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", key);
        parameters.put("token", token);
        if(trelloSecondaryObject != null) {
            if (trelloSecondaryObject.equals("lists")) {
                parameters.put("lists", "all");
            }
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

    private HashMap<String,Object> getObjectHashMap(TrelloObject trelloObjectGroup, TrelloObject trelloSecondaryObject){
        HashMap<String, Object> result = null;
        String objectString = getObjectString(trelloObjectGroup.toString().toLowerCase(),
                                   null,
                                              trelloSecondaryObject.toString().toLowerCase());
        try {
            result = new ObjectMapper().readValue(objectString, HashMap.class);
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    private HashMap<String,Object> getObjectHashMap(TrelloObject trelloObjectGroup, String trelloObjectId){
        HashMap<String, Object> result = null;
        String objectString = getObjectString(trelloObjectGroup.toString().toLowerCase(),
                trelloObjectId,
                null);
        try {
            result = new ObjectMapper().readValue(objectString, HashMap.class);
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    private String getObjectString (String trelloObjectGroup, String trelloObjectId, String trelloSecondaryObject){
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

        return content.toString();
    }

    private HashMap<String,Object>[] getObjectHashMap(TrelloObject trelloObjectGroup,
                                                      String trelloObjectId,
                                                      TrelloObject trelloSecondaryObject){
        try {
            if (trelloObjectId == null) {
                throw new NullTrelloObjectIdException("trelloObjectId cannot be null!");
            }
        } catch (NullTrelloObjectIdException e){
            e.printStackTrace();
        }

        HashMap<String, Object>[] resultArray = null;
        try {
            resultArray = new ObjectMapper().readValue(getObjectString(trelloObjectGroup.toString().toLowerCase(),
                                                                       trelloObjectId,
                                                                       trelloSecondaryObject.toString().toLowerCase()),
                                                                       HashMap[].class);
        } catch (Exception e){
            e.printStackTrace();
        }

        return resultArray;
    }

    public ArrayList<Pair<String, String>> getMyBoardIds(){
        HashMap<String,Object> me = getObjectHashMap(MEMBERS,ME);
        ArrayList<String> boardIds = (ArrayList<String>)me.get("idBoards");
        ArrayList<Pair<String, String>> result = new ArrayList<>();
        for(String boardId : boardIds){
            result.add(new Pair<>(boardId, (String) getObjectHashMap(BOARDS, boardId).get("name") ));
        }

        return result;
    }

    public Map<String, String> getMyListIds(){
        Map<String, String> myListIds = new HashMap<>();
        for(Pair<String, String> boardId : getMyBoardIds()){
            HashMap<String,Object>[] lists = getObjectHashMap(BOARDS,boardId.getKey(),LISTS);
            for(HashMap<String, Object> list : lists){
                myListIds.put((String)list.get("id"), boardId.getKey());
            }
        }
        return myListIds;
    }

    public Map<String, String> getMyCardIds(){
        Map<String, String> myCardIds = new HashMap<>();
        for(Map.Entry<String, String> listId : getMyListIds().entrySet()){
            HashMap<String,Object>[] cards = getObjectHashMap(LISTS,listId.getKey(),CARDS);
            for(HashMap<String, Object> card : cards){
                myCardIds.put((String)card.get("id"), listId.getKey());
            }
        }
        return myCardIds;
    }

}
