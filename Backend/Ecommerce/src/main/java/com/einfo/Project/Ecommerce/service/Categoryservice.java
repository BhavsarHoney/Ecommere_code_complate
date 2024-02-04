package com.einfo.Project.Ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einfo.Project.Ecommerce.Model.Category;
import com.einfo.Project.Ecommerce.dto.Categorydto;
import com.einfo.Project.Ecommerce.dto.UserRequest;
import com.einfo.Project.Ecommerce.repo.Categoryrepo;

@Service
public class Categoryservice {
	@Autowired
	Categoryrepo repo;

	public Category savecategory( Categorydto  categorydto ) {
	      Category category = Category
	    		  .build(0,categorydto.getCategory(),
	    				  categorydto.getCategotydescription()
	    				 );
	      return repo.save(category);
	    }
	public List<Category>getcategory(){
		return  repo.findAll();
	}
	     
}
