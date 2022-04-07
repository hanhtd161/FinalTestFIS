package com.fis.bank.service.imp;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fis.bank.dto.TransactionDTO;
import com.fis.bank.entity.Account;
import com.fis.bank.entity.Transaction;
import com.fis.bank.repo.TransactionRepo;
import com.fis.bank.service.TransactionService;
import com.fis.bank.ulti.Constant;

@Service
public class TransactionServiceImp implements TransactionService {
	@Autowired
	private TransactionRepo transactionRepo;

	@Override
	@Transactional
	public Transaction createTransaction(TransactionDTO transactionDTO, Account fromAccount, Account toAccount) {
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setContent(transaction.getContent());
		
		transaction.setFromAccount(fromAccount);
		fromAccount.getFromTransactionSet().add(transaction);
		
		transaction.setToAccount(toAccount);
		toAccount.getToTransactionSet().add(transaction);
		transaction.setTransactionDate(LocalDateTime.now());
		
		if(fromAccount.getStatus()!= Constant.ACCOUNT_STATUS_ACTIVE) {
			transaction.setStatus(Constant.TRANSACTION_STATUS_FAIL_FROMACCOUNT_INVALID);
			transaction.setErrorReason("Tai khoan chuyen tien khong hop le");
			return transactionRepo.save(transaction);
		}
		if(toAccount.getStatus()!= Constant.ACCOUNT_STATUS_ACTIVE) {
			transaction.setStatus(Constant.TRANSACTION_STATUS_FAIL_TOACCOUNT_INVALID);
			transaction.setErrorReason("Tai khoan nhan tien khong hop le");
			return transactionRepo.save(transaction);
		}
		if(fromAccount.getBalance() < transactionDTO.getAmount()) {
			transaction.setStatus(Constant.TRANSACTION_STATUS_FAIL_AMOUNT_NOT_ENOUGH);
			transaction.setErrorReason("Tai khoan gui khong du tien");
			return transactionRepo.save(transaction);
		}
		fromAccount.setBalance(fromAccount.getBalance() - transactionDTO.getAmount());
		toAccount.setBalance(toAccount.getBalance() + transactionDTO.getAmount());
		transaction.setStatus(Constant.TRANSACTION_STATUS_SUCCESS);
		return transactionRepo.save(transaction);
	}



	@Override
	public List<Transaction> findTransactionOfAccount(String accountNumber, LocalDateTime startDate,
			LocalDateTime endDate) {
		return transactionRepo.findTransactions(accountNumber, startDate, endDate);
	}



	@Override
	public List<Transaction> findAllTransactions(LocalDateTime startDate, LocalDateTime endDate) {
		return transactionRepo.findTransactionsByTime(startDate, endDate);
	}


	
	
	
}
