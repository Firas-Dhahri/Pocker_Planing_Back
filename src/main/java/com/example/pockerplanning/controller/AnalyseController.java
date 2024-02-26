package com.example.pockerplanning.controller;

import com.example.pockerplanning.entities.Analyse;
import com.example.pockerplanning.entities.Historique;
import com.example.pockerplanning.entities.Projet;
import com.example.pockerplanning.repository.AnalyseRepository;
import com.example.pockerplanning.repository.ProjetReposiroty;
import com.example.pockerplanning.services.Interface.IAnalyseService;
import com.example.pockerplanning.services.Interface.IHistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AnalyseController {

    @Autowired
    IAnalyseService analyseService;
    @Autowired
    AnalyseRepository analyseRepository ;
    @Autowired
    ProjetReposiroty projetReposiroty ;

    //http://localhost:8088/Spring/etudiant/retrieve-all-etudiants
    @GetMapping("/retrieve-all-Analyses")
    @ResponseBody
    public List<Analyse> getAnalyses() {
        List<Analyse> listAnalyse = analyseService.afficherAnalyse();
        return listAnalyse;
    }
    @GetMapping("/getAnalyses_par_projets/{id}")
    @ResponseBody
    public Analyse getAnalyses_par_projets(@PathVariable int id) {
        Projet p = projetReposiroty.findById(id).orElse(null);
        return analyseRepository.findByProjet(p);
    }

    @GetMapping("/getAnalyses_par_projets_khalil")
    @ResponseBody
    public List<Analyse> getAnalyses_par_projets2() {
        return analyseService.afficherAnalyse_projet();
    }


    @GetMapping("/getAnalyses_par_us")
    @ResponseBody
    public List<Analyse> getAnalyses_par_Us() {
        List<Analyse> listAnalyse = analyseService.afficherAnalyse_Us();
        return listAnalyse;
    }

    @GetMapping("/Liste_Sprint_par_projet/{projet-id}")
    @ResponseBody
    public ResponseEntity<?> affichersprint_par_projet(@PathVariable("projet-id") int id) {
        return analyseService.GetProjetParSprint(id);
    }

    // http://localhost:8088/Spring/etudiant/retrieve-etudiant/8
    @GetMapping("/retrieve-Analyse/{Analyse-id}")
    @ResponseBody
    public Analyse retrieveHistorique(@PathVariable("Analyse-id") int id) {
        return analyseService.afficher_one_Analyse(id);
    }
    @GetMapping("/retrieve-Analyse_par_sprint_par_ordre/{Analyse-id}")
    public List<Analyse> retrieve_Analyse_par_sprint() {
        return analyseService.getAnalyse_par_ordre_chronologique();
    }

    // http://localhost:8088/Spring/etudiant/add-etudiant
    @PostMapping("/add-Analyse/{id_projet}")
    @ResponseBody
    public Analyse addAnalyse(@RequestBody Analyse ae,@PathVariable("id_projet") int id_projet)
    {
        Analyse analyse = analyseService.ajouterAnalyse(ae,id_projet);

        return analyse;
    }
    @PostMapping("/add-Analyse_us/{id_ticket}")
    @ResponseBody
    public Analyse addAnalyse_us(@RequestBody Analyse ae,@PathVariable("id_ticket") long id_ticket)
    {
        Analyse analyse = analyseService.ajouterAnalyse_Us(ae,id_ticket);

        return analyse;
    }

    // http://localhost:8088/Spring/etudiant/update-etudiant/{etudiant-id}
    @PutMapping("/update-Analyse/{Analyse-id}")
    @ResponseBody
    public Analyse updateAnalyse(@PathVariable("Analyse-id") int id, @RequestBody Analyse ue) {
        ue.setId(id);
        return analyseService.updateAnalyse(ue, id);
    }

    // http://localhost:8088/Spring/etudiant/delete-etudiant/{etudiant-id}
    @DeleteMapping("/delete-Analyse/{Analyse-id}")
    @ResponseBody
    public void deleteAnalyse(@PathVariable("Analyse-id") int id) {
        analyseService.deleteAnalyse(id);
    }
}