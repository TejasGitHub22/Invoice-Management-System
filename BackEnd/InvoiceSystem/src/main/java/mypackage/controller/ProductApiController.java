package mypackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mypackage.model.Customer;
import mypackage.model.Product;
import mypackage.model.ProductModel;
import mypackage.services.ProductServices;
import mypackage.repository.ProductRepository;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = "*")
public class ProductApiController {
	
	@Autowired
	ProductServices pservice;
	@PostMapping("api/product")
	public String AddProduct(@RequestBody Product p) {
		pservice.AddProduct(p);
		return "Product Added Successfully";
	}
	
	@GetMapping("api/product")
	public List<ProductModel>GetAll(){
		return pservice.GetAllProduct();
	}
	
	@GetMapping("api/product/{id}")
	public ProductModel GetById(@PathVariable("id") int product_id) {
		return pservice.GetProduct(product_id);
	}
	
	@PutMapping("api/product/{id}")
	public String UpdateProduct(@RequestBody  Product p) {
		pservice.UpdateProduct(p);
		return "Product Updated Successfully";
	}
	
	@DeleteMapping("api/product/{id}")
	public String DeleteProduct(@PathVariable("id") int product_id) {
		pservice.DeleteProduct(product_id);
		 return "Product Deleted Successfully";
	}
}

