package com.janek.TrelloProject.RestControllers.Api;

import com.janek.TrelloProject.Utils.TrelloApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("TrelloApi/cards/")
public class TrelloApiCardController {

    @Autowired
    TrelloApi trelloApi;

    @GetMapping("")
    public String getAllMyCards(){
        return trelloApi.getMyCardIds().toString();
    }

}
