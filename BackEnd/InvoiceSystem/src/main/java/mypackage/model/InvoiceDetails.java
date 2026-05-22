package mypackage.model;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tblinvoicedetails")
public class InvoiceDetails {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int invoice_id;
		private Date invoice_date;
		private int total_amount;
		
		@OneToMany(mappedBy = "details",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
		@JsonIgnoreProperties("details")
		private Set<InvoiceProduct> invoiceprdt;
		
		@OneToMany(mappedBy = "details",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
		@JsonIgnoreProperties("details")
		private Set<InvoicePayment> payment;
		
		@ManyToOne(fetch = FetchType.LAZY,optional=false)
		@JoinColumn(name="customer_id")
		private Customer customer;

		public int getInvoice_id() {
			return invoice_id;
		}

		public void setInvoice_id(int invoice_id) {
			this.invoice_id = invoice_id;
		}

		public Date getInvoice_date() {
			return invoice_date;
		}

		public void setInvoice_date(Date invoice_date) {
			this.invoice_date = invoice_date;
		}

		public int getTotal_amount() {
			return total_amount;
		}

		public void setTotal_amount(int total_amount) {
			this.total_amount = total_amount;
		}

		public Set<InvoiceProduct> getInvoiceprdt() {
			return invoiceprdt;
		}

		public void setInvoiceprdt(Set<InvoiceProduct> invoiceprdt) {
			this.invoiceprdt = invoiceprdt;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		

		public InvoiceDetails(int invoice_id, Date invoice_date, int total_amount, Set<InvoiceProduct> invoiceprdt,
				Set<InvoicePayment> payment, Customer customer) {
			super();
			this.invoice_id = invoice_id;
			this.invoice_date = invoice_date;
			this.total_amount = total_amount;
			this.invoiceprdt = invoiceprdt;
			this.payment = payment;
			this.customer = customer;
		}

		public Set<InvoicePayment> getPayment() {
			return payment;
		}

		public void setPayment(Set<InvoicePayment> payment) {
			this.payment = payment;
		}

		public InvoiceDetails() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
