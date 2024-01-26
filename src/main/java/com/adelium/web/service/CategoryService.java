package com.adelium.web.service;

import com.adelium.web.entity.Category;
import com.adelium.web.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;

   public Category saveCategory(Category category){
       return categoryRepository.save(category);
    }

    //Category getCategoryById(Long id);

    //List<Category> getAllCategories();

    //void deleteCategory(Long id);
}
