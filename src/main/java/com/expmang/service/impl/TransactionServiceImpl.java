package com.expmang.service.impl;

import com.expmang.dto.AnalyticsDTO;
import com.expmang.dto.MonthlySummaryDTO;
import com.expmang.dto.TransactionDTO;
import com.expmang.entity.Category;
import com.expmang.entity.Transaction;
import com.expmang.entity.TransactionType;
import com.expmang.entity.User;
import com.expmang.exception.ResourceNotFoundException;
import com.expmang.repository.CategoryRepository;
import com.expmang.repository.TransactionRepository;
import com.expmang.repository.UserRepository;
import com.expmang.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    
    public TransactionServiceImpl(TransactionRepository transactionRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository) {
			this.transactionRepository = transactionRepository;
			this.userRepository = userRepository;
			this.categoryRepository = categoryRepository;
}


    @Override
    public List<Transaction> getAllTransactions() {
    	String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return transactionRepository.findByUser(user);
    }
    
    @Override
    public Transaction updateTransaction(Long id, TransactionDTO dto) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Transaction transaction = transactionRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found or not yours"));

        if (dto.getAmount() != null) {
            transaction.setAmount(dto.getAmount());
        }
        if (dto.getType() != null) {
            transaction.setType(dto.getType());
        }
        if (dto.getDate() != null) {
            transaction.setDate(dto.getDate());
        }
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            transaction.setCategory(category);
        }

        return transactionRepository.save(transaction);
    }
    
    @Override
    public MonthlySummaryDTO getMonthlySummary(int month, int year) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Double totalIncome = transactionRepository.sumByUserAndTypeAndMonthAndYear(
                user, TransactionType.INCOME, month, year);

        Double totalExpense = transactionRepository.sumByUserAndTypeAndMonthAndYear(
                user, TransactionType.EXPENSE, month, year);

        return new MonthlySummaryDTO(month, year, totalIncome, totalExpense);
    }

    @Override
    public void deleteTransaction(Long id) {
    	String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Transaction transaction = transactionRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found or not yours"));

        transactionRepository.delete(transaction);
    }
    
    @Override
    public Transaction addTransaction(TransactionDTO dto) {

        // 🔥 GET USER FROM JWT
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // CATEGORY SAME
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setType(dto.getType());
        transaction.setDate(dto.getDate());
        transaction.setUser(user); // ✅ correct
        transaction.setCategory(category);

        return transactionRepository.save(transaction);
    }    
    public List<AnalyticsDTO> getCategoryAnalytics() {
        return transactionRepository.getCategoryWiseSpending();
    }
    
    public Page<Transaction> getTransactions(Pageable pageable) {
    	String email = SecurityContextHolder.getContext()
    	        .getAuthentication()
    	        .getName();

    	User user = userRepository.findByEmail(email)
    	        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    	
        return transactionRepository.findByUser(user, pageable);
    }
    
    public Page<Transaction> getByType(TransactionType type, Pageable pageable) {
        return transactionRepository.findByType(type, pageable);
    }
}