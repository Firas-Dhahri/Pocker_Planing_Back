package com.example.pockerplanning.entities;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String objectif;
    private Date date_debut;
    private Date date_fin;
    private float duree;
    private String contraintes;
    private String exigences;
    private String dependance;
    private float budget;
    private int status;

    @JsonIgnore
    @ManyToMany
    private List<Algorithme> algorithmes;

    @ManyToOne
    Equipe equipe;

    //relation avec sprint
    @OneToMany(cascade = CascadeType.ALL, mappedBy="projet")
    private List<Sprint> Sprints;


}