package com.einfo.Project.Ecommerce.repotest;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.einfo.Project.Ecommerce.Model.Category;
import com.einfo.Project.Ecommerce.repo.Categoryrepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CategoryrepoTest {
	
     @Autowired
	 private Categoryrepo crepo;
     
	 @Test
	public void CategotyLISTSave() {
		
		Category category= Category.builder()
				.category("watch")
				.categotydescription("this is nice watch")
				.build();
		
	    Category c= crepo.save(category);	
	     Assertions.assertThat(c).isNotNull();
	     Assertions.assertThat(c.getCid()).isNotNull();
	}
	 @Test
	 public void Categorygetalllist() {
		 Category category= Category.builder()
					.category("watch")
					.categotydescription("this is nice watch")
					.build();
		 Category c= Category.builder()
					.category("bag")
					.categotydescription("this is nice beg")
					.build();
		 
		 Category c1= crepo.save(category);
		 Category c2= crepo.save(c);
		 
		     List<Category>caltegorylist= crepo.findAll();
		     
		     Assertions.assertThat(caltegorylist).isNotNull();
//		     Assertions.assertThat(caltegorylist.size()).isEqualTo(2);
		     
	 }
	  @Test
	 public void CategoryById() {
			Category category= Category.builder()
					.category("watch")
					.categotydescription("this is nice watch")
					.build();
			
		    Category c= crepo.save(category);	
		    
		      Category categorybyid=crepo.findById(category.getCid()).get();
		      Assertions.assertThat(categorybyid).isNotNull();
		 
	 }
}
