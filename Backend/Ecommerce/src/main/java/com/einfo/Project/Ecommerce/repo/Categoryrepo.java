package com.einfo.Project.Ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfo.Project.Ecommerce.Model.Category;
import com.einfo.Project.Ecommerce.Model.Image;

public interface Categoryrepo extends JpaRepository<Category,Integer>{

}
