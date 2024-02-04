package com.einfo.Project.Ecommerce.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.einfo.Project.Ecommerce.Model.Product;
import com.einfo.Project.Ecommerce.Model.User;
import com.einfo.Project.Ecommerce.dto.UserRequest;
import com.einfo.Project.Ecommerce.filter.Jwtfilter;
import com.einfo.Project.Ecommerce.repo.ProductrRepo;
import com.einfo.Project.Ecommerce.repo.UserRepo;
import com.einfo.Project.Ecommerce.service.Categoryservice;
import com.einfo.Project.Ecommerce.service.ImageService;
import com.einfo.Project.Ecommerce.service.JwtService;
import com.einfo.Project.Ecommerce.service.userService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProjectControllerTests {

	@Autowired
	private MockMvc mokmvc;

	@Autowired
	private ObjectMapper objmapper;

	@MockBean
	private userService uservice;

	@MockBean
	UserRepo userRepo;

	@MockBean
	ImageService service;

	@MockBean
	ProductrRepo productrepo;

	@MockBean
	Categoryservice cService;

	@MockBean
	JwtService jwtService;

	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	Jwtfilter jwtFilter;

	@Test
	public void saveusertests() throws JsonProcessingException, Exception {
		UserRequest usrerequest = new UserRequest("John", "Doe", "1234567890", "john@example.com", "password", "USA",
				LocalDate.of(2023, 6, 27), "ROLE_USER");

		User user = User.builder().firstName(usrerequest.getFirstName()).lastName(usrerequest.getLastName())
				.phonenum(usrerequest.getPhonenum()).email(usrerequest.getEmail()).password(usrerequest.getPassword())
				.country(usrerequest.getCountry()).signupDate(usrerequest.getSignupDate()).role(usrerequest.getRole())
				.build();

		BDDMockito.given(uservice.saveuser(usrerequest)).willReturn(user);

		ResultActions response = mokmvc.perform(
				post("/signup").contentType(MediaType.APPLICATION_JSON).content(objmapper.writeValueAsString(user)));

		response.andExpect(status().isCreated());
//	    .andExpect(jsonPath("$.firstName", CoreMatchers.is(user.getFirstName())))
//	    .andExpect(jsonPath("$.lastName", CoreMatchers.is(user.getLastName())))
//	    .andExpect(jsonPath("$.phonenum", CoreMatchers.is(user.getPhonenum())))
//	    .andExpect(jsonPath("$.email", CoreMatchers.is(user.getEmail())))
//	    .andExpect(jsonPath("$.password", CoreMatchers.is(user.getPassword())))
//	    .andExpect(jsonPath("$.country", CoreMatchers.is(user.getCountry())))
//	    .andExpect(jsonPath("$.signupDate", CoreMatchers.is(user.getSignupDate())))
//	    .andExpect(jsonPath("$.role", CoreMatchers.is(user.getRole())));    

	}

	@Test
	public void getallproductTest() throws Exception {

		List<Product> listofproduct = new ArrayList<>();
		listofproduct.add(Product.builder().pname("watch").description("this is watch").price(250)
				.pimagename("screenshot-23").category("watch").build());
		listofproduct.add(Product.builder().pname("shoes").description("this is shoes").price(280)
				.pimagename("screenshot-28").category("shoes").build());
		BDDMockito.given(productrepo.findAll()).willReturn(listofproduct);

		ResultActions response = mokmvc.perform(get("/product"));

		response.andExpect(status().isOk());

	}

	@Test
	public void getproductbyID() throws Exception {

		int id = 1;

		Product p = Product.builder().id(id).pname("watch").description("this is watch").price(250).pimagename("screenshot-23")
				.category("watch").build();

		BDDMockito.given(uservice.getProductById(p.getId())).willReturn(p);

		ResultActions response = mokmvc.perform(get("/productDetails/" + id));
		response.andExpect(status().isOk());

	}

	@Test
	public void getproductbyIDnagativesnarito() throws Exception {

		int id = 1;
		Product p = Product.builder().id(id).pname("watch").description("this is watch").price(250).pimagename("screenshot-23")
				.category("watch").build();
		BDDMockito.given(uservice.getProductById(p.getId())).willReturn(null);

		ResultActions response = mokmvc.perform(get("/productDetails/", id));
		response.andExpect(status().isNotFound());

	}

}
