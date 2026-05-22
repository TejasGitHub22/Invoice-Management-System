package mypackage.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Customer;
import mypackage.model.CustomerModel;
import mypackage.model.InvoiceDetails;
import mypackage.model.InvoiceModel;
import mypackage.model.InvoicePayment;
import mypackage.model.InvoiceProduct;
import mypackage.model.PaymentModel;
import mypackage.repository.ICustomerRepository;
import mypackage.repository.IInvoiceDetailsRepository;
import mypackage.repository.IInvoicePaymentRepository;
import mypackage.repository.IInvoiceProductRepository;

@Service
public class InvoiceService {

	@Autowired
	IInvoiceDetailsRepository invoicerepo;
	
	@Autowired
	IInvoiceProductRepository invoiceproductrepo;

	@Autowired
	IInvoicePaymentRepository paymentrepo;
	
	
	@Autowired 
	ICustomerRepository custrepo;
	
	public InvoiceDetails AddNewInvoice(InvoiceDetails d) {
		
		InvoiceDetails dt=new InvoiceDetails(0, d.getInvoice_date(), d.getTotal_amount(), null, null, d.getCustomer());
		InvoiceDetails detail=invoicerepo.save(dt);
		for(InvoiceProduct p : d.getInvoiceprdt()) {
			
			InvoiceProduct pr=new InvoiceProduct(0, p.getQuantity(), detail, p.getProduct());
			invoiceproductrepo.save(pr);
		}
		
		return detail;
		
		
	}
	
	public List<InvoiceModel> GetAllInvoices(){
		List<InvoiceModel> lst=new ArrayList<>();
//		List<InvoiceDetails> details=invoicerepo.findAll();
		for(InvoiceDetails d : invoicerepo.findAll()) {
			Customer c=custrepo.findById(d.getCustomer().getCustomer_id()).get();
			
			float total_amount=d.getTotal_amount();
			float paid_amount=0;
			float remaining_amount=0;
			
			for(InvoicePayment p : paymentrepo.findAll()) {
				if(p.getDetails().getInvoice_id()==d.getInvoice_id()) {
					paid_amount+=p.getPayment_amount();
				}
			}
			remaining_amount=total_amount-paid_amount;
			String status="";
			if(paid_amount==0) {
				status="Un Paid";
			}
			else if(paid_amount>0 && paid_amount<total_amount) {
				status="Partial Paid";
			}
			else {
				status="Paid";
			}
			
			InvoiceModel im=new InvoiceModel(d.getInvoice_id(), d.getInvoice_date(), c.getCustomer_id(), c.getCustomer_name(), c.getMobile_number(), c.getEmail_address(),total_amount, paid_amount,remaining_amount, status);
			lst.add(im);
		}
		return lst;
	}
	
	public InvoiceModel GetInvoiceByid(int id) {
		InvoiceDetails d=invoicerepo.findById(id).get();
		
		Customer c=custrepo.findById(d.getCustomer().getCustomer_id()).get();
		
		float total_amount=d.getTotal_amount();
		float paid_amount=0;
		float remaining_amount=0;
		
		for(InvoicePayment p : paymentrepo.findAll()) {
			if(p.getDetails().getInvoice_id()==d.getInvoice_id()) {
				paid_amount+=p.getPayment_amount();
			}
		}
		remaining_amount=total_amount-paid_amount;
		String status="";
		if(paid_amount==0) {
			status="Un Paid";
		}
		else if(paid_amount>0 && paid_amount<total_amount) {
			status="Partial Paid";
		}
		else {
			status="Paid";
		}
		
		InvoiceModel im=new InvoiceModel(d.getInvoice_id(), d.getInvoice_date(), c.getCustomer_id(), c.getCustomer_name(), c.getMobile_number(), c.getEmail_address(),total_amount, paid_amount,remaining_amount, status);
		
		return im;
		
		
	}
	
	public void AddPayment(InvoicePayment p) {
		
		InvoicePayment pr=new InvoicePayment(0, p.getPayment_date(), p.getPayment_amount(), p.getPayment_mode(), p.getPayment_description(), p.getDetails());
		paymentrepo.save(pr);
	}
	
	public List<PaymentModel> GetInvoiceWisePayments(int id){
		List<PaymentModel> lst=new ArrayList<>();
		List<InvoicePayment> payments=paymentrepo.findAll();
		for(InvoicePayment p: payments) {
			if(p.getDetails().getInvoice_id()==id) {
				 int invoice_id=p.getDetails().getInvoice_id();

				PaymentModel pay=new PaymentModel(invoice_id,p.getPayment_id(), p.getPayment_date(), p.getPayment_amount(), p.getPayment_mode(), p.getPayment_description());
				lst.add(pay);
			}
		}
		return lst;
	}
}


















