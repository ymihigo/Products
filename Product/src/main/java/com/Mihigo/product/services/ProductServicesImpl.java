package com.Mihigo.product.services;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Mihigo.product.model.Product;
import com.Mihigo.product.repository.ProductRepository;

@Service
public class ProductServicesImpl implements ProductServices {

	@Autowired
	private ProductRepository repo;
	
	@Override
	public void newProduct(MultipartFile file, String name, String description, int price) {
		
		try {
		Product p=new Product();
		
		p.setName(name);
		p.setDescription(description);
		p.setPrice(price);
		
		String filename=StringUtils.cleanPath(file.getOriginalFilename());
		if(filename.contains("..")) {
			System.out.println("invalid file");
		}
		
		p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		
		this.repo.save(p);
		}catch(Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		
	}
	

	@Override
	public List<Product> findAll() {
		
		return this.repo.findAll();
	}


}
