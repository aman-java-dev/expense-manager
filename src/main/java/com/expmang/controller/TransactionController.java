package com.expmang.controller;

import com.expmang.dto.AnalyticsDTO;
import com.expmang.dto.MonthlySummaryDTO;
import com.expmang.dto.TransactionDTO;
import com.expmang.entity.Transaction;
import com.expmang.entity.TransactionType;
import com.expmang.service.TransactionService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Transactions", description = "Transaction CRUD aur Analytics APIs")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable Long id,
            @RequestBody TransactionDTO dto) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, dto));
    }

    @GetMapping("/summary")
    public ResponseEntity<MonthlySummaryDTO> getMonthlySummary(
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(transactionService.getMonthlySummary(month, year));
    }
    
    // Add transaction
    @PostMapping
    public Transaction addTransaction(@RequestBody TransactionDTO dto) {
        return transactionService.addTransaction(dto);
    }

    // Get all transactions
    @GetMapping("/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // Delete transaction
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "Deleted successfully";
    }
    
    @GetMapping("/analytics/category")
    public List<AnalyticsDTO> getCategoryAnalytics() {
        return transactionService.getCategoryAnalytics();
    }
    
    @GetMapping
    public Page<Transaction> getTransactions(Pageable pageable) {
        return transactionService.getTransactions(pageable);
    }
    
    @GetMapping("/filter")
    public Page<Transaction> filterByType(@RequestParam TransactionType type, Pageable pageable) {
        return transactionService.getByType(type, pageable);
    }
}