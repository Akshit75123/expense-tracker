package com.expense.tracker.expense;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

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

    public static Specification<Expense> globalSearch(String text) {
        return (root, query, cb) -> {
            if (text == null || text.trim().isEmpty()) {
                return null;
            }

            String pattern = "%" + text.toLowerCase() + "%";

            Predicate descriptionPredicate = cb.like(cb.lower(root.get("description")), pattern);

            Predicate categoryPredicate = cb.like(cb.lower(root.get("category").get("name")), pattern);

            return cb.or(descriptionPredicate, categoryPredicate);
        };
    }

}
