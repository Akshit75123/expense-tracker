package com.expense.tracker.Expense;

import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class ExpenseSpecification {

    public static Specification<Expense> hasCategory(String category) {
        return (root, query, cb) -> cb.equal(root.get("category").get("name"), category);
    }

    public static Specification<Expense> amountGreaterThan(Integer min) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("amount"), min);
    }

    public static Specification<Expense> amountLessThan(Integer max) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("amount"), max);
    }

    public static Specification<Expense> dateAfter(LocalDate from) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("expenseDate"), from);
    }

    public static Specification<Expense> dateBefore(LocalDate to) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("expenseDate"), to);
    }
}
