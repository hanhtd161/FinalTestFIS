package com.fis.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fis.bank.entity.Customer;
import com.fis.bank.entity.Customer.CustomerType;
import com.fis.bank.repo.CustomerRepo;

@SpringBootTest
public class CustomerRepoTest {
	@Autowired
	private CustomerRepo customerRepo;
	
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
		Customer customer2 = Customer.builder()
				.name("Tung")
				.customerType(CustomerType.INDIVIDUAL)
				.identityNo("1234567894")
				.birthday(LocalDateTime.now())
				.address("ABC")
				.status(1)
				.build();
		customerRepo.save(customer1);
		customerRepo.save(customer2);
	}
	
	@Test
	public void testFindByCustomerInfo_givenCustomerInfo_returnListofCustomersWithGivenInfo() {
		List<Customer> list1 = customerRepo.findByCustomInfo("Hanh", null, null, null, 0);
		List<Customer> list2 = customerRepo.findByCustomInfo(null, null, null, CustomerType.INDIVIDUAL, -1);
		assertThat(list1.size() == 1);
		assertThat(list2.size() == 2);	
	}
	
	@Test
	public void testExistedByIdentity_givenCustomers_returnTrueWithExistedIdentityNo() {
		assertTrue(customerRepo.existsByIdentityNo("1234567894"));
		assertFalse(customerRepo.existsByIdentityNo("0123456789"));
	}
	
	
	@AfterEach
	public void after() {
		customerRepo.deleteAll();
	}
}
