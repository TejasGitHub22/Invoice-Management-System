package mypackage.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Product;
import mypackage.model.ProductModel;
import mypackage.repository.ProductRepository;



@Service
public class ProductServices {
	
	@Autowired
	ProductRepository productrepo;
	
	public Product AddProduct(Product p) {
		Product pr=productrepo.save(p);
		return p;
		}
	
//	public List<Product> GetAllProduct(){
//		List<Product> lst=productrepo.findAll();
//		return lst;
//	}
//	
	public List<ProductModel> GetAllProduct(){
		List<ProductModel> lst=new ArrayList<>();
		for(Product p: productrepo.findAll())
		{
			ProductModel pm= new  ProductModel(p.getProduct_id(), p.getProduct_name(), p.getProduct_rate(), p.getProduct_gst(),p.getProduct_stock());
			lst.add(pm);
		}
		return lst;
	}
	
	public ProductModel GetProduct(int id){
		Product p=productrepo.findById(id).get();
		ProductModel pm= new ProductModel(p.getProduct_id(), p.getProduct_name(), p.getProduct_rate(), p.getProduct_gst(),p.getProduct_stock());
		return pm;
	}
	
	public Product UpdateProduct(Product p) {
		Product pr=productrepo.save(p);
		return pr;
	}
	
	public void DeleteProduct(int id) {
		Product p=productrepo.findById(id).get();
		productrepo.delete(p);
	}
}
