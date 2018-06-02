package com.janek.TrelloProject.Repositories;

import com.janek.TrelloProject.Entities.Trellome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrellomeRepository extends JpaRepository<Trellome, Long> {

}
