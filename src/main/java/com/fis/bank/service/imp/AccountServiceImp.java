package com.fis.bank.service.imp;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fis.bank.dto.AccountDTO;
import com.fis.bank.entity.Account;
import com.fis.bank.entity.Customer;
import com.fis.bank.exception.AppException;
import com.fis.bank.repo.AccountRepo;
import com.fis.bank.service.AccountService;
import com.fis.bank.ulti.Constant;

@Service
public class AccountServiceImp implements AccountService {
	
	public static final Logger Logger = LoggerFactory.getLogger(AccountServiceImp.class);
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Override
	public Page<Account> findAllAccountOrderByCustomerName(Pageable pageable) {
		return accountRepo.findAllByOrderByCustomer_NameAsc(pageable);
	}

	@Override
	public List<Account> findActiveAccountsByCustomerId(long customerId) {
		Logger.info("find active account with customer id {}",customerId);
		return accountRepo.findActiveAccountByCustomerIdOrderByAccountNumber(customerId);
	}

	@Override
	public List<Account> findInactiveAccountsByCustomerId(long customerId) {
		Logger.info("find inactive account with customer id {}",customerId);
		return accountRepo.findInactiveAccountByCustomerIdOrderByAccountNumber(customerId);
	}

	@Override
	public Account findAccountById(long accountId) {
		return accountRepo.findById(accountId).orElseThrow(()-> new AppException("ACC101","Account Id khong ton tai"));
	}

	@Override
	public Account findAccountByAccountNumber(String accountNumber) {
		return accountRepo.findByAccountNumber(accountNumber).orElseThrow(()-> new AppException("ACC102","AccountNumber khong ton tai"));
	}

	@Override
	public Account createAccount(AccountDTO accountDTO,Customer customer) {
		Account account = new Account();
		account.setAccountNumber(accountDTO.getAccountNumber());
		account.setBalance(accountDTO.getBalance());
		account.setCustomer(customer);
		customer.getAccountSet().add(account);
		return accountRepo.save(account);
	}

	@Override
	public Account approveAccount(long accountId) {
		Logger.info("approve account with id {}",accountId);
		Account account = findAccountById(accountId);
		account.setStatus(Constant.ACCOUNT_STATUS_ACTIVE);
		account.setUpdateDatetime(LocalDateTime.now());
		return accountRepo.save(account);
	}

	@Override
	public Account updateAccountStatus(long accountId, int status) {
		Logger.info("update status account with id {}",accountId);
		Account account = findAccountById(accountId);
		account.setStatus(status);
		account.setUpdateDatetime(LocalDateTime.now());
		return accountRepo.save(account);
	}

	@Override
	public Boolean existByAccountNumber(String accountNumber) {
		return accountRepo.findByAccountNumber(accountNumber).isPresent();
	}



}
