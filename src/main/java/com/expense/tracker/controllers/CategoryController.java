package com.expense.tracker.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.ok("Category added");
    }

}
