package com.expmang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.expmang.dto.AnalyticsDTO;
import com.expmang.entity.Transaction;
import com.expmang.entity.TransactionType;
import com.expmang.entity.User;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("SELECT new com.expmang.dto.AnalyticsDTO(c.name, SUM(t.amount)) " +
		       "FROM Transaction t JOIN t.category c " +
		       "WHERE t.type = 'EXPENSE' " +
		       "GROUP BY c.name")
		List<AnalyticsDTO> getCategoryWiseSpending();
	
	
	List<Transaction> findByUser(User user);
	Optional<Transaction> findByIdAndUser(Long id, User user);
	
	// ✅ NAYA
	Page<Transaction> findByType(TransactionType type, Pageable pageable);
	
	Page<Transaction> findByUser(User user, Pageable pageable);
	
	@Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
		       "WHERE t.user = :user " +
		       "AND t.type = :type " +
		       "AND MONTH(t.date) = :month " +
		       "AND YEAR(t.date) = :year")
		Double sumByUserAndTypeAndMonthAndYear(
		    @Param("user") User user,
		    @Param("type") TransactionType type,
		    @Param("month") int month,
		    @Param("year") int year
		);
}
