package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trellocard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrellocardRepository extends JpaRepository<Trellocard, Long> {

    Optional<Trellocard> findByCardId(String card_id);
    void deleteTrellocardByCardId(String card_id);

}
