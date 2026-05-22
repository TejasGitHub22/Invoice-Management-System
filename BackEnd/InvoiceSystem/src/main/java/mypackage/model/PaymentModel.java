package mypackage.model;

import java.sql.Date;

public class PaymentModel {

	private int invoice_id;
	private int payment_id;
	private Date payment_date;
	private int payment_amount;
	private String payment_mode;
	private String payment_description;
	public PaymentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	public int getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(int payment_amount) {
		this.payment_amount = payment_amount;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getPayment_description() {
		return payment_description;
	}
	public void setPayment_description(String payment_description) {
		this.payment_description = payment_description;
	}
	public PaymentModel(int invoice_id, int payment_id, Date payment_date, int payment_amount, String payment_mode,
			String payment_description) {
		super();
		this.invoice_id = invoice_id;
		this.payment_id = payment_id;
		this.payment_date = payment_date;
		this.payment_amount = payment_amount;
		this.payment_mode = payment_mode;
		this.payment_description = payment_description;
	}
}
