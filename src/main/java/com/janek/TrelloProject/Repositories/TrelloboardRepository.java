package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trelloboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrelloboardRepository extends JpaRepository<Trelloboard, Long> {

}
