package com.einfo.Project.Ecommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfo.Project.Ecommerce.Model.Product;

public interface ProductrRepo extends JpaRepository<Product,Integer>{
 
	List<Product> findById(int id);
}
