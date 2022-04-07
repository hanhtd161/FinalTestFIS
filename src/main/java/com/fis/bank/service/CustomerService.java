package com.fis.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fis.bank.entity.Customer;
import com.fis.bank.entity.Customer.CustomerType;

public interface CustomerService {
	Page<Customer> findAllCustomers(Pageable pageable);
	Customer findById(long customerId);
	Customer createCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	List<Customer> findByCustomInfo(String name, String mobile, String identityNo, CustomerType customerType,int status);
	Boolean existByIdentityNo(String identityNo);
}
