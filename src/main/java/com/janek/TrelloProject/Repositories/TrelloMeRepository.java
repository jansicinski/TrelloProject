package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.TrelloMe;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TrelloMeRepository extends JpaRepository<TrelloMe, Long> {

}
