package mypackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mypackage.model.InvoiceDetails;
import mypackage.model.InvoiceModel;
import mypackage.model.InvoicePayment;
import mypackage.model.InvoiceProduct;
import mypackage.model.PaymentModel;
import mypackage.services.InvoiceService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = "*")

public class InvoiceApiController {

	
	@Autowired
	InvoiceService iservice;
	
	
	@GetMapping("api/allinvoices")
	public List<InvoiceModel>GetAll(){
		return iservice.GetAllInvoices();
	}
	@PostMapping("api/newinvoice")
	public InvoiceDetails AddInvoice(@RequestBody InvoiceDetails d ) {
		System.out.println(d.getCustomer().getCustomer_id()+" "+d.getTotal_amount()+" "+d.getInvoice_date());
		for(InvoiceProduct p :d.getInvoiceprdt()) {
			System.out.println(p.getProduct().getProduct_id()+" "+p.getQuantity());
		}
		
		
		// return d;
		 	return iservice.AddNewInvoice(d);
	}
	
	@GetMapping("api/invoice/{id}")
	public InvoiceModel GetInvoice(@PathVariable("id") int id) {
		return iservice.GetInvoiceByid(id);
	}
	
	@PostMapping("api/addpayment")
	public String AddPayment(@RequestBody InvoicePayment p) {
		System.out.println(p.getDetails().getInvoice_id()+" "+p.getPayment_date()+" "+p.getPayment_amount());
		iservice.AddPayment(p);
		
		return "Payment Added Successfully";
	}
	
	@GetMapping("api/getinvoicepayments/{id}")
	public  List<PaymentModel> GetAllInvoices(@PathVariable("id") int id){
		List<PaymentModel> lst= iservice.GetInvoiceWisePayments(id);
		return lst;
	}
}
