package com.adelium.web.repository;

import com.adelium.web.entity.Course;
import com.adelium.web.entity.SessionCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionCourseRepository extends JpaRepository<SessionCourse, Long> {
    // Ajoutez ici des méthodes personnalisées si nécessaire
}

