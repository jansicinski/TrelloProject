package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trellolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrellolistRepository extends JpaRepository<Trellolist, Long> {
    List<Trellolist> findAll();
    Trellolist findById(int id);
    List<Trellolist> saveAll(List<Trellolist> boards);
}
