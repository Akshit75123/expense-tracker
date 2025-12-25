package com.expense.tracker.Expense;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/expense-tracker/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestBody ExpenseDTO expenseRequest) {
        expenseService.addExpense(expenseRequest);
        return ResponseEntity.ok("Expense Added");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense Deleted");
    }
}
