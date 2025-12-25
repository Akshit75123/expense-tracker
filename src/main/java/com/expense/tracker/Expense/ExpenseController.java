package com.expense.tracker.Expense;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.Category.Category;
import com.expense.tracker.Category.CategoryRepository;
import com.expense.tracker.Enums.PaymentMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/expense-tracker")
public class ExpenseController {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        Category cat = new Category();
        cat.setName(category.getName());
        cat.setActive(true);

        categoryRepository.save(cat);
        return ResponseEntity.ok("Category added successfull");
    }

    @PostMapping("/addExpense")
    public ResponseEntity<?> addExpense(@RequestBody ExpenseRequestDTO expenseRequest) {
        Long categoryId = expenseRequest.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Catogory not found"));

        Expense expense = new Expense();
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(category);
        expense.setDescription(expenseRequest.getDescription());
        expense.setCreatedAt(LocalDateTime.now());
        expense.setExpenseDate(LocalDate.now());
        expense.setPaymentMethod(PaymentMethod.CASH);

        expenseRepository.save(expense);

        return ResponseEntity.ok("Expense added successfully");
    }

}
