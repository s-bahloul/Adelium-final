package com.adelium.web.repository;

import com.adelium.web.entity.SessionTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionTraining, Long> {
    // Ajoutez ici des méthodes personnalisées si nécessaire
}
