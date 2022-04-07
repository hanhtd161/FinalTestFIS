package com.fis.bank.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fis.bank.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
	Page<Account> findAllByOrderByCustomer_NameAsc(Pageable pageable);
	
	@Query("SELECT a FROM Account a where a.customer.id = :customerId AND a.status = 1 ORDER BY a.accountNumber ASC ")
	List<Account> findActiveAccountByCustomerIdOrderByAccountNumber(long customerId);
	
	@Query("SELECT a FROM Account a where a.customer.id = :customerId AND a.status = 0 ORDER BY a.accountNumber ASC")
	List<Account> findInactiveAccountByCustomerIdOrderByAccountNumber(long customerId);
	
	Optional<Account> findByAccountNumber(String accountNumber);
	
	
}
