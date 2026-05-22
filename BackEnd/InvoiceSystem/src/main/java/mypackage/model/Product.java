package mypackage.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tblproduct")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	private String product_name;
	private int product_rate;
	private int product_gst;
	private int product_stock;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties("product")
	
	private Set<InvoiceProduct> invoiceprdt;
	
	
	
	public Set<InvoiceProduct> getInvoiceprdt() {
		return invoiceprdt;
	}
	public void setInvoiceprdt(Set<InvoiceProduct> invoiceprdt) {
		this.invoiceprdt = invoiceprdt;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_rate() {
		return product_rate;
	}
	public void setProduct_rate(int product_rate) {
		this.product_rate = product_rate;
	}
	public int getProduct_gst() {
		return product_gst;
	}
	public void setProduct_gst(int product_gst) {
		this.product_gst = product_gst;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	
	public Product(int product_id, String product_name, int product_rate, int product_gst, int product_stock,
			Set<InvoiceProduct> invoiceprdt) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_rate = product_rate;
		this.product_gst = product_gst;
		this.product_stock = product_stock;
		this.invoiceprdt = invoiceprdt;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
