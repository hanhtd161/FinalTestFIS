package com.fis.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fis.bank.entity.Customer;
import com.fis.bank.exception.AppException;
import com.fis.bank.repo.CustomerRepo;
import com.fis.bank.service.imp.CustomerServiceImp;

@SpringBootTest
public class CustomerServiceTest {
	@Mock
	private CustomerRepo customerRepo;
	
	@InjectMocks
	private CustomerServiceImp service;
	
	@Test
	public void testFindById_GivenId_ReturnCustomerWithGivenId() {
		Customer customer = new Customer();
		customer.setId(1L);
		when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
		when(customerRepo.findById(2L)).thenReturn(Optional.ofNullable(null));
		assertThat(service.findById(1L).equals(customer));
		assertThrows(AppException.class,()->service.findById(2L));
	}
	
	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setCreateDatetime(LocalDateTime.now());
		when(customerRepo.save(customer)).thenReturn(customer);
		assertThat(service.createCustomer(customer).equals(customer));
	}
	
	@Test
	public void updUpdateCustomer() {
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setUpdateDateTime(LocalDateTime.now());
		when(customerRepo.save(customer)).thenReturn(customer);
		assertThat(service.updateCustomer(customer).equals(customer));
	}
}
