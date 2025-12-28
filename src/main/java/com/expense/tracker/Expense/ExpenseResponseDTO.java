package com.expense.tracker.Expense;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ExpenseResponseDTO {
    private Long id;
    private int amount;
    private String description;
    private String categoryName;
    private LocalDate expenseDate;
}
