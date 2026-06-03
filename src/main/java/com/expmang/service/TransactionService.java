package com.expmang.service;

import com.expmang.dto.AnalyticsDTO;
import com.expmang.dto.MonthlySummaryDTO;
import com.expmang.dto.TransactionDTO;
import com.expmang.entity.Transaction;
import com.expmang.entity.TransactionType;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    void deleteTransaction(Long id);
    Transaction addTransaction(TransactionDTO dto);
    public List<AnalyticsDTO> getCategoryAnalytics();
    public Page<Transaction> getTransactions(Pageable pageable);
    public Page<Transaction> getByType(TransactionType type, Pageable pageable);
    Transaction updateTransaction(Long id, TransactionDTO dto);
    MonthlySummaryDTO getMonthlySummary(int month, int year);
}