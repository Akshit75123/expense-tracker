package com.expense.tracker.Expense;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByOrderByExpenseDateDesc();

    List<Expense> findAllByCategory_NameOrderByExpenseDateDesc(String name);

    List<Expense> findAllByExpenseDateBetweenOrderByExpenseDateDesc(LocalDate from, LocalDate to);
}
