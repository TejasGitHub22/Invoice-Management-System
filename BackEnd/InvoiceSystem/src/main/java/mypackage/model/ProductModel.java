package mypackage.model;

public class ProductModel {
	private int product_id;
	private String product_name;
	private int product_rate;
	private int product_gst;
	private int product_stock;
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
	public ProductModel(int product_id, String product_name, int product_rate, int product_gst, int product_stock) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_rate = product_rate;
		this.product_gst = product_gst;
		this.product_stock = product_stock;
	}
	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
