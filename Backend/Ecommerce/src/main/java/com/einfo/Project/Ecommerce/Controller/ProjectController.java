package com.einfo.Project.Ecommerce.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.razorpay.*;
import org.springframework.security.core.Authentication;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.HttpHeaders;
//import org.togglz.core.Feature;
//import org.togglz.core.manager.FeatureManager;
//import org.togglz.core.util.NamedFeature;
import com.einfo.Project.Ecommerce.Exception.UserAlaradyExiest;
import com.einfo.Project.Ecommerce.Model.Addresss;
import com.einfo.Project.Ecommerce.Model.Category;
import com.einfo.Project.Ecommerce.Model.Product;
import com.einfo.Project.Ecommerce.Model.User;
import com.einfo.Project.Ecommerce.dto.Addressdto;
import com.einfo.Project.Ecommerce.dto.AuthRequest;
import com.einfo.Project.Ecommerce.dto.Categorydto;
import com.einfo.Project.Ecommerce.dto.ProductResponce;
import com.einfo.Project.Ecommerce.dto.UserRequest;
import com.einfo.Project.Ecommerce.dto.Userseller;
import com.einfo.Project.Ecommerce.repo.ProductrRepo;
import com.einfo.Project.Ecommerce.repo.UserRepo;
import com.einfo.Project.Ecommerce.service.Categoryservice;
import com.einfo.Project.Ecommerce.service.ImageService;
import com.einfo.Project.Ecommerce.service.JwtService;
import com.einfo.Project.Ecommerce.service.userService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
//@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
@CrossOrigin
public class ProjectController {

	Logger log = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	userService uservice;

	@Autowired
	UserRepo repo;

	@Autowired
	ImageService service;

	@Autowired
	ProductrRepo productrepo;

	@Autowired
	Categoryservice cService;
	

	
	@Autowired
	private JwtService jwtservice;
	
	RestTemplate restTemplete=new RestTemplate();

	@Autowired
	private AuthenticationManager authenticationManager;
	// private FeatureManager manager;
//    
	// public static final Feature DISCOUNT_APPLIED = new
	// NamedFeature("DISCOUNT_APPLIED");

//    @PostMapping("/signup")
//	public User saveuser(@RequestBody User user)
//	{  
//   	 return repo.save(user);
//	}
//     public ProjectController(FeatureManager manager) {
//         this.manager = manager;
//     }
	@PostMapping("/signup")
	public ResponseEntity<User> saveuser(@RequestBody @Valid UserRequest userRequest) throws UserAlaradyExiest {
		log.info("user is add in database");
		return new ResponseEntity<>(uservice.saveuser(userRequest), HttpStatus.CREATED);
	}

//	@PostMapping("/authenticate")
//	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
//		if (authentication.isAuthenticated()) {
//			log.info("token genrated");
//			return jwtservice.generateToken(authRequest.getEmail());
//		} else {
//			log.info("user is invalied");
//			throw new UsernameNotFoundException("invalid user request !");
//		}
//
//	}
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			log.info("token genrated");
			   String token = jwtservice.generateToken(authRequest.getEmail());
	            return ResponseEntity.ok(token);
//	     return  ResponseEntity<String>(jwtservice.generateToken(authRequest.getEmail()), HttpStatus.OK); 
		} else {
			log.info("user is invalied");
			throw new UsernameNotFoundException("invalid user request !");
		}

	}

//   @PostMapping("/login")
//	public ResponseEntity<User>getLogin(@RequestBody User user){
//   	User userdata =uservice.userlogin(user.getEmail(),user.getPassword());
//   	System.out.println(userdata);
//   	if(userdata==null) {
//   		log.info("user is not  present");
//   		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//   	}
//   	else {
//   		log.info("user is present");
//   		return ResponseEntity.ok().build();
//   		
//   	}
//   	
//   }

	@PostMapping("image/add")
//	   @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> productImageupload(@RequestParam("file") MultipartFile file,
			@RequestParam("pname") String productName, @RequestParam("description") String Description,
			@RequestParam("price") int productPrice, @RequestParam("category") String category) throws IOException {
		String imagename = service.uploadImage(file);

		Product newproduct = productrepo.save(Product.builder().pname(productName).description(Description)
				.price(productPrice).category(category).pimagename(imagename).build());
		log.info("product with image is add ");
		return ResponseEntity.status(HttpStatus.OK).body("prodct and image uploaded successfuly");

	}

	@GetMapping("image/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) {

		byte[] imageData = service.downloadImage(fileName);
		log.info("image is dowloaded");
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);

	}
	@GetMapping("/product")
	 public List<Product>getAllProduct(){
		 
		return  productrepo.findAll();
		 
	 }
	 

	@GetMapping("/productbypage")
	public ResponseEntity<ProductResponce>  getproduct(@RequestParam(value="pagenumber",defaultValue="0",required=false) int pagenumber,
			@RequestParam(value="pagesize",defaultValue="6",required=false)int pagesize)
	{
		log.info("list of product lodded");
		ProductResponce res= uservice.getallproduct(pagenumber, pagesize);
		System.out.println(res);
//	System.out.println(DISCOUNT_APPLIED);
//	 if (manager.isActive(DISCOUNT_APPLIED)) {
//            return applyDiscount(uservice.getallproduct());
//        } else {
//		Pageable p=PageRequest.of(pagenumber, pagesize);
//		Page<Product>pageproduct=productrepo.findAll(p);
//	   List<Product>allproduct=pageproduct.getContent();
//		 HttpHeaders responseHeaders = new HttpHeaders();
//		    responseHeaders.set("X-Total-Pages", String.valueOf(pageproduct.getTotalPages()));
//            System.out.println(responseHeaders); 
//		 return ResponseEntity.ok()
//			        .headers(responseHeaders)
//			        .body(allproduct + String.valueOf(pageproduct.getTotalPages())
		return new ResponseEntity<ProductResponce>(res,HttpStatus.OK);
	}

	
	@PostMapping("/addcategory")
