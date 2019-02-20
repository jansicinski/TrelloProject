package com.janek.TrelloProject.RestControllers.Api;

import com.janek.TrelloProject.Entities.Trellocard;
import com.janek.TrelloProject.Entities.Trellolist;
import com.janek.TrelloProject.Repositories.TrellocardRepository;
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
@RequestMapping("TrelloApi/cards/")
@AllArgsConstructor
public class TrelloApiCardController {

    private final TrelloApi trelloApi;

    private final TrellolistRepository trellolistRepository;

    private final TrellocardRepository trellocardRepository;

    @GetMapping("")
    public String getAllMyCards(){
        return trelloApi.getMyCardIds().toString();
    }

    @PostMapping("saveAllToDb")
    public List<Trellocard> saveAllCardsToDb(){
        Trellolist trellolist = null;
        for(Map.Entry<String,String> cardIdlistId : trelloApi.getMyCardIds().entrySet()){
            if(trellolist == null || !trellolist.getListId().equals(cardIdlistId.getValue()))
                trellolist = trellolistRepository.findByListId(cardIdlistId.getValue()).get();
            Trellocard trellocard = Trellocard.builder().cardId(cardIdlistId.getKey()).build();
            trellocard.setTrellolist(trellolist);
            trellocardRepository.save(trellocard);
        }
        return trellocardRepository.findAll();
    }

}
