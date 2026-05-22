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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tblcustomer")
public class Customer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	private String customer_name;
	private String gender;
	private Date birth_date;
	private String email_address;
	private String mobile_number;
	private String city;
	
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties("customer")
	private Set<InvoiceDetails> details;
	
	public Set<InvoiceDetails> getDetails() {
		return details;
	}
	public void setDetails(Set<InvoiceDetails> details) {
		this.details = details;
	}
	
	public Customer(int customer_id, String customer_name, String gender, Date birth_date, String email_address,
			String mobile_number, String city, Set<InvoiceDetails> details) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.gender = gender;
		this.birth_date = birth_date;
		this.email_address = email_address;
		this.mobile_number = mobile_number;
		this.city = city;
		this.details = details;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
