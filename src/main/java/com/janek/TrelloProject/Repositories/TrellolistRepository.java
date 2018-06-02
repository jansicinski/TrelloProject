package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trellolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrellolistRepository extends JpaRepository<Trellolist, Long> {

}
