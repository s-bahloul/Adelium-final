package com.adelium.web.controller;

import com.adelium.web.entity.Category;
import com.adelium.web.entity.Training;
import com.adelium.web.repository.CategoryRepository;
import com.adelium.web.repository.TrainingRepository;
import com.adelium.web.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Controller
@RequestMapping("/training")

public class TrainingController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    public List<Training> getAllTraining() {
        return trainingService.getAllTraining();
    }

    @GetMapping("/{id}")
    public Optional<Training> getTrainingById(@PathVariable Long id) {
        return trainingService.getTrainingById(id);
    }

    @PostMapping
    public Training addNewTraining(@RequestBody Training newTraining) {
        return trainingService.addNewTraining(newTraining);
    }

    @PutMapping("/{id}")
    public Training updateTraining(@PathVariable Long id, @RequestBody Training updatedTraining) {
        return trainingService.updateTraining(id, updatedTraining);
    }

    @DeleteMapping("/{id}")
    public void deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
    }

    @GetMapping
    public String showTrainingList(Model model) {

        // Récupérer la liste des catégories depuis la base de données
        List<Category> categories = categoryRepository.findAll();


        // Récupérer la liste des formations depuis la base de données
        List<Training> trainings = trainingRepository.findAll();

        // Ajouter les listes au modèle pour les rendre disponibles dans la vue
        model.addAttribute("categories", categories);
        model.addAttribute("trainings", trainings);

        // Retourner le nom de la vue (le fichier HTML sans l'extension)
        return "training";
    }

    @GetMapping("/{id}")
    public String getOneTraining(@PathVariable Long id, Model model) {
        Optional<Training> trainingOptional = trainingRepository.findById(id);
        Training training = trainingOptional.orElse(null);
        // TODO : faire une page 404 d'erreur
        if (training==null){
            return null;
        }

        model.addAttribute("training" , training);
//        System.out.println(id);
//        System.out.println("getOne");
        return "trainingDetail";
    }



}