package com.einfo.Project.Ecommerce.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private  String pname;
   private String description;
   private long price;
   private String pimagename;
   private String category;
@Override
public String toString() {
	return "{'id':'" + id + "', 'pname':'" + pname + "', 'description':'" + description + "', 'price':'" + price
			+ "', 'pimagename':'" + pimagename + "', 'category':'" + category + "'}";
}
   
   
   
   
}
