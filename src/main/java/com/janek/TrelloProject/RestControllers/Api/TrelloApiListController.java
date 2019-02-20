package com.janek.TrelloProject.RestControllers.Api;

import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Entities.Trellolist;
import com.janek.TrelloProject.Repositories.TrelloboardRepository;
import com.janek.TrelloProject.Repositories.TrellolistRepository;
import com.janek.TrelloProject.Utils.TrelloApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("TrelloApi/lists/")
@AllArgsConstructor
public class TrelloApiListController {

    private final TrelloApi trelloApi;

    private final TrelloboardRepository trelloboardRepository;

    private final TrellolistRepository trellolistRepository;

    @GetMapping("")
    public String getAllMyLists(){
        return trelloApi.getMyListIds().toString();
    }

    @PostMapping("saveAllToDb")
    public List<Trellolist> saveAllListsToDb(){
        Trelloboard trelloboard = null;
        for(Map.Entry<String,String> listIdBoardId : trelloApi.getMyListIds().entrySet()){
            if(trelloboard == null || !trelloboard.getBoardId().equals(listIdBoardId.getValue()))
                trelloboard = trelloboardRepository.findByBoardId(listIdBoardId.getValue()).get();
            Trellolist trellolist = Trellolist.builder().listId(listIdBoardId.getKey()).build();
            trellolist.setTrelloboard(trelloboard);
            trellolistRepository.save(trellolist);
        }
        return trellolistRepository.findAll();
    }

}