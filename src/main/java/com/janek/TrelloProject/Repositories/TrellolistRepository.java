package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trellolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrellolistRepository extends JpaRepository<Trellolist, Long> {

    Optional<Trellolist> findByListId(String list_id);
    void deleteTrellolistByListId(String list_id);

}
