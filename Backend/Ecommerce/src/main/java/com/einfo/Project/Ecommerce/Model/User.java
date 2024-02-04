package com.einfo.Project.Ecommerce.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor(staticName="build")
@NoArgsConstructor
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String firstName;
 private String lastName;
 private String phonenum;
 private String email;
 private String password;
 private String country;
 private LocalDate signupDate =LocalDate.now();
// private String role="Customer";
 private String role;
 
 
}
