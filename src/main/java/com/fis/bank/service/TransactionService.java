package com.fis.bank.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fis.bank.dto.TransactionDTO;
import com.fis.bank.entity.Account;
import com.fis.bank.entity.Transaction;

public interface TransactionService {
	Transaction createTransaction(TransactionDTO transactionDTO,Account fromAccount,Account toAccount);
	List<Transaction> findTransactionOfAccount(String accountNumber, LocalDateTime startDate, LocalDateTime endDate);
	List<Transaction> findAllTransactions(LocalDateTime startDate, LocalDateTime endDate);
}
