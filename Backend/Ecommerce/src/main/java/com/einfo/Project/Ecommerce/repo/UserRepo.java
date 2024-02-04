package com.einfo.Project.Ecommerce.repo;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.einfo.Project.Ecommerce.Model.Product;
import com.einfo.Project.Ecommerce.Model.User;

public interface UserRepo extends JpaRepository<User,Integer> {
      //List<User> findByEmail(String email);
      
       Optional<User>findByEmail(String email);
       
       @Query("SELECT COUNT(DISTINCT email) FROM User")
       Long countEmail();
   	
      
}
