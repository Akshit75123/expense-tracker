package com.expense.tracker.Expense;

import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class ExpenseSpecification {

    public static Specification<Expense> hasCategory(String category) {
        return (root, query, cb) -> cb.equal(root.get("category").get("name"), category);
    }

    public static Specification<Expense> amountBetween(Integer min, Integer max) {
        return (root, query, cb) -> cb.between(root.get("amount"), min, max);
    }

    public static Specification<Expense> dateBetween(LocalDate from, LocalDate to) {
        return (root, query, cb) -> cb.between(root.get("expenseDate"), from, to);
    }
}
