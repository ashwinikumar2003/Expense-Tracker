package com.expensetracker.dto;

import com.expensetracker.model.ExpenseCategory;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseRequest {
    private String title;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private ExpenseCategory category;
}
