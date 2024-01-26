package com.adelium.web.repository;

import com.adelium.web.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    // Ajoutez ici des méthodes personnalisées si nécessaire
}

