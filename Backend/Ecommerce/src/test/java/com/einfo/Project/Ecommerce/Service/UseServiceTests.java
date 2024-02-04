package com.einfo.Project.Ecommerce.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//import org.junit.jupiter.api.Assertions;
import org.assertj.core.annotations.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.einfo.Project.Ecommerce.Exception.UserAlaradyExiest;
import com.einfo.Project.Ecommerce.Model.Category;
import com.einfo.Project.Ecommerce.Model.Product;
import com.einfo.Project.Ecommerce.Model.User;
import com.einfo.Project.Ecommerce.dto.UserRequest;
import com.einfo.Project.Ecommerce.repo.ProductrRepo;
import com.einfo.Project.Ecommerce.repo.UserRepo;
import com.einfo.Project.Ecommerce.service.userService;

@ExtendWith(MockitoExtension.class)
public class UseServiceTests {
	
	@Mock
	UserRepo repo;
	
	@InjectMocks
	userService userservice;
	
	@Mock
	ProductrRepo prepo;
	
	@Test
	public void saveusertest() throws UserAlaradyExiest {
		
		UserRequest usrerequest = new UserRequest("John", "Doe", "1234567890", 
				"john@example.com", "password", "USA", LocalDate.of(2023,06,27), "ROLE_USER");
		User user=User.builder()
				.firstName(usrerequest.getFirstName())
				.lastName(usrerequest.getLastName())
		        . phonenum(usrerequest.getPhonenum())
	          	. email(usrerequest.getEmail())
	       	 . password(usrerequest.getPassword())
	       	 .country(usrerequest.getCountry())
	       	 .signupDate(usrerequest.getSignupDate())
	       	.role(usrerequest.getRole()) .build();
		BDDMockito.given(repo.findByEmail(user.getEmail())).willReturn(Optional.empty());
		BDDMockito.given(repo.save(BDDMockito.any(User.class))).willReturn(user);
		User saveduser= userservice.saveuser(usrerequest);
		
		Assertions.assertThat(saveduser).isNotNull();
	}
	
	@Test
	public void saveuserEXIESTuser()   {
		
		UserRequest usrerequest = new UserRequest("John", "Doe", "1234567890", 
				"john@example.com", "password", "USA", LocalDate.of(2023,06,27), "ROLE_USER");
		User user=User.builder()
				.firstName(usrerequest.getFirstName())
				.lastName(usrerequest.getLastName())
		        . phonenum(usrerequest.getPhonenum())
	          	. email(usrerequest.getEmail())
	       	 . password(usrerequest.getPassword())
	       	 .country(usrerequest.getCountry())
	       	 .signupDate(usrerequest.getSignupDate())
	       	.role(usrerequest.getRole()) .build();
		BDDMockito.given(repo.findByEmail(user.getEmail())).willReturn(Optional.of(user));
//		BDDMockito.given(repo.save(BDDMockito.any(User.class))).willReturn(user);
//		User saveduser= userservice.saveuser(usrerequest);
		
		org.junit.jupiter.api.Assertions.assertThrows(UserAlaradyExiest.class,()->{
			userservice.saveuser(usrerequest);
		});
   
		 verify(repo,never()).save(any(User.class));
	}
	
	@Test
	public void testforgetalluser() {
		
		User user=User.builder()
				.firstName("JOHN")
				.lastName("DOWe")
		        . phonenum("1234567890")
	          	. email("John@gmail.com")
	       	 . password("password")
	       	 .country("USA")
	       	 .signupDate(LocalDate.of(2023,06,27))
	       	.role("ROLE_USER") .build();
		User user1=User.builder()
				.firstName("RON")
				.lastName("DOWe")
		        . phonenum("1234657890")
	          	. email("ohn@gmail.com")
	       	 . password("password")
	       	 .country("UAE")
	       	 .signupDate(LocalDate.of(2023,06,7))
	       	.role("ROLE_USER") .build();
	
		 BDDMockito.given(repo.findAll()).willReturn(List.of(user,user1));
		 
		 List<User>userlist=userservice.getAllUsers();
		 
		 Assertions.assertThat(userlist).isNotNull();
		 
		 
	}
	
	@Test
	public void  getproductbyIdtest() {
		
		Product p=new Product(1,"screnshot-11","this is nice product",230,"screnshot-11","watch");
		
		 BDDMockito.given(prepo.findById(1)).willReturn(List.of(p));
		 
		 Product getproduct=  userservice.getProductById(1);
		 
		Assertions.assertThat(getproduct).isNotNull();

		
	}
	

	
	

}