//     @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Category> categorysave(@RequestBody @Valid Categorydto categorydto) {
		log.info("category is add ");
		return new ResponseEntity<>(cService.savecategory(categorydto), HttpStatus.CREATED);
	}

	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> categories = cService.getcategory();
		log.info(" list category is loadded");
		return ResponseEntity.ok().body(categories);
	}

//	@GetMapping("/productDetails/{id}")
//	public Product getProductById(@PathVariable int id) {
//		log.info("give product details usign id");
//		return uservice.getProductById(id);
//	}
	@GetMapping("/productDetails/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
	    log.info("Getting product details using id: " + id);
	    Product product = uservice.getProductById(id);
	    
	    if (product == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(product);
	    }
	}






//private List<Product> applyDiscount(List<Product> availableProducts) {
//List<Product> orderListAfterDiscount = new ArrayList<>();
//uservice.getallproduct().forEach(order -> {
//order.setPrice(order.getPrice() - (order.getPrice() * 5 / 100));
//orderListAfterDiscount.add(order);
//});
//return orderListAfterDiscount;
//}
	@PostMapping("/checkseller")
	public ResponseEntity<String>checkforseller(@RequestParam("email")String email, @RequestParam("password") String password){ 
		 System.out.println(email);
		Userseller userseller= restTemplete.getForObject("http://localhost:9000/getseller?email={email}&password={password}",Userseller.class, email, password);
//				getForObject("http://localhost:9000/getseller?email={email}&password={password}",Userseller.class, email, password);
	    if (userseller == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	    }
	    if (userseller.getUserType().equals("Seller")) {
	        return ResponseEntity.status(HttpStatus.OK).body("You are a seller, add the product");
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body("You are not a seller");
	    }
       }
	
	@GetMapping("/viewuser")
    public List<User> getAllUsers() {
    	log.info("Customer List printed in frontend");
        return uservice.getAllUsers();
    }
	 @PostMapping("/updateuser")
	    public void updateUser(@RequestBody User user) {
	      log.info("Updating user with id: ");
	    	System.out.println(user);
	    	repo.save(user);
	    }
	 @GetMapping("/deleteuser")
	    public void deleteUser(@RequestParam int id) {
	        log.info("Delete Customer with email " + id);
//	        userServiceImpl.deleteUser(id);
	        repo.deleteById(id);
	        
	    }
	 @GetMapping("/customerdetail")
	    public ResponseEntity<User> getUserDetailsById(@RequestParam int id) {
	    	log.info("Detail Of Customer with ID"+id);
	        User user = repo.findById(id)
	            .orElseThrow();
	        return ResponseEntity.ok(user);
	    }
	 @GetMapping("/totaluser")
	    public ResponseEntity<Long> countEmail() {
	    	log.info("Counting total number of Customers");
	        return uservice.countEmail();
	    }

	 
	  @PostMapping("/addaddress")
	  public ResponseEntity<Addresss>addresssave(@RequestBody @Valid Addressdto addressdto){
		  log.info("Address is add");
		  return new ResponseEntity<>(uservice.saveaddress(addressdto), HttpStatus.CREATED);
	  }
	  
	  @PostMapping("/createorder")
		 public String  pymnetorder(@RequestBody Map<String,Object>  data) throws RazorpayException {
	    	   System.out.println(data);
	    	   int amt=Integer.parseInt(data.get("amount").toString());
	    	   var Client=new RazorpayClient("rzp_test_AI8eqhaiXHtxi2","fDK4YcdULY96q5wJJ6FrqBQ8");
		
	    	   JSONObject orderRequest = new JSONObject();
	    	   orderRequest.put("amount", amt*100); // amount in the smallest currency unit
	    	   orderRequest.put("currency", "INR");
	    	   orderRequest.put("receipt", "order_rcptid_11");
	    	  Order order  =Client.orders.create(orderRequest);
//	    	   Client.orders.create(orderRequest)
	    	   System.out.println(order);
			  return order.toString();
		 } 
}
