package com.expense.tracker.analytics;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    AnalyticsService analyticsService;

    AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/date")
    public ResponseEntity<AnalyticsResponseDTO> analyticsFromDateRange(@RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        return ResponseEntity.ok(analyticsService.analyticsOnDateRange(start, end));
    }

    @GetMapping("/month")
    public ResponseEntity<List<AnalyticsMonthlyDTO>> analyticsFromMonth(@RequestParam int month) {
        return ResponseEntity.ok(analyticsService.analyticsOnMonth(month));
    }
}
