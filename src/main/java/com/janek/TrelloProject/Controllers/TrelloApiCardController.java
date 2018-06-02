package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Repositories.TrellocardRepository;
import com.janek.TrelloProject.Utils.TrelloApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("TrelloApi/cards/")
public class TrelloApiCardController {

    @Autowired
    TrelloApi trelloApi;

    @Autowired
    TrellocardRepository trellocardRepository;

    @PutMapping("{id}")
    public String updateCard(@PathVariable int id){
        // TODO: 6/2/2018 PutMapping TrelloApiCardController.updateCard
        return null;
    }

    @PostMapping("{description}")
    public String createCard(@PathVariable String description){
        // TODO: 6/2/2018 PostMapping TrelloApiCardController.createCard
        return null;
    }

    @PostMapping("saveAllToDb")
    public String saveAllCardsToDb(){
        // TODO: 6/2/2018 PostMapping TrelloApiCardController.saveAllCardsToDb
        return null;
    }

    @DeleteMapping("{id}")
    public String deleteCard(@PathVariable int id){
        // TODO: 6/2/2018 DeleteMapping TrelloApiCardController.deleteCard
        return null;
    }

    @GetMapping("{id}")
    public String getCard(@PathVariable int id){
        // TODO: 6/2/2018 GetMapping TrelloApiCardController.getCard
        return null;
    }

    @GetMapping("")
    public String getAllMyCards(){
        return trelloApi.getMyCardIds().toString();
    }

}
