package com.expense.tracker.expense;

import com.expense.tracker.category.Category;
import com.expense.tracker.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expense")
@Data

public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense-id")
    private Long id;
    private int amount;
    private String description;
    private LocalDate expenseDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;
    private PaymentMethod paymentMethod;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Category category;
}