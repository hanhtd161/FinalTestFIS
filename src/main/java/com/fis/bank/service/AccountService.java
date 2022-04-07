package com.fis.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fis.bank.dto.AccountDTO;
import com.fis.bank.entity.Account;
import com.fis.bank.entity.Customer;

public interface AccountService {
	Page<Account> findAllAccountOrderByCustomerName(Pageable pageable);
	
	List<Account> findActiveAccountsByCustomerId(long customerId);
	
	List<Account> findInactiveAccountsByCustomerId(long customerId);
	
	Account findAccountById(long accountId);
	
	Account findAccountByAccountNumber(String accountNumber);
	
	Account createAccount(AccountDTO accountDTO,Customer customer);
	
	Account approveAccount(long accountId);
	
	Account updateAccountStatus(long accountId,int status);
	
	Boolean existByAccountNumber(String accountNumber);
}
