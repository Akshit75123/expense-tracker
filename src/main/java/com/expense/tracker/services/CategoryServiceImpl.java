package com.expense.tracker.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category getOrCreateCategory(String name) {
        return categoryRepository.findByName(name).orElseGet(() -> {
            Category category = new Category();
            category.setName(name);
            category.setActive(true);
            return categoryRepository.save(category);
        });
    }

}
