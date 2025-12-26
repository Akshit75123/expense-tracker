package com.expense.tracker.Expense;

import lombok.Data;

@Data
public class ExpenseDTO {
    private int amount;
    private String description;
    private String categoryName;
}
