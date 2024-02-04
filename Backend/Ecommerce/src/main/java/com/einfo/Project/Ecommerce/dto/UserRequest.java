package com.einfo.Project.Ecommerce.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
      @NotNull(message ="firstname should not be null")
	 private String firstName;
      @NotNull(message ="lastname should not be null")
	 private String lastName;
     @NotNull 
     @Pattern(regexp="^[0-9]{10,10}$", message="invalid mobile Number" )
	 private String phonenum;
	 @Email(message ="invalid email addresss")
	 private String email;
	 private String password;
	 @NotNull(message ="country should not be null")
	 private String country;
	 private LocalDate signupDate =LocalDate.now();
	 private String role;
	 
}
