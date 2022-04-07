package com.fis.bank.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,unique = true)
	private String accountNumber;
	
	@Column(nullable = false)
	private Double balance;
	
	@Column(nullable = false)
	private int status;
	
	@Column(nullable = false)
	private LocalDateTime createDatetime;
	
	private LocalDateTime updateDatetime;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "fromAccount")
	@JsonIgnore
	@Exclude
	private Set<Transaction> fromTransactionSet = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "toAccount")
	@JsonIgnore
	@Exclude
	private Set<Transaction> toTransactionSet = new HashSet<>();
	
	@PrePersist
	public void createDatetime() {
		this.createDatetime = LocalDateTime.now();
	}
	
}

