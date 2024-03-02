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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public List<Sprint> sprint_en_retard(int id) {
        List<Sprint> allSprints = sprintRepository.getprojet_par_sprint(id);
        List<Sprint> sprintsEnRetard = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (Sprint sp : allSprints) {
            try {
                Date endDate = dateFormat.parse(sp.getEndDate());
                Date realEndDate = sp.getReal_end_date();
                if (endDate.before(realEndDate)) {
                    sprintsEnRetard.add(sp);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("lista loula "+allSprints.size()+"lista thenia"+allSprints.size());
        return sprintsEnRetard;
    }
    @Override
    public List<Analyse> afficherAnalyse() {
        List<Analyse> list=analyseRepository.findAll();
        return analyseRepository.findAll();
    }
    @Override
    public ResponseEntity<Map<Date,Long>> getprojetpartime(int id_projet) {
   List<Ticket> ticketList=ticketRepository.ticket_projet(id_projet);

   return null; }


    public ResponseEntity<Map<Long, Long>> GetProjetParSprint(int id) {
        List<Sprint> sprintList = sprintRepository.getprojet_par_sprint(id);
        Map<Long, Long> maps = new HashMap<>();
        for (Sprint sp : sprintList) {
            String ed=sp.getEndDate();
            Long sprint_id = sp.getId();
            String sd=sp.getStartDate();
            long differenceInDays = 0;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date end_date = dateFormat.parse(ed);
                Date start_date = dateFormat.parse(sd);
                long differenceInMillis = end_date.getTime() - start_date.getTime();
                differenceInDays  = differenceInMillis / (24 * 60 * 60 * 1000);
                System.out.println("nammme: " + differenceInDays);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            maps.putIfAbsent(sprint_id,differenceInDays);
        }
        System.out.println("jobjob " + maps.size());
        return ResponseEntity.ok(maps);
    }
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
