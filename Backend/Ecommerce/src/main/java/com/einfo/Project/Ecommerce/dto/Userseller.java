package com.einfo.Project.Ecommerce.dto;

import java.time.LocalDate;

//import com.einfochips.ecommerce.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userseller {
        
	private Long id;
		
		private String email;
		private String firstName;
		private String lastName;
		private String password;
		private String country;
		private String userType;
		private LocalDate signupDate = LocalDate.now();
		private String phoneno;
		private String role;
}
