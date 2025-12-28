package com.expense.tracker.Expense;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

public interface ExpenseService {
    ExpenseDTO addExpense(ExpenseDTO dto);

    void deleteExpense(Long id);

    List<ExpenseResponseDTO> getAllExpenses();

    ExpenseResponseDTO updateExpense(Long id, ExpenseDTO dto);

    ExpenseResponseDTO getExpenseById(@PathVariable Long id);

    List<ExpenseResponseDTO> filterExpenseByCategoryName(String categoryName);

    List<ExpenseResponseDTO> filterExpenseByExpenseDate(LocalDate from, LocalDate to);

    List<ExpenseResponseDTO> filterExpenseByAmount(Integer min, Integer max);

    List<ExpenseResponseDTO> filterExpenses(
            String category,
            Integer min,
            Integer max,
            LocalDate from,
            LocalDate to);
}
