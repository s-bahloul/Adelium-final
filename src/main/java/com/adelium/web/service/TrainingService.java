package com.adelium.web.service;

import com.adelium.web.entity.Training;
import com.adelium.web.repository.CategoryRepository;
import com.adelium.web.repository.TrainingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final CategoryRepository categoryRepository;

    public TrainingService(TrainingRepository trainingRepository, CategoryRepository categoryRepository) {
        this.trainingRepository = trainingRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Training> getAllTraining() {
        return trainingRepository.findAll();
    }
    public Optional<Training> getTrainingById(Long id) {
        return trainingRepository.findById(id);
    }

    @Transactional
    public Training updateTraining(Long id, Training updatedTraining) {
        Training existingTraining = trainingRepository.getById(id);
        if (existingTraining != null) {
            existingTraining.setName(updatedTraining.getName());
            existingTraining.setDescription(updatedTraining.getDescription());
            existingTraining.setCategory(updatedTraining.getCategory());
            // Ajoutez d'autres champs si nécessaire

            return trainingRepository.save(existingTraining);
        }
        return null;
    }

    @Transactional
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }

    @Transactional
    public Training addNewTraining(Training newTraining) {
        // Vérifiez si la catégorie existe déjà, si non, sauvegardez-la avant la formation
        if (newTraining.getCategory() != null && newTraining.getCategory().getId() == null) {
            newTraining.setCategory(categoryRepository.save(newTraining.getCategory()));
        }

        return trainingRepository.save(newTraining);
    }


}

