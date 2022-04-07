package com.fis.bank.model;

import java.time.LocalDateTime;

import com.fis.bank.entity.Customer.CustomerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
	private LocalDateTime date;
	private CustomerType customerType;
	private int status;
	private long quantity;
	private double amount;
}
