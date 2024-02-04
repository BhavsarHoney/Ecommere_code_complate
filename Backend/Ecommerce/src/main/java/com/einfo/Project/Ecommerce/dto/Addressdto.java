package com.einfo.Project.Ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addressdto {
	 @NotNull(message ="Fullname  should not be null")
	private String fullname;
	 @NotNull(message ="pincode should not be null")
	private  String pincode;
	 @NotNull(message ="State should not be null")
	private String State;
	 @NotNull(message ="city should not be null")
	private String city;
	 @NotNull(message ="Address should not be null")
	private String address;
}
