package com.einfo.Project.Ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producdto {
	private int id;
	@NotNull(message ="product name should not be null")
	private  String pname;
	@NotNull(message ="description should not be null")
	   private String description;
	@NotNull(message ="price should not be null")
	   private long price;
	@NotNull(message ="price should not be null")
	   private String pimagename;
} 
