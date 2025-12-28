package com.expense.tracker.Expense;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

public interface ExpenseService {
    ExpenseDTO addExpense(ExpenseDTO dto);

    void deleteExpense(Long id);

    Page<ExpenseResponseDTO> getAllExpenses(int page, int size);

    ExpenseResponseDTO updateExpense(Long id, ExpenseDTO dto);

    ExpenseResponseDTO getExpenseById(@PathVariable Long id);

    Page<ExpenseResponseDTO> filterExpenseByCategoryName(String categoryName, Integer page, Integer size,
            String sortProperty, String sortType);

    List<ExpenseResponseDTO> filterExpenseByExpenseDate(LocalDate from, LocalDate to);

    List<ExpenseResponseDTO> filterExpenseByAmount(Integer min, Integer max);

    List<ExpenseResponseDTO> filterExpenses(
            String category,
            Integer min,
            Integer max,
            LocalDate from,
            LocalDate to);
}
