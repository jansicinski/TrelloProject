package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trellocard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrellocardRepository extends JpaRepository<Trellocard, Long> {

}
