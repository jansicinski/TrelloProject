package com.janek.TrelloProject.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("TrelloDb/lists/")
public class TrelloDbListController {

    @PutMapping("{id}")
    public String updateList(@PathVariable int id){
        // TODO: 6/2/2018 PutMapping TrelloDbListController.updateList
        return null;
    }

    @PostMapping("{description}")
    public String createList(@PathVariable String description){
        // TODO: 6/2/2018 PostMapping TrelloDbListController.createList
        return null;
    }

    @DeleteMapping("{id}")
    public String deleteList(@PathVariable int id){
        // TODO: 6/2/2018 DeleteMapping TrelloDbListController.deleteList
        return null;
    }

    @GetMapping("{id}")
    public String getList(@PathVariable int id){
        // TODO: 6/2/2018 GetMapping TrelloDbListController.getList
        return null;
    }

    @GetMapping("")
    public String getAllMyLists(){
        // TODO: 6/2/2018 GetMapping TrelloDbListController.getAllMyLists
        return null;
    }

}
