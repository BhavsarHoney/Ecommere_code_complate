package com.einfo.Project.Ecommerce.service;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.einfo.Project.Ecommerce.Model.Image;
import com.einfo.Project.Ecommerce.repo.Imagerepo;
import com.einfo.Project.Ecommerce.utill.ImageUtils;
@Service
public class ImageService {
	 @Autowired
	    private Imagerepo repo;

	    public String uploadImage(MultipartFile file) throws IOException {

	        Image image = repo.save(Image.builder()
	                .name(file.getOriginalFilename())
	                .type(file.getContentType())
	                .imageData(ImageUtils.compressImage(file.getBytes())).build());
	        if (image != null) {
	            return file.getOriginalFilename();
	        }
	        return null;
	    }

	    public byte[] downloadImage(String fileName){
	        Optional<Image> dbImageData = repo.findByName(fileName);
	        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
	        return images;
	    }
}
