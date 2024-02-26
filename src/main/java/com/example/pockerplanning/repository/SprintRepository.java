package com.example.pockerplanning.repository;

import com.example.pockerplanning.entities.Analyse;
import com.example.pockerplanning.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

    @Query("Select a from Sprint a where a.projet.id=:id_projet")
    List<Sprint> getprojet_par_sprint(@Param("id_projet") int id_projet);
}
