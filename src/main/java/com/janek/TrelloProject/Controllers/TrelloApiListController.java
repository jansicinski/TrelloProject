package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Utils.TrelloApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("TrelloApi/lists/")
public class TrelloApiListController {

    @Autowired
    TrelloApi trelloApi;

    @GetMapping("")
    public String getAllMyLists(){
        return trelloApi.getMyListIds().toString();
    }

}
