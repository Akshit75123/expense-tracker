package com.expense.tracker.expense;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expense.tracker.analytics.AnalyticsMonthlyDTO;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {
    List<Expense> findAllByOrderByExpenseDateDesc();

    @Query("SELECT e FROM Expense e LEFT JOIN e.category c WHERE c.name = :name ORDER BY e.expenseDate desc")
    List<Expense> findAllByCategory_NameOrderByExpenseDateDesc(String name);

    @Query("SELECT e FROM Expense e WHERE e.expenseDate BETWEEN :from AND :to ORDER BY e.expenseDate desc")
    List<Expense> findAllByExpenseDateBetweenOrderByExpenseDateDesc(LocalDate from, LocalDate to);

    @Query("SELECT e FROM Expense e WHERE e.amount BETWEEN :min AND :max ORDER BY e.expenseDate desc")
    List<Expense> findAllByAmountBetweenOrderByExpenseDateDesc(Integer min, Integer max);

    List<Expense> findAllByExpenseDateGreaterThanOrderByExpenseDateDesc(LocalDate from);

    List<Expense> findAllByExpenseDateLessThanOrderByExpenseDateDesc(LocalDate to);

    List<Expense> findAllByAmountLessThanEqualOrderByExpenseDateDesc(Integer max);

    List<Expense> findAllByAmountGreaterThanEqualOrderByExpenseDateDesc(Integer min);

    Page<Expense> findAllByCategory_Name(String name, Pageable pageable);

    Page<Expense> findAllByExpenseDateLessThanEqual(LocalDate to, Pageable pageable);

    Page<Expense> findAllByExpenseDateGreaterThanEqual(LocalDate from, Pageable pageable);

    Page<Expense> findAllByExpenseDateBetween(LocalDate from, LocalDate to, Pageable pageable);

    Page<Expense> findAllByAmountLessThanEqual(Integer max, Pageable pageable);

    Page<Expense> findAllByAmountGreaterThanEqual(Integer min, Pageable pageable);

    Page<Expense> findAllByAmountBetween(Integer min, Integer max, Pageable pageable);

    @Query("""
            SELECT new com.expense.tracker.analytics.dto.AnalyticsMonthlyDTO(
                FUNCTION('year', e.expenseDate),
                FUNCTION('month', e.expenseDate),
                SUM(e.amount)
            )
            FROM Expense e
            WHERE FUNCTION('month', e.expenseDate) = :month
            GROUP BY
                FUNCTION('year', e.expenseDate),
                FUNCTION('month', e.expenseDate)
            ORDER BY
                FUNCTION('year', e.expenseDate),
                FUNCTION('month', e.expenseDate)
            """)
    List<AnalyticsMonthlyDTO> getMonthlyExpenseSummary(@Param("month") int month);

}
