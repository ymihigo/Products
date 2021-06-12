package com.Mihigo.product.viewController;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.Mihigo.product.model.Product;
import com.Mihigo.product.services.ProductServices;

@Controller
public class ViewController {
	
	@Autowired
	private ProductServices serv;
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@PostMapping("/save")
	public RedirectView  saveProduct(@RequestParam("proname") String proname,@RequestParam("prodescription") String desc,
			@RequestParam("proprice") int price,
			@RequestParam("filez") MultipartFile file
			) {
		try {
			this.serv.newProduct(
					file, 
					proname, desc, price);
			return new RedirectView("/AllProducts",true);
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@RequestMapping("/AllProducts")
	public String allProducts(Model model) {
		
		List<Product> pro=new ArrayList<>();
		
		pro=this.serv.findAll();
		
		model.addAttribute("products",pro);
		
		return "/AllProduct";
	}
}
