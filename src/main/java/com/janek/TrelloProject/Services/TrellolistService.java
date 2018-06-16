package com.janek.TrelloProject.Services;

import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Entities.Trellolist;
import com.janek.TrelloProject.Repositories.TrelloboardRepository;
import com.janek.TrelloProject.Repositories.TrellolistRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrellolistService {

    private final TrellolistRepository trellolistRepository;

    public TrellolistService(TrellolistRepository trellolistRepository) {
        this.trellolistRepository = trellolistRepository;
    }

    public boolean delete(String boardId){
        if(trellolistRepository.findByListId(boardId).isPresent()) {
            trellolistRepository.deleteTrellolistByListId(boardId);
            return true;
        } else
            return false;
    }

    public Trellolist update(Trellolist trellolist){
        trellolist = trellolistRepository.save(trellolist);
        Optional<Trellolist> trellolistOptional = trellolistRepository.findByListId(trellolist.getListId());
        if (trellolistOptional.isPresent()) {
            return trellolistOptional.get();
        } else
            return null;
    }

    public Trellolist create(Trellolist trellolist){
        trellolist = trellolistRepository.save(trellolist);
        Optional<Trellolist> trellolistOptional = trellolistRepository.findByListId(trellolist.getListId());
        if (trellolistOptional.isPresent()) {
            return trellolistOptional.get();
        } else
            return null;
    }

    public Trellolist read(String listId){
        Optional<Trellolist> trellolistOptional = trellolistRepository.findByListId(listId);
        if (trellolistOptional.isPresent()) {
            return trellolistOptional.get();
        } else
            return null;
    }

    public List<Trellolist> read(){
        List<Trellolist> trellolists = trellolistRepository.findAll();
        if (trellolists.size() > 0) {
            return trellolists;
        } else
            return null;
    }

}
