package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trellocard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrellocardRepository extends JpaRepository<Trellocard, Long> {
    List<Trellocard> findAll();
    Trellocard findById(int id);
    List<Trellocard> saveAll(List<Trellocard> boards);
}
