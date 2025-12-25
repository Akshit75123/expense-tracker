package com.expense.tracker.Expense;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.Category.Category;

import com.expense.tracker.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/expense-tracker")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.ok("Category added");
    }

    @PostMapping("/addExpense")
    public ResponseEntity<?> addExpense(@RequestBody ExpenseDTO expenseRequest) {
        expenseService.addExpense(expenseRequest);
        return ResponseEntity.ok("Expense Added");
    }

}
