package com.fis.bank.service.imp;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fis.bank.entity.Customer;
import com.fis.bank.entity.Customer.CustomerType;
import com.fis.bank.exception.AppException;
import com.fis.bank.repo.CustomerRepo;
import com.fis.bank.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService{
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Page<Customer> findAllCustomers(Pageable pageable) {
		return customerRepo.findAllByOrderByNameAsc(pageable);
	}

	@Override
	public Customer findById(long customerId) {
		return customerRepo.findById(customerId).orElseThrow(() -> new AppException("CUS101","Customer Id Not Found!"));
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	@Override
	public Customer updateCustomer(Customer customer) {
		customer.setUpdateDateTime(LocalDateTime.now());
		return customerRepo.save(customer);
	}

	@Override
	public List<Customer> findByCustomInfo(String name, String mobile, String identityNo, CustomerType customerType,int status) {
		return customerRepo.findByCustomInfo(name,mobile,identityNo,customerType,status);
	}

	@Override
	public Boolean existByIdentityNo(String identityNo) {
		return customerRepo.existsByIdentityNo(identityNo);
	}

}
