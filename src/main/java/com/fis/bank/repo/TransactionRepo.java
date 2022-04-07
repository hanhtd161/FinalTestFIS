package com.fis.bank.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fis.bank.entity.Transaction;
import com.fis.bank.model.Report;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
	@Query("SELECT t from Transaction t where t.fromAccount.accountNumber = :accountNumber OR t.toAccount.accountNumber = :accountNumber"
			+ " AND t.transactionDate >= :startDate AND t.transactionDate <= :endDate ORDER BY t.transactionDate ASC")
	List<Transaction> findTransactions(String accountNumber, LocalDateTime startDate, LocalDateTime endDate);
	
	@Query("SELECT t from Transaction t where t.transactionDate >= :start AND t.transactionDate <= :end")
	List<Transaction> findTransactionsByTime(LocalDateTime start, LocalDateTime end);
	
	// 
	@Query("Select new com.fis.bank.model.Report(t.transactionDate,t.toAccount.customer.customerType,t.status,COUNT(t.id), SUM(t.amount)) "
			+ "FROM Transaction t WHERE t.transactionDate >= :start AND t.transactionDate <= :end AND t.status = :status "
			+ "GROUP BY t.transactionDate,t.toAccount.customer.customerType")
	List<Report> findReport(LocalDateTime start,LocalDateTime end,int status);
}
