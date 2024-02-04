package com.einfo.Project.Ecommerce.Service;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.einfo.Project.Ecommerce.Model.Category;
import com.einfo.Project.Ecommerce.Model.User;
import com.einfo.Project.Ecommerce.dto.Categorydto;
import com.einfo.Project.Ecommerce.repo.Categoryrepo;
import com.einfo.Project.Ecommerce.service.Categoryservice;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {
	
	@Mock
	Categoryrepo  categoryrepo;
	
	@InjectMocks
	Categoryservice  categoryservice;
	
	@Test
	public void  Savecategorytests() {
		
		Category category= Category.builder() 
				.category("shoes")
				.categotydescription("this is nice shoes")
				.build();
		Categorydto Categorydto=new Categorydto("shoes","thie is nice shoes") ;
		BDDMockito.given(categoryrepo.save(BDDMockito.any(Category.class))).willReturn(category);
		
		Category saveCategoty=categoryservice.savecategory(Categorydto);
		
		Assertions.assertThat(saveCategoty).isNotNull();
	}
	
	@Test
	public void  ListofCategoryTest() {
		
		Category category= Category.builder() 
				.category("shoes")
				.categotydescription("this is nice shoes")
				.build();
		Category category1= Category.builder() 
				.category("watch")
				.categotydescription("this is nice watch")
				.build();
		
//		Categorydto Categorydto=new Categorydto("shoes","thie is nice shoes") ;
//		BDDMockito.given(categoryrepo.save(BDDMockito.any(Category.class))).willReturn(category);
//		
		BDDMockito.given(categoryrepo.findAll()).willReturn(List.of(category,category1));
		 
		
		 List<Category>categorylist=categoryservice.getcategory();
		 
		 Assertions.assertThat(categorylist).isNotNull();
		 

		
	}
	
	@Test
	public void  ListofCategoryEmPTYLISTTest() {
		
		Category category= Category.builder() 
				.category("shoes")
				.categotydescription("this is nice shoes")
				.build();
		Category category1= Category.builder() 
				.category("watch")
				.categotydescription("this is nice watch")
				.build();
		
//		Categorydto Categorydto=new Categorydto("shoes","thie is nice shoes") ;
//		BDDMockito.given(categoryrepo.save(BDDMockito.any(Category.class))).willReturn(category);
//	
		BDDMockito.given(categoryrepo.findAll()).willReturn(Collections.emptyList());
		 
		
		 List<Category>categorylist=categoryservice.getcategory();
		 
		 Assertions.assertThat(categorylist).isEmpty();
		 

		
	}
	
	
}
