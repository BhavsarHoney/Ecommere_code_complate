package com.einfo.Project.Ecommerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfo.Project.Ecommerce.Model.Image;

public interface Imagerepo  extends JpaRepository<Image,Long> {
	Optional <Image>findByName(String fileName);
}
