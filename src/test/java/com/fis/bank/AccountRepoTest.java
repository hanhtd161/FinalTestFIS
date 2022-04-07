package com.fis.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fis.bank.entity.Account;
import com.fis.bank.entity.Customer;
import com.fis.bank.entity.Customer.CustomerType;
import com.fis.bank.repo.AccountRepo;

@SpringBootTest
public class AccountRepoTest {

	@Autowired
	private AccountRepo accountRepo;
	
	@BeforeEach
	public void setUp() {
		Customer customer1 = Customer.builder()
				.name("Hanh")
				.customerType(CustomerType.INDIVIDUAL)
				.identityNo("1234567890")
				.birthday(LocalDateTime.now())
				.address("ABC")
				.status(1)
				.build();
		Account account1 = new Account();
		account1.setAccountNumber("01234567890123");
		account1.setStatus(1);
		account1.setBalance(500000d);
		account1.setCustomer(customer1);
		customer1.getAccountSet().add(account1);
		Account account2 = new Account();
		account2.setAccountNumber("01234567890121");
		account2.setStatus(1);
		account2.setBalance(300000d);
		account2.setCustomer(customer1);
		customer1.getAccountSet().add(account2);
		accountRepo.save(account1);
		accountRepo.save(account2);
	}
	
	@Test
	public void testFindByAccountNumber_givenAccounts_returnAccountWithGivenAccountNumber() {
		Optional<Account> optional = accountRepo.findByAccountNumber("01234567890123");
		assertThat(optional.get().getStatus()==1);
		assertThat(optional.get().getBalance()==500000);
	}
	
	@Test
	public void testFindActiveAccount_givenAccounts_returnListAccountsWithGivenCustomerId() {
		//mock
		Customer customer1 = Customer.builder()
				.name("Hanh")
				.customerType(CustomerType.INDIVIDUAL)
				.identityNo("1234567898")
				.birthday(LocalDateTime.now())
				.address("ABC")
				.status(1)
				.build();
		Account account1 = new Account();
		account1.setAccountNumber("123456789");
		account1.setStatus(1);
		account1.setBalance(500000d);
		account1.setCustomer(customer1);
		customer1.getAccountSet().add(account1);
	    Account savedAccount = accountRepo.save(account1);
	    // test
	    Long id = savedAccount.getCustomer().getId();
		List<Account> list = accountRepo.findActiveAccountByCustomerIdOrderByAccountNumber(id);
		assertThat(list.size() == 1);
		assertThat(list.get(0).getAccountNumber().equals("123456789"));
	}
	
	@AfterEach
	public void after() {
		accountRepo.deleteAll();
	}
}
