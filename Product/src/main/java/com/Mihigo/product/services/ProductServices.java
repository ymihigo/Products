/**
 * 
 */
package com.Mihigo.product.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Mihigo.product.model.Product;


/**
 * @author Captain
 *
 */
public interface ProductServices {
	
	void newProduct(MultipartFile file,String name,String description,int price);
	List<Product> findAll();
	
}
