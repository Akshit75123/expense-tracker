package com.expense.tracker.analytics;

import java.time.LocalDate;
import java.util.List;

public interface AnalyticsService {
    AnalyticsResponseDTO analyticsOnDateRange(LocalDate from, LocalDate to);

    List<AnalyticsMonthlyDTO> analyticsOnMonth(int month);
}
