package com.janek.TrelloProject.Services;

import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Repositories.TrelloboardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TrelloboardService {

    private final TrelloboardRepository trelloboardRepository;


    public boolean delete(String boardId){
        if(trelloboardRepository.findByBoardId(boardId).isPresent()) {
            trelloboardRepository.deleteTrelloboardByBoardId(boardId);
            return true;
        } else
            return false;
    }

    public Trelloboard update(Trelloboard trelloboard){
        return updateOrCreate(trelloboard);
    }

    public Trelloboard create(Trelloboard trelloboard){
        return updateOrCreate(trelloboard);
    }

    public Trelloboard read(String boardId){
        Optional<Trelloboard> trelloboardOptional = trelloboardRepository.findByBoardId(boardId);
        return trelloboardOptional.orElse(null);
    }

    public List<Trelloboard> read(){
        List<Trelloboard> trelloboards = trelloboardRepository.findAll();
        if (trelloboards.size() > 0) {
            return trelloboards;
        } else
            return null;
    }

    private Trelloboard updateOrCreate(Trelloboard trelloboard){
        trelloboard = trelloboardRepository.save(trelloboard);
        Optional<Trelloboard> trelloboardOptional = trelloboardRepository.findByBoardId(trelloboard.getBoardId());
        return trelloboardOptional.orElse(null);
    }

}
