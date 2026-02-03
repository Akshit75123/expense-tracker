package com.expense.tracker.analytics;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AnalyticsResponseDTO {
    String amount;
    LocalDate start;
    LocalDate end;
}
