package com.expense.tracker.category;

public interface CategoryService {
    Category addCategory(Category category);

    Category getOrCreateCategory(String name);
}
