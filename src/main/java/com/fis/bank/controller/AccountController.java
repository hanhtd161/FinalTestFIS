package com.fis.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fis.bank.dto.AccountDTO;
import com.fis.bank.entity.Customer;
import com.fis.bank.exception.AppException;
import com.fis.bank.service.AccountService;
import com.fis.bank.service.CustomerService;
import com.fis.bank.ulti.Constant;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/get-all")
	public ResponseEntity<?> getAllAccounts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ){
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(accountService.findAllAccountOrderByCustomerName(pageable));
	}
	
	@GetMapping("/get-active/{customerId}")
	public ResponseEntity<?> getActiveAccountByCustomerId(@PathVariable long customerId){
		return ResponseEntity.ok(accountService.findActiveAccountsByCustomerId(customerId));
	}
	
	@GetMapping("/get-inactive/{customerId}")
	public ResponseEntity<?> getInactiveAccountByCustomerId(@PathVariable long customerId){
		return ResponseEntity.ok(accountService.findInactiveAccountsByCustomerId(customerId));
	}
	
	@GetMapping("/get-by-id/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable long id){
		return ResponseEntity.ok(accountService.findAccountById(id));
	}
	
	@GetMapping("/get/{accountNumber}")
	public ResponseEntity<?> getAccountById(@PathVariable String accountNumber){
		return ResponseEntity.ok(accountService.findAccountByAccountNumber(accountNumber));
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO){
		if(accountService.existByAccountNumber(accountDTO.getAccountNumber())) {
			throw new AppException("ACC005","AccountNumber is existed");
		};
		Customer customer = customerService.findById(accountDTO.getCustomerId());
		return ResponseEntity.ok(accountService.createAccount(accountDTO, customer));	
	}
	
	@GetMapping("/approve/{accountId}")
	public ResponseEntity<?> approveAccount(@PathVariable long accountId){
		return ResponseEntity.ok(accountService.approveAccount(accountId));
	}
	
	@GetMapping("/update/{accountId}")
	public ResponseEntity<?> updateAccount(@PathVariable long accountId, @RequestParam(required = true) int status){
		if(status == Constant.ACCOUNT_STATUS_WAITING) {
			throw new AppException("ACC302", "Tai khoan chua duoc phe duyet");
		}
		if (status!= Constant.ACCOUNT_STATUS_ACTIVE && status != Constant.CUSTOMER_STATUS_INACTIVE) {
			throw new AppException("ACC301", "Trang thai truyen vao khong hop le");
		}
		return ResponseEntity.ok(accountService.approveAccount(accountId));
	}
}
