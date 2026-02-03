package com.expense.tracker.expense;

import lombok.Data;

@Data
public class ExpenseDTO {
    private int amount;
    private String description;
    private String categoryName;
}
