package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Repositories.TrelloboardRepository;
import com.janek.TrelloProject.Utils.TrelloApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("TrelloApi/boards/")
public class TrelloApiBoardController {

    @Autowired
    TrelloApi trelloApi;

    @Autowired
    TrelloboardRepository trelloboardRepository;

    @PutMapping("{id}")
    public String updateBoard(@PathVariable int id){
        // TODO: 6/2/2018 PutMapping TrelloApiBoardController.updateBoard
        return null;
    }

    @PostMapping("{description}")
    public String createBoard(@PathVariable String description){
        // TODO: 6/2/2018 PostMapping TrelloApiBoardController.createBoard
        return null;
    }

    @DeleteMapping("{id}")
    public String deleteBoard(@PathVariable int id){
        // TODO: 6/2/2018 DeleteMapping TrelloApiBoardController.deleteBoard
        return null;
    }

    @GetMapping("{id}")
    public String getBoard(@PathVariable int id){
        // TODO: 6/2/2018 GetMapping TrelloApiBoardController.getBoard
        return null;
    }

    @PostMapping("saveAllToDb")
    public List<Trelloboard> saveAllBoardsToDb(){
        ArrayList<Trelloboard> boards = new ArrayList<>();
        for(String boardId : trelloApi.getMyBoardIds()){
            boards.add(new Trelloboard(null,boardId,null));
        }
        return trelloboardRepository.saveAll(boards);
    }

    @GetMapping("")
    public List<Trelloboard> getAllMyBoards(){
        ArrayList<Trelloboard> boards = new ArrayList<>();
        for(String boardId : trelloApi.getMyBoardIds()){
            boards.add(new Trelloboard(null,boardId,null));
        }
        return boards;
    }

}
