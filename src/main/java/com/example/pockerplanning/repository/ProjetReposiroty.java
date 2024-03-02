package com.example.pockerplanning.repository;

import com.example.pockerplanning.entities.Analyse;
import com.example.pockerplanning.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface    ProjetReposiroty extends JpaRepository<Projet, Integer> {



}
