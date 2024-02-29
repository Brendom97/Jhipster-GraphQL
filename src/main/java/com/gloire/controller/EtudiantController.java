package com.gloire.controller;

import com.gloire.dto.EtudiantDTO;
import com.gloire.entity.Etudiant;
import com.gloire.repository.EtudiantRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EtudiantController {

    private EtudiantRepository etudiantRepository;

    public EtudiantController(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    //lister les produits
    @QueryMapping
    public List<Etudiant> etudiantList() {
        return etudiantRepository.findAll();
    }

    //Consulter un produit
    @QueryMapping
    public Etudiant etudiantById(@Argument int id) {
        return etudiantRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("etudiant %s not found ", id)));
    }

    //CREATE avec DTO
    @MutationMapping
    public Etudiant saveEtudiant(@Argument EtudiantDTO etudiant) {
        Etudiant etudiantToSave = new Etudiant();
        etudiantToSave.setId(etudiant.hashCode());
        etudiantToSave.setNom(etudiant.getNom());
        etudiantToSave.setPrenom(etudiant.getPrenom());
        etudiantToSave.setEmail(etudiant.getEmail());
        etudiantToSave.setAdresse(etudiant.getAdresse());
        etudiantToSave.setTelephone(etudiant.getTelephone());
        return etudiantRepository.save(etudiantToSave);
    }

    //UPDATE avec  DTO
    @MutationMapping
    public Etudiant updateEtudiant(@Argument int id, @Argument EtudiantDTO etudiant) {
        Etudiant etudiantToSave = new Etudiant();
        etudiantToSave.setId(id);
        etudiantToSave.setNom(etudiant.getNom());
        etudiantToSave.setPrenom(etudiant.getPrenom());
        etudiantToSave.setEmail(etudiant.getEmail());
        etudiantToSave.setAdresse(etudiant.getAdresse());
        etudiantToSave.setTelephone(etudiant.getTelephone());
        return etudiantRepository.save(etudiantToSave);
    }

    //DELETE
    @MutationMapping
    public void deleteEtudiant(@Argument int id) {
        etudiantRepository.deleteById(id);
    }

    @QueryMapping
    public int countEtudiant(@Argument int id) {
        List<Etudiant> etudiants = etudiantRepository.findAll(); // Supposons que vous ayez une méthode findAll dans votre repository
        return etudiants.size();
    }
}
