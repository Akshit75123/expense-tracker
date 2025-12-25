package com.expense.tracker.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.Category.Category;
import com.expense.tracker.Category.CategoryRepository;
import com.expense.tracker.Enums.PaymentMethod;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ExpenseDTO addExpense(ExpenseDTO expenseRequest) {
        String categoryName = expenseRequest.getCategoryName();
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense expense = new Expense();
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(category);
        expense.setDescription(expenseRequest.getDescription());
        expense.setCreatedAt(LocalDateTime.now());
        expense.setExpenseDate(LocalDate.now());
        expense.setPaymentMethod(PaymentMethod.CASH);

        expenseRepository.save(expense);
        return expenseRequest;
    }

}
