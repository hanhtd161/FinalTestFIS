package com.fis.bank.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	
	@NotNull
	@Size(max = 100, message = "CUS001:Ten khach hang khong qua 100 ki tu")
	private String name;
	
	@NotBlank(message = "CUS002:Ngay sinh khong duoc bo trong")
	private LocalDateTime birthday;
	
	@NotBlank(message = "CUS003:Dia chi khong duoc bo trong")
	private String address;
	
	@Size(min =10, max =10, message = "CUS004:CMT phai co 10 ky tu so")
	private String identityNo;
	
	private String mobile;
	
	@NotNull(message = "CUS005:Loai khach hang khong duoc bo trong")
	private String customerType;
	
	@Size(min = 0, max = 1, message = "CUS006:status khong hop le")
	private int status;
	
	
}
