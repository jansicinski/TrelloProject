package com.janek.TrelloProject.Services;

import com.janek.TrelloProject.Entities.Trellocard;
import com.janek.TrelloProject.Repositories.TrellocardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrellocardService {

    private final TrellocardRepository trellocardRepository;

    public TrellocardService(TrellocardRepository trellocardRepository) {
        this.trellocardRepository = trellocardRepository;
    }

    public boolean delete(String cardId){
        if(trellocardRepository.findByCardId(cardId).isPresent()) {
            trellocardRepository.deleteTrellocardByCardId(cardId);
            return true;
        } else
            return false;
    }

    public Trellocard update(Trellocard trellocard){
        trellocard = trellocardRepository.save(trellocard);
        Optional<Trellocard> trellocardOptional = trellocardRepository.findByCardId(trellocard.getCardId());
        if (trellocardOptional.isPresent()) {
            return trellocardOptional.get();
        } else
            return null;
    }

    public Trellocard create(Trellocard trellocard){
        trellocard = trellocardRepository.save(trellocard);
        Optional<Trellocard> trellocardOptional = trellocardRepository.findByCardId(trellocard.getCardId());
        if (trellocardOptional.isPresent()) {
            return trellocardOptional.get();
        } else
            return null;
    }

    public Trellocard read(String cardId){
        Optional<Trellocard> trellocardOptional = trellocardRepository.findByCardId(cardId);
        if (trellocardOptional.isPresent()) {
            return trellocardOptional.get();
        } else
            return null;
    }

    public List<Trellocard> read(){
        List<Trellocard> trellocards = trellocardRepository.findAll();
        if (trellocards.size() > 0) {
            return trellocards;
        } else
            return null;
    }

}
