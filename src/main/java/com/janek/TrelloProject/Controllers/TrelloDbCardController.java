package com.janek.TrelloProject.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("TrelloDb/cards/")
public class TrelloDbCardController {

    @PutMapping("{id}")
    public String updateCard(@PathVariable int id){
        // TODO: 6/2/2018 PutMapping TrelloDbCardController.updateCard
        return null;
    }

    @PostMapping("{description}")
    public String createCard(@PathVariable String description){
        // TODO: 6/2/2018 PostMapping TrelloDbCardController.createCard
        return null;
    }

    @DeleteMapping("{id}")
    public String deleteCard(@PathVariable int id){
        // TODO: 6/2/2018 DeleteMapping TrelloDbCardController.deleteCard
        return null;
    }

    @GetMapping("{id}")
    public String getCard(@PathVariable int id){
        // TODO: 6/2/2018 GetMapping TrelloDbCardController.getCard
        return null;
    }

    @GetMapping("")
    public String getAllMyCards(){
        // TODO: 6/2/2018 GetMapping TrelloDbCardController.getAllMyCards
        return null;
    }

}
