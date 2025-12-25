package com.expense.tracker.Expense;

public interface ExpenseService {
    ExpenseDTO addExpense(ExpenseDTO dto);

    void deleteExpense(Long id);
}
