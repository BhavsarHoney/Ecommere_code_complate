package com.einfo.Project.Ecommerce.service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.einfo.Project.Ecommerce.Exception.UserAlaradyExiest;
import com.einfo.Project.Ecommerce.Model.Addresss;
import com.einfo.Project.Ecommerce.Model.Image;
import com.einfo.Project.Ecommerce.Model.Product;
import com.einfo.Project.Ecommerce.Model.User;
import com.einfo.Project.Ecommerce.dto.Addressdto;
import com.einfo.Project.Ecommerce.dto.Producdto;
import com.einfo.Project.Ecommerce.dto.ProductResponce;
import com.einfo.Project.Ecommerce.dto.UserRequest;
import com.einfo.Project.Ecommerce.dto.Userseller;
import com.einfo.Project.Ecommerce.repo.AddressRepo;
import com.einfo.Project.Ecommerce.repo.ProductrRepo;
import com.einfo.Project.Ecommerce.repo.UserRepo;

@Service
public class userService {
	@Autowired
	UserRepo repo;

	@Autowired
	ProductrRepo productrepo;
	
	@Autowired 
	AddressRepo arepo;
	
	
	RestTemplate restTemplete=new RestTemplate();
    
	public User saveuser(UserRequest userRequest) throws UserAlaradyExiest  {
	    Optional<User> li = repo.findByEmail(userRequest.getEmail());
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		 String brepctpassword=passwordEncoder.encode(userRequest.getPassword());
		if (!li.isEmpty()) {
			throw new UserAlaradyExiest("user Email:" + userRequest.getEmail() + "user is already present");
		}
					
		else {
			User user = User.build(0, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getPhonenum(),
					userRequest.getEmail(),brepctpassword,userRequest.getCountry(),userRequest.getSignupDate(),userRequest.getRole());
			return repo.save(user);
		}
	}

//	public User userlogin(String email, String password) {
//		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
//          List<User> li=repo.findByEmail(email);
//		
//		if (repo.findByEmail(email).size() > 0) {
//			User dbuser=li.get(0);
//			if (passwordEncoder.matches(password, dbuser.getPassword())) {
//				System.out.println("password mathch");
//				return  dbuser;
//			}
//			else {
//				System.out.println("password not match");
//				return null;
//			}
//		}
//		return null;
//
//	}

	public ProductResponce getallproduct(int pagenumber,int pagesize) {
		Pageable p=PageRequest.of(pagenumber, pagesize);
		Page<Product>pageproduct=productrepo.findAll(p);
		List<Product>allproduct=pageproduct.getContent();
		List<Producdto>productdto=allproduct.stream().map(product -> {
			   Producdto dto = new Producdto();
			    dto.setId(product.getId());
                dto.setPname(product.getPname());
                dto.setPimagename(product.getPimagename());
                dto.setDescription(product.getDescription());
                dto.setPrice(product.getPrice());
               return dto;
		}
			).collect(Collectors.toList());
		ProductResponce res=new ProductResponce();
		res.setContent(productdto);
		res.setPagenumber(pageproduct.getNumber());
		res.setPageSize(pageproduct.getSize());
		res.setTotalElement(pageproduct.getTotalElements());
		res.setTotalpages(pageproduct.getTotalPages());
		res.setLastpages(pageproduct.isLast());
		return res;
	}	
	 public Product getProductById(int id) {
	         return productrepo.findById(id).get(0);
	         }
	 
//	 public Userseller checkforseller(String email,String password) {
//		    Userseller userseller= restTemplete.getForObject("http://localhost:9000/getseller",Userseller.class);
//
//	 }
//	 public ResponseEntity<String> login(String email,String password ) {
//	
////		    User user = restTemplate.getForObject("http://user-service/getUserByEmailAndPassword?email={email}&password={password}", User.class, userLogin.getEmail(), userLogin.getPassword());
//		    Userseller userseller= restTemplete.getForObject("http://localhost:9000/getseller?email={email}&password={password}",Userseller.class, email, password);
//		    if (userseller == null) {
//		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
//		    }
//		    if (userseller.getUserType().equals("Seller")) {
//		        return ResponseEntity.status(HttpStatus.OK).body("You are a seller, add the product");
//		    } else {
//		        return ResponseEntity.status(HttpStatus.OK).body("You are not a seller");
//		    }
//		}
	 public List<User> getAllUsers() {
	        List<User> users = repo.findAll();
	        return users;
	    }
	 public ResponseEntity<Long> countEmail() {
	        Long count = repo.countEmail();
	        return ResponseEntity.ok(count);
	    }
	 
	 public Addresss saveaddress(Addressdto addressdto) {
		 Addresss add=Addresss
				 .build(0,addressdto.getFullname(),addressdto.getPincode(),
						 addressdto.getState(),addressdto.getCity(),addressdto.getAddress()
				 );
		 return arepo.save(add);
	 }
	 
	 
	   
}
