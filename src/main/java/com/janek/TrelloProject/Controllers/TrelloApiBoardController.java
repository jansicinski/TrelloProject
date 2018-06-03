package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Repositories.TrelloboardRepository;
import com.janek.TrelloProject.Utils.TrelloApi;
import javafx.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("TrelloApi/boards/")
public class TrelloApiBoardController {

    private final TrelloApi trelloApi;

    private final TrelloboardRepository trelloboardRepository;

    public TrelloApiBoardController(TrelloApi trelloApi, TrelloboardRepository trelloboardRepository) {
        this.trelloApi = trelloApi;
        this.trelloboardRepository = trelloboardRepository;
    }

    @PostMapping("saveAllToDb")
    public List<Trelloboard> saveAllBoardsToDb(){
        ArrayList<Trelloboard> boards = new ArrayList<>();
        for(Pair<String, String> boardId : trelloApi.getMyBoardIds()){
            boards.add(Trelloboard.builder().boardId(boardId.getKey()).name(boardId.getValue()).build());
        }

        return trelloboardRepository.saveAll(boards);
    }

    @GetMapping("")
    public List<Trelloboard> getAllMyBoards(){
        ArrayList<Trelloboard> boards = new ArrayList<>();
        for(Pair<String, String>  boardId : trelloApi.getMyBoardIds()){
            boards.add(Trelloboard.builder().boardId(boardId.getKey()).build());
        }
        return boards;
    }

}


//    @PostMapping("saveAllToDb")
//    public List<Trelloboard> saveAllBoardsToDb(){
//        // TODO: 6/2/2018 make it work
//        ArrayList<Trelloboard> boards = new ArrayList<>();
//        for(String boardId : trelloApi.getMyBoardIds()){
////            Map<String, String> trelloListIds = trelloApi.getMyListIds()
////                                                            .entrySet()
////                                                            .stream()
////                                                            .filter(e -> e.getValue().equals(boardId))
////                                                            .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
////            List<Trellolist> trellolistsList = new ArrayList<>();
////            for(Map.Entry<String, String> listId : trelloListIds.entrySet()){
////                trellolistsList.add(new Trellolist(listId.getKey(), new Trelloboard(boardId, null), null));
////            }
////            boards.add(new Trelloboard(boardId,null));
//            boards.add(Trelloboard.builder().boardId(boardId).build());
////            boards.add(new Trelloboard(boardId,trellolistsList));
////            trelloboardRepository.save(new Trelloboard(boardId, trellolistsList));
//
////            return trelloboardRepository.findAll();
//        }
////        List<Trelloboard> trelloboards =
//        return trelloboardRepository.saveAll(boards);
////        for(Trelloboard trelloboard : trelloboards){
////            trelloboard.setTrellolists();
////        }
////        return trelloboardRepository.findAll();
//    }

//            boards.add(new Trelloboard(boardId,null));
