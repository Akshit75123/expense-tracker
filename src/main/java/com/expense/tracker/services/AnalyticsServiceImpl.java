package com.expense.tracker.analytics;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.expense.tracker.expense.Expense;
import com.expense.tracker.expense.ExpenseRepository;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    ExpenseRepository expenseRepository;

    AnalyticsServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public AnalyticsResponseDTO analyticsOnDateRange(LocalDate from, LocalDate to) {
        List<Expense> expenses = expenseRepository.findAllByExpenseDateBetweenOrderByExpenseDateDesc(from, to);
        int totalAmount = 0;
        for (int i = 0; i < expenses.size(); i++) {
            totalAmount += expenses.get(i).getAmount();
        }
        return mapToAnalyticsResponseDTO(totalAmount, from, to);
    }

    public AnalyticsResponseDTO mapToAnalyticsResponseDTO(int amount, LocalDate from, LocalDate to) {
        AnalyticsResponseDTO dto = new AnalyticsResponseDTO();
        dto.setAmount(String.valueOf(amount));
        dto.setStart(from);
        dto.setEnd(to);
        return dto;
    }

    @Override
    public List<AnalyticsMonthlyDTO> analyticsOnMonth(int month) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Month can only be from 1 to 12");
        return expenseRepository.getMonthlyExpenseSummary(month);
    }

}
