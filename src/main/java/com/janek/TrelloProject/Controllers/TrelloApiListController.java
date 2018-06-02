package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Utils.TrelloApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("TrelloApi/lists/")
public class TrelloApiListController {

    @Autowired
    TrelloApi trelloApi;

    @PostMapping("saveAllToDb")
    public String saveAllListsToDb(){
        // TODO: 6/2/2018 PostMapping TrelloApiListController.saveAllListsToDb
        return null;
    }

    @GetMapping("")
    public String getAllMyLists(){
        return trelloApi.getMyListIds().toString();
    }

}
