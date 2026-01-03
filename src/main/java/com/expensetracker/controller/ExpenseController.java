package com.expensetracker.controller;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody ExpenseRequest expenseRequest) {
        Expense expense = expenseService.createExpense(expenseRequest);
        return ResponseEntity.ok(expense);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        
        if (filter != null) {
            LocalDate end = LocalDate.now();
            LocalDate start = end;

            switch (filter) {
                case "week":
                    start = end.minusWeeks(1);
                    break;
                case "month":
                    start = end.minusMonths(1);
                    break;
                case "3months":
                    start = end.minusMonths(3);
                    break;
                case "custom":
                    if (startDate != null && endDate != null) {
                        start = startDate;
                        end = endDate;
                    } else {
                        return ResponseEntity.badRequest().build();
                    }
                    break;
                default:
                    // If filter is unknown, return all or bad request? 
                    // Let's assume return all if filter is invalid or just ignore.
                    // But requirement implies specific filters.
                    break;
            }
            return ResponseEntity.ok(expenseService.getExpensesByDateRange(start, end));
        }

        return ResponseEntity.ok(expenseService.getAllExpenses());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody ExpenseRequest expenseRequest) {
        Expense expense = expenseService.updateExpense(id, expenseRequest);
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }
}
