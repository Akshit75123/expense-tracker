package com.expense.tracker.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.expense.tracker.Category.Category;
import com.expense.tracker.Category.CategoryRepository;
import com.expense.tracker.Category.CategoryService;
import com.expense.tracker.Enums.PaymentMethod;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public ExpenseDTO addExpense(ExpenseDTO expenseRequest) {
        String categoryName = expenseRequest.getCategoryName();
        Category category = categoryService.getOrCreateCategory(categoryName);

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
    public Page<ExpenseResponseDTO> getAllExpenses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("expenseDate").descending());

        Page<Expense> expenses = expenseRepository.findAll(pageable);

        return expenses.map(this::mapToExpenseResponseDTO);
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
        Category category = categoryService.getOrCreateCategory(dto.getCategoryName());
        expense.setAmount(dto.getAmount());
        expense.setDescription(dto.getDescription());
        expense.setCategory(category);
        expense.setUpdatedAt(LocalDateTime.now());
        expenseRepository.save(expense);
        return mapToExpenseResponseDTO(expense);
    }

    @Override
    public ExpenseResponseDTO getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("No Expense Found"));

        return mapToExpenseResponseDTO(expense);
    }

    @Override
    public Page<ExpenseResponseDTO> filterExpenseByCategoryName(String categoryName, Integer page, Integer size,
            String sortProperty, String sortType) {
        Sort sort = null;
        if (sortType.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortProperty).ascending();
        } else
            sort = Sort.by(sortProperty).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Expense> expenses = expenseRepository.findAllByCategory_Name(categoryName, pageable);
        return expenses.map(this::mapToExpenseResponseDTO);
    }

    @Override
    public List<ExpenseResponseDTO> filterExpenseByExpenseDate(LocalDate from, LocalDate to) {

        List<Expense> expenses = new ArrayList<>();
        if (from != null && to != null)
            expenses = expenseRepository.findAllByExpenseDateBetweenOrderByExpenseDateDesc(from, to);
        else if (from != null)
            expenses = expenseRepository.findAllByExpenseDateGreaterThanOrderByExpenseDateDesc(from);
        else if (to != null)
            expenses = expenseRepository.findAllByExpenseDateLessThanOrderByExpenseDateDesc(to);
        else
            expenses = expenseRepository.findAll();
        List<ExpenseResponseDTO> responseList = new ArrayList<>();
        for (Expense expense : expenses) {
            responseList.add(mapToExpenseResponseDTO(expense));
        }
        return responseList;
    }

    @Override
    public List<ExpenseResponseDTO> filterExpenseByAmount(Integer min, Integer max) {
        List<Expense> expenses = new ArrayList<>();
        if (max != null && min != null)
            expenses = expenseRepository.findAllByAmountBetweenOrderByExpenseDateDesc(min, max);
        else if (max != null)
            expenses = expenseRepository.findAllByAmountLessThanEqualOrderByExpenseDateDesc(max);
        else if (min != null)
            expenses = expenseRepository.findAllByAmountGreaterThanEqualOrderByExpenseDateDesc(min);
        else
            expenses = expenseRepository.findAll();
        List<ExpenseResponseDTO> responseList = new ArrayList<>();
        for (Expense expense : expenses) {
            responseList.add(mapToExpenseResponseDTO(expense));
        }
        return responseList;
    }

    @Override
    public List<ExpenseResponseDTO> filterExpenses(
            String category,
            Integer min,
            Integer max,
            LocalDate from,
            LocalDate to) {

        Specification<Expense> spec = (root, query, cb) -> cb.conjunction();

        if (category != null && !category.isBlank()) {
            spec = spec.and(ExpenseSpecification.hasCategory(category));
        }

        if (min != null) {
            spec = spec.and(ExpenseSpecification.amountGreaterThan(min));
        }

        if (max != null) {
            spec = spec.and(ExpenseSpecification.amountLessThan(max));
        }

        if (from != null) {
            spec = spec.and(ExpenseSpecification.dateAfter(from));
        }

        if (to != null) {
            spec = spec.and(ExpenseSpecification.dateBefore(to));
        }

        return expenseRepository.findAll(spec)
                .stream()
                .map(this::mapToExpenseResponseDTO)
                .toList();
    }

}
