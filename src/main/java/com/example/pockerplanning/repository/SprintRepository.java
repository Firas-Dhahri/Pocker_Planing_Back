package com.example.pockerplanning.repository;

import com.example.pockerplanning.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
    @Query("SELECT s.id FROM Sprint s WHERE s.name = :sprintName")
    Long getSprintIdByName(@Param("sprintName") String sprintName);

    boolean existsByName(String name);
}
