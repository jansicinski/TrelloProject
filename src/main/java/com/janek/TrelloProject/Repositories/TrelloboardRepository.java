package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trelloboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrelloboardRepository extends JpaRepository<Trelloboard, Long> {
    Optional<Trelloboard> findByBoard_id(String board_id);
    void deleteTrelloboardByBoard_id(String board_id);
}
