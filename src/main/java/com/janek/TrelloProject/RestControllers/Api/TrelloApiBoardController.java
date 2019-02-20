package com.janek.TrelloProject.RestControllers.Api;

import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Repositories.TrelloboardRepository;
import com.janek.TrelloProject.Utils.TrelloApi;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("TrelloApi/boards/")
@AllArgsConstructor
public class TrelloApiBoardController {

    private final TrelloApi trelloApi;

    private final TrelloboardRepository trelloboardRepository;

    @PostMapping("saveAllToDb")
    public List<Trelloboard> saveAllBoardsToDb(){
        return trelloboardRepository.saveAll(buildBoards());
    }

    @GetMapping("")
    public List<Trelloboard> getAllMyBoards(){
        return buildBoards();
    }

    private ArrayList<Trelloboard> buildBoards(){
        ArrayList<Trelloboard> boards = new ArrayList<>();
        for(Pair<String, String> boardId : trelloApi.getMyBoardIds()){
            boards.add(Trelloboard.builder().boardId(boardId.getKey()).name(boardId.getValue()).build());
        }
        return boards;
    }

}