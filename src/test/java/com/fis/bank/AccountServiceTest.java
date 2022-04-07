package com.fis.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fis.bank.entity.Account;
import com.fis.bank.exception.AppException;
import com.fis.bank.repo.AccountRepo;
import com.fis.bank.service.imp.AccountServiceImp;

@SpringBootTest
public class AccountServiceTest {
	@Mock
	private AccountRepo accountRepo;
	
	@InjectMocks
	private AccountServiceImp accountService;
	
	@Test
	public void testFindByAccountId_GiveAccountId_ReturnAccountWithGivenAccountId() {
		Account account = new Account();
		account.setId(1L);
		when(accountRepo.findById(1L)).thenReturn(Optional.of(account));
		when(accountRepo.findById(2L)).thenReturn(Optional.ofNullable(null));
		assertThat(accountService.findAccountById(1L).equals(account));
		assertThrows(AppException.class, ()-> accountService.findAccountById(2L));	
	}
	
	
	public void testFindByAccountNumber_GiveAccountId_ReturnAccountWithGivenAccountNumber() {
		Account account = new Account();
		account.setId(1L);
		account.setAccountNumber("012345678912");
		when(accountRepo.findByAccountNumber("012345678912")).thenReturn(Optional.of(account));
		when(accountRepo.findByAccountNumber("112345678912")).thenReturn(Optional.ofNullable(null));
		assertThat(accountService.findAccountByAccountNumber("012345678912").equals(account));
		assertThrows(AppException.class, ()-> accountService.findAccountByAccountNumber("112345678912"));	
	}
	
	@Test
	public void testFindByAccountId_GiveAccountNumber_ReturnAccountWithGivenAccountNumber() {
		Account account = new Account();
		account.setId(1L);
		account.setAccountNumber("012345678912");
		when(accountRepo.findByAccountNumber("012345678912")).thenReturn(Optional.of(account));
		when(accountRepo.findByAccountNumber("112345678912")).thenReturn(Optional.ofNullable(null));
		assertTrue(accountService.existByAccountNumber("012345678912"));
		assertFalse(accountService.existByAccountNumber("112345678912"));
	}
	
}
