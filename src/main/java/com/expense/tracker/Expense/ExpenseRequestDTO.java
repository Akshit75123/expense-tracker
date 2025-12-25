package com.expense.tracker.Expense;

import com.expense.tracker.Category.Category;

import lombok.Data;

@Data
public class ExpenseRequestDTO {
    private int amount;
    private String description;
    private Category category;
}
