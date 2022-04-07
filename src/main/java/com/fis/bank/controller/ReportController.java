package com.fis.bank.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fis.bank.service.TransactionService;

@RestController
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/transaction-time")
	public ResponseEntity<?> findAllTransactions(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end){
		return ResponseEntity.ok(transactionService.findAllTransactions(start, end));
	}
}
