package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trelloboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrelloboardRepository extends JpaRepository<Trelloboard, Long> {

    Optional<Trelloboard> findByBoardId(String board_id);
    void deleteTrelloboardByBoardId(String board_id);

}
