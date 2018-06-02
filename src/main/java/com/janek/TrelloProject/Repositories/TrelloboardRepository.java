package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trelloboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrelloboardRepository extends JpaRepository<Trelloboard, Long> {
    List<Trelloboard> findAll();
    Trelloboard findById(int id);
    List<Trelloboard> saveAll(List<Trelloboard> boards);
}
