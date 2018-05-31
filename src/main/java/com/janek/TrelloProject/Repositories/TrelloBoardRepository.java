package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.TrelloBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrelloBoardRepository extends JpaRepository<TrelloBoard, Long> {
}
