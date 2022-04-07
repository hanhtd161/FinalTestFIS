package com.fis.bank.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fis.bank.dto.TransactionDTO;
import com.fis.bank.entity.Account;
import com.fis.bank.service.AccountService;
import com.fis.bank.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO transactionDTO){
		Account fromAccount = accountService.findAccountById(transactionDTO.getFromAccountId());
		Account toAccount = accountService.findAccountById(transactionDTO.getToAccountId());
		return ResponseEntity.ok(transactionService.createTransaction(transactionDTO, fromAccount, toAccount));
	}
	
	@GetMapping("/accountNumber")
	public ResponseEntity<?> findTransactionByAccountNumBer(@RequestParam String accountNumber,
			@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate){
		return ResponseEntity.ok(transactionService.findTransactionOfAccount(accountNumber, startDate, endDate));
	}
}
