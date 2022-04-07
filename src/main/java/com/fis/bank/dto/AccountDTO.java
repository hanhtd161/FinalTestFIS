package com.fis.bank.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
	@NotNull(message = "ACC001:CustomerId is required")
	private Long customerId;
	@NotNull(message = "ACC002:CustomerId is required")
	@Size(min = 13,max = 13, message = "ACC003:accountNumber must be 13 digit character")
	private String accountNumber;
	@NotNull(message = "ACC004:Balance is required")
	private Double balance;
	
}
