package com.janek.TrelloProject.Services;

import com.janek.TrelloProject.Entities.Trellolist;
import com.janek.TrelloProject.Repositories.TrellolistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TrellolistService {

    private final TrellolistRepository trellolistRepository;

    public boolean delete(String boardId){
        if(trellolistRepository.findByListId(boardId).isPresent()) {
            trellolistRepository.deleteTrellolistByListId(boardId);
            return true;
        } else
            return false;
    }

    public Trellolist update(Trellolist trellolist){
        return updateOrCreate(trellolist);
    }

    public Trellolist create(Trellolist trellolist){
        return updateOrCreate(trellolist);
    }

    public Trellolist read(String listId){
        Optional<Trellolist> trellolistOptional = trellolistRepository.findByListId(listId);
        return trellolistOptional.orElse(null);
    }

    public List<Trellolist> read(){
        List<Trellolist> trellolists = trellolistRepository.findAll();
        if (trellolists.size() > 0) {
            return trellolists;
        } else
            return null;
    }

    private Trellolist updateOrCreate(Trellolist trellolist){
        trellolist = trellolistRepository.save(trellolist);
        Optional<Trellolist> trellolistOptional = trellolistRepository.findByListId(trellolist.getListId());
        return trellolistOptional.orElse(null);
    }

}
