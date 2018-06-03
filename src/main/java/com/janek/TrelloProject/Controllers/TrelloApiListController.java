package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Entities.Trellolist;
import com.janek.TrelloProject.Repositories.TrelloboardRepository;
import com.janek.TrelloProject.Repositories.TrellolistRepository;
import com.janek.TrelloProject.Utils.TrelloApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("TrelloApi/lists/")
public class TrelloApiListController {

    private final TrelloApi trelloApi;

    private final TrelloboardRepository trelloboardRepository;
    private final TrellolistRepository trellolistRepository;

    public TrelloApiListController(TrelloApi trelloApi, TrellolistRepository trellolistRepository, TrelloboardRepository trelloboardRepository) {
        this.trelloApi = trelloApi;
        this.trellolistRepository = trellolistRepository;
        this.trelloboardRepository = trelloboardRepository;
    }

    @GetMapping("")
    public String getAllMyLists(){
        return trelloApi.getMyListIds().toString();
    }

    @PostMapping("saveAllToDb")
    @Transactional
    public List<Trellolist> saveAllListsToDb(){
        // TODO: 6/2/2018 make it work 
//        ArrayList<Trellolist> trellolists = new ArrayList<>();

//        Map<String, String> myMap = new HashMap<>();
//        for(Map.Entry<String,String> listId : trelloApi.getMyListIds().entrySet()){
//
//            myMap.put(listId.getKey(), listId.getValue());
//
//        }
//            trellolists.add(new Trellolist(listId.getKey(), null, null));
         //Trellolist trellolist = Trellolist.builder().listId(myMap.getKey()).build();
           // trellolists.add(trellolist);
        Trelloboard trelloboard =  trelloboardRepository.findByBoardId("5a54de08b62cff803bd7f42b").get();
        Trellolist trellolist = Trellolist.builder().listId("5a54de08b62cff803bd7f42e").build();
        trellolist.setTrelloboard(trelloboard);
         trellolistRepository.save(trellolist);
            System.out.println("asdf");

//        return trellolistRepository.saveAll(trellolists);
        //save board to DB with null list
        //
        return null;
    }

}
