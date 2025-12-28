package com.expense.tracker.Expense;

import java.util.List;

public interface ExpenseService {
    ExpenseDTO addExpense(ExpenseDTO dto);

    void deleteExpense(Long id);

    List<ExpenseResponseDTO> getAllExpenses();
}
