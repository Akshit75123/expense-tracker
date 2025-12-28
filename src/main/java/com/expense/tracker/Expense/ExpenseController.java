package com.expense.tracker.Expense;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/expenses")
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

    @GetMapping("/get-expenses")
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO dto) {
        return ResponseEntity.ok(expenseService.updateExpense(id, dto));
    }

    @GetMapping("/get-expense/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @GetMapping("/filter/")
    public ResponseEntity<List<ExpenseResponseDTO>> filterExpenseByCategoryName(@RequestParam String category) {
        return ResponseEntity.ok(expenseService.filterExpenseByCategoryName(category));
    }

    @GetMapping("/filter/date")
    public ResponseEntity<List<ExpenseResponseDTO>> filterExpenseByExpenseDate(@RequestParam LocalDate from,
            @RequestParam LocalDate to) {
        return ResponseEntity.ok(expenseService.filterExpenseByExpenseDate(from, to));
    }

    @GetMapping("/filter/amount")
    public ResponseEntity<List<ExpenseResponseDTO>> filterExpenseByAmount(@RequestParam Integer min,
            @RequestParam Integer max) {
        return ResponseEntity.ok(expenseService.filterExpenseByAmount(min, max));
    }

}
