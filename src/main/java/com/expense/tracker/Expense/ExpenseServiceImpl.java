package com.expense.tracker.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("No Expense Found"));
        expenseRepository.delete(expense);
    }

    @Override
    public List<ExpenseResponseDTO> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAllByOrderByExpenseDateDesc();

        List<ExpenseResponseDTO> listOfExpenseDTO = new ArrayList<>();
        for (int i = 0; i < expenses.size(); i++) {
            listOfExpenseDTO.add(mapToExpenseResponseDTO(expenses.get(i)));
        }
        return listOfExpenseDTO;
    }

    public ExpenseResponseDTO mapToExpenseResponseDTO(Expense expense) {
        ExpenseResponseDTO dto = new ExpenseResponseDTO();
        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount());
        dto.setExpenseDate(expense.getExpenseDate());
        dto.setDescription(expense.getDescription());
        dto.setCategoryName(expense.getCategory().getName());
        dto.setCreatedAt(expense.getCreatedAt());
        dto.setUpdatedAt(expense.getUpdatedAt());
        return dto;
    }

    @Override
    public ExpenseResponseDTO updateExpense(Long id, ExpenseDTO dto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense Do not Exist"));
        Category category = categoryRepository.findByName(dto.getCategoryName())
                .orElseThrow(() -> new RuntimeException("No Category Found"));
        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        expense.setCategory(category);
        expense.setUpdatedAt(LocalDateTime.now());
        expenseRepository.save(expense);
        return mapToExpenseResponseDTO(expense);
    }

}
