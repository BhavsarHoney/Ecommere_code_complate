package com.einfo.Project.Ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorydto {
	
	 @NotNull(message ="category should not be null")
	private String category;
	 @NotNull(message ="categorydescription  should not be null")
	private String categotydescription;
}
