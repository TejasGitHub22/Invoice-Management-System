package mypackage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblinvoiceproduct")
public class InvoiceProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invoice_product_id;
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name="invoice_id")
	private InvoiceDetails details;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name="product_id")
	private Product product;

	
	public int getInvoice_product_id() {
		return invoice_product_id;
	}

	public void setInvoice_product_id(int invoice_product_id) {
		this.invoice_product_id = invoice_product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public InvoiceDetails getDetails() {
		return details;
	}

	public void setDetails(InvoiceDetails details) {
		this.details = details;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	

	public InvoiceProduct(int invoice_product_id, int quantity, InvoiceDetails details, Product product) {
		super();
		this.invoice_product_id = invoice_product_id;
		this.quantity = quantity;
		this.details = details;
		this.product = product;
	}

	public InvoiceProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
