package com.expense.tracker.Category;

public interface CategoryService {
    Category addCategory(Category category);

    Category getOrCreateCategory(String name);
}
