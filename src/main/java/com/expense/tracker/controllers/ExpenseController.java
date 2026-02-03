package com.expense.tracker.expense;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<Page<ExpenseResponseDTO>> getAllExpenses(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(expenseService.getAllExpenses(page, size));

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ExpenseResponseDTO> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO dto) {
        return ResponseEntity.ok(expenseService.updateExpense(id, dto));
    }

    @GetMapping("/get-expense/{id}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ExpenseResponseDTO>> filterExpenseByCategoryName(@RequestParam String category,
            @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "expenseDate") String sortProperty,
            @RequestParam(defaultValue = "desc") String sortType) {
        return ResponseEntity
                .ok(expenseService.filterExpenseByCategoryName(category, page, size, sortProperty, sortType));
    }

    @GetMapping("/filter/date")
    public ResponseEntity<Page<ExpenseResponseDTO>> filterExpenseByExpenseDate(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "expenseDate") String sortProperty,
            @RequestParam(defaultValue = "desc") String sortType) {
        return ResponseEntity
                .ok(expenseService.filterExpenseByExpenseDate(from, to, page, size, sortProperty, sortType));
    }

    @GetMapping("/filter/amount")
    public ResponseEntity<Page<ExpenseResponseDTO>> filterExpenseByAmount(@RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max, @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "expenseDate") String sortProperty,
            @RequestParam(defaultValue = "desc") String sortType) {
        return ResponseEntity.ok(expenseService.filterExpenseByAmount(min, max, page, size, sortProperty, sortType));
    }

    @GetMapping("/filter/advanced")
    public ResponseEntity<Page<ExpenseResponseDTO>> filterExpenses(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer min,
            @RequestParam(required = false) Integer max,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "expenseDate") String sortProperty,
            @RequestParam(defaultValue = "desc") String sortType) {

        return ResponseEntity.ok(
                expenseService.filterExpenses(category, min, max, from, to, page, size, sortProperty, sortType));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ExpenseResponseDTO>> searchExpense(@RequestParam String text,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "expenseDate") String sortProperty,
            @RequestParam(defaultValue = "desc") String sortType) {
        return ResponseEntity.ok(expenseService.searchExpenses(text, page, size, sortProperty, sortType));
    }

}
