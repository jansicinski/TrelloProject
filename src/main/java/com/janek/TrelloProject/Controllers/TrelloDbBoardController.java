package com.janek.TrelloProject.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("TrelloDb/cards/")
public class TrelloDbBoardController {

    @PutMapping("{id}")
    public String updateBoard(@PathVariable int id){
        // TODO: 6/2/2018 PutMapping TrelloDbBoardController.updateBoard
        return null;
    }

    @PostMapping("{description}")
    public String createBoard(@PathVariable String description){
        // TODO: 6/2/2018 PostMapping TrelloDbBoardController.createBoard
        return null;
    }

    @DeleteMapping("{id}")
    public String deleteBoard(@PathVariable int id){
        // TODO: 6/2/2018 DeleteMapping TrelloDbBoardController.deleteBoard
        return null;
    }

    @GetMapping("{id}")
    public String getBoard(@PathVariable int id){
        // TODO: 6/2/2018 GetMapping TrelloDbBoardController.getBoard
        return null;
    }

    @GetMapping("")
    public String getAllMyBoards(){
        // TODO: 6/2/2018 GetMapping TrelloDbBoardController.getAllMyBoards
        return null;
    }

}
