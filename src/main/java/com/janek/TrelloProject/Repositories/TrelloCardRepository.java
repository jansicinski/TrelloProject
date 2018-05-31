package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.TrelloCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrelloCardRepository extends JpaRepository<TrelloCard, Long> {
}
