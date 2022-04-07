package com.fis.bank.controller;

import javax.validation.Valid;

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

import com.fis.bank.dto.CustomerDTO;
import com.fis.bank.entity.Customer;
import com.fis.bank.entity.Customer.CustomerType;
import com.fis.bank.model.ErrorMessage;
import com.fis.bank.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/get-all")
	public ResponseEntity<?> getAllCustomers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ){
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(customerService.findAllCustomers(pageable));
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable long id){
		return ResponseEntity.ok(customerService.findById(id));
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchCustomer(@RequestParam(required = false) String name,@RequestParam(required = false) String mobile,@RequestParam(required = false) String identityNo,
			@RequestParam(required = false) String customerType,@RequestParam(defaultValue = "-1") int status){
		CustomerType type = CustomerType.valueOf(customerType);
		return ResponseEntity.ok(customerService.findByCustomInfo(name, mobile, identityNo, type, status));
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerDTO customerDTO){
		if(customerDTO.getIdentityNo().matches("[0-9]+")) {
			return ResponseEntity.badRequest().body(new ErrorMessage("CUS007","CMT phai la chuoi 10 ky tu so"));
		}
		if(customerService.existByIdentityNo(customerDTO.getIdentityNo())) {
			return ResponseEntity.badRequest().body(new ErrorMessage("CUS008","CMT da duoc dang ky"));
		}
		CustomerType customerType = null;
		try {
			customerType = CustomerType.valueOf(customerDTO.getCustomerType());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorMessage("CUS009","Customer Type khong hop le "));
		}
		Customer customer = Customer.builder()
								.name(customerDTO.getName())
								.birthday(customerDTO.getBirthday())
								.address(customerDTO.getAddress())
								.mobile(customerDTO.getMobile())
								.status(customerDTO.getStatus())
								.customerType(customerType)
								.build();
		return ResponseEntity.ok(customerService.createCustomer(customer));
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable long id, @RequestBody @Valid CustomerDTO customerDTO){
		if(customerDTO.getIdentityNo().matches("[0-9]+")) {
			return ResponseEntity.badRequest().body(new ErrorMessage("CUS007","CMT phai la chuoi 10 ky tu so"));
		}
		if(customerService.existByIdentityNo(customerDTO.getIdentityNo())) {
			return ResponseEntity.badRequest().body(new ErrorMessage("CUS008","CMT da duoc dang ky"));
		}
		CustomerType customerType = null;
		try {
			customerType = CustomerType.valueOf(customerDTO.getCustomerType());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ErrorMessage("CUS009","Customer Type khong hop le "));
		}
		Customer customer = customerService.findById(id);
		customer.setName(customerDTO.getName());
		customer.setAddress(customerDTO.getAddress());
		customer.setBirthday(customerDTO.getBirthday());
		customer.setCustomerType(customerType);
		customer.setStatus(customerDTO.getStatus());
		customer.setMobile(customerDTO.getMobile());
		return ResponseEntity.ok(customerService.updateCustomer(customer));
	}
	
	
}
