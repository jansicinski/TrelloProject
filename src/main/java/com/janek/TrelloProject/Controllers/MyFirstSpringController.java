package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Enums.TrelloObjectGroup;
import com.janek.TrelloProject.Enums.TrelloObjectId;
import com.janek.TrelloProject.Utils.TrelloAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("myTrello/")
public class MyFirstSpringController {

    @Autowired
    TrelloAPI trelloAPI;

    @PutMapping("card/{id}")
    public String updateCard(){
        return null;
    }

    @PostMapping("card/{description}")
    public String createCard(){
        return null;
    }

    @DeleteMapping("card/{id}")
    public String deleteCard(){
        return null;
    }

    @GetMapping("boards")
    public String getAllMyBoards(){
        return getMyBoardIds().toString();
    }

    @GetMapping("lists")
    public String getAllMyLists(){
        return getMyListIds().toString();
    }

    @GetMapping("cards")
    public String getAllMyCards(){
        return getMyCardIds().toString();
    }

    private ArrayList<String> getMyBoardIds(){
        HashMap<String,Object> me = trelloAPI.getObjectHashMap(TrelloObjectGroup.MEMBERS.toString().toLowerCase(),
                TrelloObjectId.ME.toString().toLowerCase());
        return (ArrayList<String>)me.get("idBoards");
    }

    private ArrayList<String> getMyListIds(){
        ArrayList<String> myListIds = new ArrayList<>();
        for(String boardId : getMyBoardIds()){
            HashMap<String,Object>[] lists = trelloAPI.getObjectHashMap(TrelloObjectGroup.BOARDS.toString().toLowerCase(),
                    boardId,
                    TrelloObjectId.LISTS.toString().toLowerCase());
            for(HashMap<String, Object> list : lists){
                myListIds.add((String)list.get("id"));
            }
        }
        return myListIds;
    }

    private ArrayList<String> getMyCardIds(){
        ArrayList<String> myCardIds = new ArrayList<>();
        for(String listId : getMyListIds()){
            HashMap<String,Object>[] cards = trelloAPI.getObjectHashMap(TrelloObjectGroup.LISTS.toString().toLowerCase(),
                                                                        listId,
                                                                        TrelloObjectId.CARDS.toString().toLowerCase());
            for(HashMap<String, Object> card : cards){
                myCardIds.add((String)card.get("id"));
            }
        }
        return myCardIds;
    }

}
