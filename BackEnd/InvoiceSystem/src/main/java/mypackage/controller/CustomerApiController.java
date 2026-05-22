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
import mypackage.model.CustomerModel;
import mypackage.services.CustomerServices;
import mypackage.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = "*")

public class CustomerApiController {

		@Autowired
		CustomerServices custservice;
		
		@PostMapping("api/customer")
		public String AddCustomer(@RequestBody Customer c) {
			custservice.AddCustomer(c);
			return "Customer Added Successfully";
		}
		
		@GetMapping("api/customer")
		public List<CustomerModel>GetAll(){
			return custservice.GetAllCustomer();
		}
		
		@GetMapping("api/customer/{id}")
		public CustomerModel GetById(@PathVariable("id") int customer_id) {
			return custservice.GetCustomer(customer_id);
		}
		
		@PutMapping("api/customer/{id}")
		public String UpdateCustomer(@RequestBody  Customer c) {
			custservice.UpdateCustomer(c);
			return "Customer Updated Successfully";
		}
		
		@DeleteMapping("api/customer/{id}")
		public String DeleteCustomer(@PathVariable("id") int customer_id) {
			custservice.DeleteCustomer(customer_id);
			 return "Customer Deleted Successfully";
		}
}
