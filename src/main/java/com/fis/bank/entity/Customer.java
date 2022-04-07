package com.fis.bank.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Exclude;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
	
	public enum CustomerType{
		INDIVIDUAL,CORPORATE
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDateTime birthday;
	
	@Column(nullable = false)
	private String address;
	
	@Column(unique = true, nullable = false)
	private String identityNo;
	
	private String mobile;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CustomerType customerType;
	
	@Column(nullable = false)
	private int status;
	
	@Column(nullable = false)
	private LocalDateTime createDatetime;
	
	private LocalDateTime updateDateTime;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	@JsonIgnore
	@Exclude
	@Builder.Default
	private Set<Account> accountSet = new HashSet<>();
	
	@PrePersist
	public void createDatetime() {
		this.createDatetime = LocalDateTime.now();
	}
	
	
}
