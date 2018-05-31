package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.TrelloList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrelloListRepository extends JpaRepository<TrelloList, Long> {
}
