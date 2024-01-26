package com.adelium.web.repository;

import com.adelium.web.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Ajoutez ici des méthodes personnalisées si nécessaire
}

