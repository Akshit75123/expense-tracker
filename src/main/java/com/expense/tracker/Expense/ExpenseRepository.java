package com.expense.tracker.Expense;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {
    List<Expense> findAllByOrderByExpenseDateDesc();

    @Query("SELECT e FROM Expense e LEFT JOIN e.category c WHERE c.name = :name ORDER BY e.expenseDate desc")
    List<Expense> findAllByCategory_NameOrderByExpenseDateDesc(String name);

    List<Expense> findAllByExpenseDateBetweenOrderByExpenseDateDesc(LocalDate from, LocalDate to);

    List<Expense> findAllByAmountBetweenOrderByExpenseDateDesc(Integer min, Integer max);

    List<Expense> findAllByExpenseDateGreaterThanOrderByExpenseDateDesc(LocalDate from);

    List<Expense> findAllByExpenseDateLessThanOrderByExpenseDateDesc(LocalDate to);

    List<Expense> findAllByAmountLessThanEqualOrderByExpenseDateDesc(Integer max);

    List<Expense> findAllByAmountGreaterThanEqualOrderByExpenseDateDesc(Integer min);
}
