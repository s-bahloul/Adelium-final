package com.adelium.web.repository;

import com.adelium.web.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Ajoutez ici des méthodes personnalisées si nécessaire



}
