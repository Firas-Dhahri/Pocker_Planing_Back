package com.example.pockerplanning.services.impl;

import com.example.pockerplanning.entities.Analyse;
import com.example.pockerplanning.entities.Projet;
import com.example.pockerplanning.entities.Sprint;
import com.example.pockerplanning.entities.Ticket;
import com.example.pockerplanning.repository.AnalyseRepository;
import com.example.pockerplanning.repository.ProjetReposiroty;
import com.example.pockerplanning.repository.SprintRepository;
import com.example.pockerplanning.repository.TicketRepository;
import com.example.pockerplanning.services.Interface.IAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyseService implements IAnalyseService {

    @Autowired
    AnalyseRepository analyseRepository;
    @Autowired
    ProjetReposiroty projetReposiroty;
    @Autowired
    SprintRepository sprintRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Override
    public Analyse ajouterAnalyse(Analyse analyse,int id_projet) {
           Projet p= projetReposiroty.findById(id_projet).orElse(null);
         //  Ticket t=ticketRepository.findById(id_ticket).orElse(null);

       analyse.setProjet(p);
        return analyseRepository.save(analyse);
    }
    @Override
    public Analyse ajouterAnalyse_Us(Analyse analyse, long id_ticket) {
        Ticket t=ticketRepository.findById(id_ticket).orElse(null);
    analyse.setTicket((t));
        return analyseRepository.save(analyse);
    }

    @Override
    public List<Analyse> afficherAnalyse() {
        List<Analyse> list=analyseRepository.findAll();

     //   for(Analyse analyse:list){

            //System.out.println("jobjob"+analyse.getProjet().getTitre());
         //   System.out.println("jobjob2"+analyse.getProjet().getEquipes());

      //  }/*
        return analyseRepository.findAll();
    }


    @Override
    public ResponseEntity<?> GetProjetParSprint(int id) {
    List<Sprint> sprintList= sprintRepository.getprojet_par_sprint(id);
//System.out.println("khalil"+sprintList.size());
        Map<String, String> maps = new HashMap<>();
        for(Sprint sp:sprintList){
            String projet_titre=sp.getProjet().getTitre();
            System.out.println("khalil"+sp.getName());

            maps.put(projet_titre,sp.getName());
        }//end for

        return ResponseEntity.status(HttpStatus.OK).body(maps);   }

    @Override
    public List<Analyse> afficherAnalyse_projet() {
        return analyseRepository.getAnalys_projet();
    }

    @Override
    public List<Analyse> afficherAnalyse_Us() {
        return analyseRepository.getAnalys_Us();
    }

    @Override
    public Analyse afficher_one_Analyse(int id) {
        return analyseRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAnalyse(int id) {
        Analyse analyse=analyseRepository.findById(id).orElse(null);
    analyseRepository.delete(analyse);
    }

    @Override
    public Analyse updateAnalyse(Analyse a, int id_analyse) {
        Analyse analyse = afficher_one_Analyse(id_analyse);
        return analyseRepository.save(a);
    }

    @Override
    public List<Analyse> getAnalyse_par_ordre_chronologique() {
        return analyseRepository.getAnalyseparsprint();

    }
}
