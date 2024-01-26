package com.adelium.web.repository;

import com.adelium.web.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    // Ajoutez ici des méthodes personnalisées si nécessaire
}

