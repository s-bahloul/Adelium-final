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


//@RequiredArgsConstructor
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

//   @GetMapping
//    public List<Training> getAllTraining() {
//
//        return trainingService.getAllTraining();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Training> getTrainingById(@PathVariable Long id) {
//
//        return trainingService.getTrainingById(id);
//    }


@PostMapping("/update/{id}")
public String updateTraining(@PathVariable Long id, @ModelAttribute Training updatedTraining) {
    // Mettez à jour la formation avec les nouvelles informations
    Training updatedTrainingResult = trainingService.updateTraining(id, updatedTraining);

    if (updatedTrainingResult != null) {
        // Redirigez vers la page de détail de la formation mise à jour
        return "redirect:/training/" + id;
    } else {
        // Gérez le cas où la formation n'a pas pu être mise à jour (par exemple, elle n'existe pas)
        // Vous pouvez rediriger vers une page d'erreur ou traiter d'une autre manière
        return "redirect:/error";
    }
}

    @PostMapping("/deleteTraining/{id}")
    public String deleteTraining(@PathVariable Long id) {
        // Supprimez la formation par son ID
        trainingService.deleteTraining(id);

        // Redirigez vers la page principale des formations
        return "redirect:/training";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        // Récupérez la liste des catégories pour l'afficher dans le formulaire de création
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        // Ajoutez un objet Training vide pour lier le formulaire à un nouvel élément
        model.addAttribute("newTraining", new Training());

        // Retournez la vue du formulaire de création
        return "createTraining";
    }

    @PostMapping("/create")
    public String createTraining(@ModelAttribute Training newTraining) {
        // Ajoutez la nouvelle formation à la base de données
        Training createdTraining = trainingService.addNewTraining(newTraining);

        // Redirigez vers la page de détail de la nouvelle formation
        return "redirect:/training/" + createdTraining.getId();
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