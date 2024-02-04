////package com.einfo.Project.Ecommerce.Controller;
////
////import java.io.IOException;
////import java.util.ArrayList;
////import java.util.List;
////import org.togglz.core.Feature;
////import org.togglz.core.manager.FeatureManager;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.MediaType;
////import org.springframework.http.ResponseEntity;
////import org.springframework.validation.annotation.Validated;
////import org.springframework.web.bind.annotation.CrossOrigin;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.ModelAttribute;
////import org.springframework.web.bind.annotation.PathVariable;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestBody;
////import org.springframework.web.bind.annotation.RequestParam;
////import org.springframework.web.bind.annotation.RestController;
////import org.springframework.web.multipart.MultipartFile;
////
////import org.togglz.core.util.NamedFeature;
////
////import com.einfo.Project.Ecommerce.Exception.UserAlaradyExiest;
////import com.einfo.Project.Ecommerce.Model.Category;
////import com.einfo.Project.Ecommerce.Model.Product;
////import com.einfo.Project.Ecommerce.Model.User;
////import com.einfo.Project.Ecommerce.dto.Categorydto;
////import com.einfo.Project.Ecommerce.dto.UserRequest;
////import com.einfo.Project.Ecommerce.repo.ProductrRepo;
////import com.einfo.Project.Ecommerce.repo.UserRepo;
////import com.einfo.Project.Ecommerce.service.Categoryservice;
////import com.einfo.Project.Ecommerce.service.ImageService;
////import com.einfo.Project.Ecommerce.service.userService;
////
////import jakarta.validation.Valid;
////@CrossOrigin("http://localhost:3000/")
////@RestController
////public class Controller {
////	
////     @Autowired
////     userService uservice;
////     
////     @Autowired 
////      UserRepo repo;
////     
////     @Autowired 
////     ImageService service;
////     
////     @Autowired
////     ProductrRepo productrepo;
////     
////     @Autowired
////     Categoryservice cService;
////     
////
////     FeatureManager manager;
////     
////     public static final Feature DISCOUNT_APPLIED = new NamedFeature("DISCOUNT_APPLIED");
////     
//////     @PostMapping("/signup")
//////	public User saveuser(@RequestBody User user)
//////	{  
//////    	 return repo.save(user);
//////	}
////     @PostMapping("/signup")
////     public  ResponseEntity<User>saveuser(@RequestBody @Valid UserRequest  userRequest) throws UserAlaradyExiest {
////    
////    	 return  new ResponseEntity<>(uservice.saveuser(userRequest),HttpStatus.CREATED);
////     }
////     
////    @PostMapping("/login")
////	public ResponseEntity<User>getLogin(@RequestBody User user){
////    	User userdata =uservice.userlogin(user.getEmail(),user.getPassword());
////    	System.out.println(userdata);
////    	if(userdata==null) {
////    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
////    	}
////    	else {
////    		return ResponseEntity.ok().build();
////    	}
////    	
////    }
////    
////	@PostMapping("image/add")
////	public ResponseEntity<String> productImageupload(@RequestParam("file") MultipartFile file,
////		    @RequestParam("pname") String productName,
////		    @RequestParam("description") String Description,
////		    @RequestParam("price") int productPrice,
////		    @RequestParam("category") String category ) throws IOException {
////		String imagename = service.uploadImage(file);
////		  
////		   Product newproduct =productrepo.save(Product.builder().pname(productName)
////				   .description(Description)
////				   .price(productPrice)
////				   .category(category)
////				   .pimagename(imagename).build());
////		return ResponseEntity.status(HttpStatus.OK).body("prodct and image uploaded successfuly");
////			
////	}
////
////	@GetMapping("/{fileName}")
////	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
////		byte[] imageData=service.downloadImage(fileName);
////		return ResponseEntity.status(HttpStatus.OK)
////				.contentType(MediaType.valueOf("image/png"))
////				.body(imageData);
////
////	}
////	@GetMapping("/product")
////	public List<Product>getproduct(){
////		System.out.println(DISCOUNT_APPLIED);
////		 if (manager.isActive(DISCOUNT_APPLIED)) {
////	            return applyDiscount(uservice.getallproduct());
////	        } else {
////	        	return uservice.getallproduct();
////	        }   
////		
////	}
////	
////	@PostMapping("/addcategory")
////	   public  ResponseEntity<Category>categorysave(@RequestBody @Valid Categorydto categorydto){
////   	 return  new ResponseEntity<>(cService.savecategory(categorydto),HttpStatus.CREATED);
////    }
////    
////	@GetMapping("/category")
////	public ResponseEntity<List<Category>> getAllCategory() {
////	    List<Category> categories = cService.getcategory();
////	    return ResponseEntity.ok().body(categories);
////	}
////
////	 @GetMapping("/productDetails/{id}")
////	    public Product getProductById(@PathVariable int id) {
////	        return uservice.getProductById(id);
////	    }
////	 
////	 private List<Product> applyDiscount(List<Product> availableProducts) {
////	        List<Product> orderListAfterDiscount = new ArrayList<>();
////	        uservice.getallproduct().forEach(order -> {
////	            order.setPrice(order.getPrice() - (order.getPrice() * 5 / 100));
////	            orderListAfterDiscount.add(order);
////	        });
////	        return orderListAfterDiscount;
////	    }
////	 
////
////    
////	
////   
////}
//
//package com.einfo.Project.Ecommerce.Controller;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import org.togglz.core.Feature;
//import org.togglz.core.manager.FeatureManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import org.togglz.core.util.NamedFeature;
//
//import com.einfo.Project.Ecommerce.service.userService;
//
//import jakarta.validation.Valid;
//@CrossOrigin("http://localhost:3000/")
//@RestController
//public class Controller {
//	
//     @Autowired
//     userService uservice;
//     
//     @Autowired 
//      UserRepo repo;
//     
//     @Autowired 
//     ImageService service;
//     
//     @Autowired
//     ProductrRepo productrepo;
//     
//     @Autowired
//     Categoryservice cService;
//     
//     @Autowired
//     FeatureManager manager;
//     
//     public static final Feature DISCOUNT_APPLIED = new NamedFeature("DISCOUNT_APPLIED");
//     
////     @PostMapping("/signup")
////	public User saveuser(@RequestBody User user)
////	{  
////    	 return repo.save(user);
////	}
//     @PostMapping("/signup")
//     public  ResponseEntity<User>saveuser(@RequestBody @Valid UserRequest  userRequest) throws UserAlaradyExiest {
//    
//    	 return  new ResponseEntity<>(uservice.saveuser(userRequest),HttpStatus.CREATED);
//     }
//     
//    @PostMapping("/login")
//	public ResponseEntity<User>getLogin(@RequestBody User user){
//    	User userdata =uservice.userlogin(user.getEmail(),user.getPassword());
//    	System.out.println(userdata);
//    	if(userdata==null) {
//    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    	}
//    	else {
//    		return ResponseEntity.ok().build();
//    	}
//    	
//    }
//    
//	@PostMapping("image/add")
//	public ResponseEntity<String> productImageupload(@RequestParam("file") MultipartFile file,
//		    @RequestParam("pname") String productName,
//		    @RequestParam("description") String Description,
//		    @RequestParam("price") int productPrice,
//		    @RequestParam("category") String category ) throws IOException {
//		String imagename = service.uploadImage(file);
//		  
//		   Product newproduct =productrepo.save(Product.builder().pname(productName)
//				   .description(Description)
//				   .price(productPrice)
//				   .category(category)
//				   .pimagename(imagename).build());
//		return ResponseEntity.status(HttpStatus.OK).body("prodct and image uploaded successfuly");
//			
//	}
//	
//}
//
//
