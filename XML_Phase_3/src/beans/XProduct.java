package beans;

public class XProduct extends Product {
	private String categoryName = "";
	private String supplierName = "";
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
	public String getCategoryName(){
		return this.categoryName;
	}
	public void setSupplierName(String supplierName){
		this.supplierName = supplierName;
	}
	public String getSupplierName(){
		return this.supplierName;
	}
	public static XProduct create(Product p){
		XProduct rxp = new XProduct();
		rxp.load(p);
		return rxp;
	}
	public void load(Product p){
		this.setCategoryID(p.getCategoryID());
		this.setDiscontinued(p.getDiscontinued());
		this.setProductID(p.getProductID());
		this.setProductName(p.getProductName());
		this.setQuantityPerUnit(p.getQuantityPerUnit());
		this.setReorderLevel(p.getReorderLevel());
		this.setSupplierID(p.getSupplierID());
		this.setUnitPrice(p.getUnitPrice());
		this.setUnitsInStock(p.getUnitsInStock());
		this.setUnitsOnOrder(p.getUnitsOnOrder());
	}
}
