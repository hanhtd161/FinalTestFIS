package com.fis.bank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
	@NotNull(message = "TXT001:fromtAccount Id is required")
	private long fromAccountId;
	@NotNull(message = "TXT002:toAccount Id is required")
	private long toAccountId;
	@NotNull(message = "TXT003:Amount is required")
	private double amount;
	@NotBlank(message = "TXT004:Content is required")
	private String content;
	
}
