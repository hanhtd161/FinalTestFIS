package com.fis.bank.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_table")
@NoArgsConstructor
public class User {
	@Id
	private long id;
	public enum ERole{
		ROLE_USER,ROLE_MANAGER
	}
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private ERole role;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
		
}
