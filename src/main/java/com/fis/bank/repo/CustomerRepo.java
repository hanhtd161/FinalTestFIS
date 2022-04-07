package com.fis.bank.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fis.bank.entity.Customer;
import com.fis.bank.entity.Customer.CustomerType;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	Page<Customer> findAllByOrderByNameAsc(Pageable pageable);
	
	@Query("SELECT c from Customer c WHERE (:name is null or c.name LIKE %:name%) and (:mobile is null or c.mobile LIKE %:mobile%) "
			+ "and (:identityNo is null or c.identityNo LIKE %:identityNo%) and (:customerType is null or c.customerType = :customerType) "
			+ "and (:status = -1 or c.status = :status) ORDER BY c.name ASC")
	List<Customer> findByCustomInfo(String name, String mobile, String identityNo, CustomerType customerType,int status);
	Boolean existsByIdentityNo(String identityNo);
}

