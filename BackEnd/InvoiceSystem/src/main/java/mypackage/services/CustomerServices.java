package mypackage.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Customer;
import mypackage.model.CustomerModel;
import mypackage.repository.ICustomerRepository;

@Service
public class CustomerServices {
	
	@Autowired
	ICustomerRepository custrepo;
	
	public void AddCustomer(Customer c) {
		custrepo.save(c);
	}
	
	public void UpdateCustomer(Customer c) {
		custrepo.save(c);
	}
	
//	public List<Customer>GetAllCustomer(){
//		List<Customer> lst=custrepo.findAll();
//		return lst;
//	}
	
	public List<CustomerModel>GetAllCustomer(){
		List<CustomerModel> lst=new ArrayList<>();
		for(Customer c : custrepo.findAll()) {
			CustomerModel cm=new CustomerModel(c.getCustomer_id(), c.getCustomer_name(), c.getGender(), c.getBirth_date(), c.getEmail_address(), c.getMobile_number(), c.getCity());
			lst.add(cm);
		}
		return lst;
	}
	
	public CustomerModel GetCustomer(int customer_id) {
		Customer c= custrepo.findById(customer_id).get();
		CustomerModel cm=new CustomerModel(c.getCustomer_id(), c.getCustomer_name(), c.getGender(), c.getBirth_date(), c.getEmail_address(), c.getMobile_number(), c.getCity());
		
		return cm;
	}
	
	public void DeleteCustomer(int customer_id) {
		Customer c= custrepo.findById(customer_id).get();
		custrepo.delete(c);
	}
}
