package com.expense.tracker.Expense;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExpenseResponseDTO {
    private Long id;
    private int amount;
    private String description;
    private String categoryName;
    private LocalDate expenseDate;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
