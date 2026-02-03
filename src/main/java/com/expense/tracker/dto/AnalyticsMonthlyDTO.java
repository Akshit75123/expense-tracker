package com.expense.tracker.analytics;

import lombok.Data;

@Data
public class AnalyticsMonthlyDTO {
    private Integer year;
    private Integer month;
    private Integer total;

    public AnalyticsMonthlyDTO(Integer year, Integer month, Integer total) {
        this.year = year;
        this.month = month;
        this.total = total;
    }
}
