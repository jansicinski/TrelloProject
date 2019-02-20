package com.janek.TrelloProject.Services;

import com.janek.TrelloProject.Entities.Trellocard;
import com.janek.TrelloProject.Repositories.TrellocardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TrellocardService {

    private final TrellocardRepository trellocardRepository;

    public boolean delete(String cardId){
        if(trellocardRepository.findByCardId(cardId).isPresent()) {
            trellocardRepository.deleteTrellocardByCardId(cardId);
            return true;
        } else
            return false;
    }

    public Trellocard update(Trellocard trellocard){
        return updateOrCreate(trellocard);
    }

    public Trellocard create(Trellocard trellocard){
        return updateOrCreate(trellocard);
    }

    public Trellocard read(String cardId){
        Optional<Trellocard> trellocardOptional = trellocardRepository.findByCardId(cardId);
        return trellocardOptional.orElse(null);
    }

    public List<Trellocard> read(){
        List<Trellocard> trellocards = trellocardRepository.findAll();
        if (trellocards.size() > 0) {
            return trellocards;
        } else
            return null;
    }

    private Trellocard updateOrCreate(Trellocard trellocard){
        trellocard = trellocardRepository.save(trellocard);
        Optional<Trellocard> trellocardOptional = trellocardRepository.findByCardId(trellocard.getCardId());
        return trellocardOptional.orElse(null);
    }

}
