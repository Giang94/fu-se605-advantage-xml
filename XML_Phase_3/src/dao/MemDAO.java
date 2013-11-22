package dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import utils.StringUtilities;
import beans.Categories;
import beans.Category;
import beans.Database;
import beans.ObjectFactory;
import beans.Product;
import beans.Products;
import beans.Supplier;
import beans.Suppliers;
import beans.XProduct;

public class MemDAO extends VDAO {
	
	private static MemDAO _memdao;
	private static String extension = ".log";
	
	private Hashtable<Integer, Category> dictCategories;
	private Hashtable<Integer, Supplier> dictSuppliers;
	private Hashtable<Integer, Product> dictProducts;
	private int mpid = 0;
	private int mcid = 0;
	private int msid = 0;
	private String dbname = "";
	private boolean auto_commit = false;
	
	protected static MemDAO createNew(String dbname){
		MemDAO memdao = new MemDAO();
		memdao.dictCategories = new Hashtable<>();
		memdao.dictProducts = new Hashtable<>();
		memdao.dictSuppliers = new Hashtable<>();
		memdao.dbname = dbname;
		return memdao;
	}
	
	public static MemDAO create(String[] args) throws DAOException{
		if (args == null || args.length < 1) throw new DAOException("Not enoungh arguments, require database name.");
		String database_name = args[0];
		if (_memdao == null || _memdao.dbname != database_name){
			 _memdao = MemDAO.createNew(database_name);
			 try{
				 _memdao.fromFile(database_name + MemDAO.extension);
			 }
			 catch (Exception ex){
				 //Do Nothing
			 }
		}
		return _memdao;
	}

	//These functions show be implement in Product, Category, Supplier and XProduct class
	//However, not sure if it will affect marshal/unmarshal process or not
	//So, here is safer place.
	
	protected static void copy(Product p, Product targetp) {		
		targetp.setCategoryID(p.getCategoryID());
		targetp.setDiscontinued(p.getDiscontinued());
		targetp.setProductID(p.getProductID());
		targetp.setProductName(p.getProductName());
		targetp.setQuantityPerUnit(p.getQuantityPerUnit());
		targetp.setReorderLevel(p.getReorderLevel());
		targetp.setSupplierID(p.getProductID());
		targetp.setUnitPrice(p.getUnitPrice());
		targetp.setUnitsInStock(p.getUnitsInStock());
		targetp.setUnitsOnOrder(p.getUnitsOnOrder());			
	}
	
	protected static void copy(Category c, Category targetc) {		
		targetc.setCategoryID(c.getCategoryID());
		targetc.setCategoryName(c.getCategoryName());
		targetc.setDescription(c.getDescription());
		targetc.setFile(c.getFile());
		targetc.setPicture(c.getPicture());		
	}
	
	protected static void copy(Supplier s, Supplier targets) {		
		targets.setAddress(s.getAddress());
		targets.setCity(s.getCity());
		targets.setCompanyName(s.getCompanyName());
		targets.setContactName(s.getContactName());
		targets.setContactTitle(s.getContactTitle());
		targets.setCountry(s.getCountry());
		targets.setFax(s.getFax());
		targets.setHomePage(s.getHomePage());
		targets.setPhone(s.getPhone());
		targets.setPostalCode(s.getPostalCode());
		targets.setRegion(s.getRegion());
		targets.setSupplierID(s.getSupplierID());
	}
	
	protected static Product clone(Product p){
		Product np = new Product();
		copy(p, np);
		return np;
	}
	
	protected static Category clone(Category c){
		Category nc = new Category();
		copy(c, nc);
		return nc;
	}
	
	protected static Supplier clone(Supplier s){
		Supplier ns = new Supplier();
		copy(s, ns);
		return ns;
	}
	
	//--------------------------------------------------------------
	
	
	@Override
	public void addProduct(Product p) throws DAOException {
		this.mpid++;
		p.setProductID(BigInteger.valueOf(this.mpid));
		dictProducts.put(this.mpid, clone(p));
		if (this.auto_commit) this.commit();
	}

	@Override
	public void addCategory(Category c) throws DAOException {
		this.mcid++;
		c.setCategoryID(BigInteger.valueOf(this.mcid));
		dictCategories.put(this.mcid, clone(c));
		if (this.auto_commit) this.commit();
	}

	@Override
	public void addSupplier(Supplier s) throws DAOException {
		this.msid++;
		s.setSupplierID(BigInteger.valueOf(this.msid));
		dictSuppliers.put(this.msid, clone(s));	
		if (this.auto_commit) this.commit();
	}

	@Override
	public void updateProduct(Product p) throws DAOException {		
		//if (p.getProductID().intValue() > this.mpid) this.mpid = p.getProductID().intValue();
		//dictProducts.put(this.mpid, p);
		int pid = p.getProductID().intValue();
		Product op = dictProducts.get(pid);
		if (op != null) {
			copy(p, op);
			if (this.auto_commit) this.commit();
		}		
	}

	@Override
	public void updateCategory(Category c) throws DAOException {
		//if (c.getCategoryID().intValue() > this.mcid) this.mcid = c.getCategoryID().intValue();
		//dictCategories.put(this.mcid, c);
		int cid = c.getCategoryID().intValue();
		Category oc = dictCategories.get(cid);
		if (oc != null) {
			copy(c, oc);
			if (this.auto_commit) this.commit();
		}
	}

	@Override
	public void updateSupplier(Supplier s) throws DAOException {
		//if (s.getSupplierID().intValue() > this.msid) this.msid = s.getSupplierID().intValue();
		//dictSuppliers.put(this.msid, s);
		int sid = s.getSupplierID().intValue();
		Supplier os = dictSuppliers.get(sid);
		if (os != null) {
			copy(s, os);
			if (this.auto_commit) this.commit();
		}
	}

	@Override
	public void deleteProduct(int pid) throws DAOException {
		dictProducts.remove(pid);
		if (this.auto_commit) this.commit();
	}

	@Override
	public void deleteCategory(int cid) throws DAOException {
		dictCategories.remove(cid);
		if (this.auto_commit) this.commit();
		
	}

	@Override
	public void deleteSupplier(int sid) throws DAOException {
		dictSuppliers.remove(sid);		
		if (this.auto_commit) this.commit();
	}

	@Override
	public ArrayList<XProduct> getXProducts() {		
		ArrayList<XProduct> products = new ArrayList<>();
		Collection<Product> pb = dictProducts.values();
		for (Product p : pb) {
			XProduct xp = XProduct.create(p);
			Category c = dictCategories.get(xp.getCategoryID().intValue());
			if (c != null){
				xp.setCategoryName(c.getCategoryName());
			}
			Supplier s = dictSuppliers.get(xp.getSupplierID().intValue());
			if (s != null){
				xp.setSupplierName(s.getCompanyName());
			}
			products.add(xp);			
		}
		return products;
	}

	@Override
	public ArrayList<Product> getProducts() {		
		return new ArrayList<Product>(dictProducts.values());
	}
	
	@Override
	public ArrayList<Supplier> getSuppliers() {		
		return new ArrayList<Supplier>(dictSuppliers.values());
	}

	@Override
	public ArrayList<Category> getCategories() {		
		return new ArrayList<Category>(dictCategories.values());
	}
	
	@Override
	public XProduct getXProduct(int pid) {
		XProduct xp = XProduct.create(this.dictProducts.get(pid));
		Category c = dictCategories.get(xp.getCategoryID().intValue());
		if (c != null){
			xp.setCategoryName(c.getCategoryName());
		}
		Supplier s = dictSuppliers.get(xp.getSupplierID().intValue());
		if (s != null){
			xp.setSupplierName(s.getCompanyName());
		}
		return xp;
	};
	
	@Override
	public Product getProduct(int pid) {
		return this.dictProducts.get(pid);
	}
	
	@Override
	public Category getCategory(int cid) {
		return this.dictCategories.get(cid);
	}
	
	@Override
	public Supplier getSupplier(int sid) {
		return this.dictSuppliers.get(sid);
	}

	protected boolean containskw(XProduct xp, String kw){	
		if (StringUtilities.from(xp.getCategoryID()).indexOf(kw) >= 0) return true;
		if (xp.getCategoryName().indexOf(kw) >= 0) return true;
		if (StringUtilities.from(xp.getDiscontinued()).indexOf(kw) >= 0) return true;
		if (StringUtilities.from(xp.getProductID()).indexOf(kw) >= 0) return true;
		if (xp.getProductName().indexOf(kw) >= 0) return true;
		if (xp.getQuantityPerUnit().indexOf(kw) >= 0) return true;
		if (xp.getReorderLevel().toString().indexOf(kw) >= 0) return true;
		if (StringUtilities.from(xp.getSupplierID()).indexOf(kw) >= 0) return true;
		if (xp.getSupplierName().indexOf(kw) >= 0) return true;
		if (xp.getUnitPrice().toString().indexOf(kw) >= 0) return true;
		if (xp.getUnitsInStock().toString().indexOf(kw) >= 0) return true;
		if (xp.getUnitsOnOrder().toString().indexOf(kw) >= 0) return true;
		return false;
	}
	
	protected boolean containskw(Category c, String kw){
		if (StringUtilities.from(c.getCategoryID()).indexOf(kw) >= 0) return true;
		if (c.getCategoryName().indexOf(kw) >= 0) return true;
		if (c.getDescription().indexOf(kw) >= 0) return true;
		if (c.getFile().indexOf(kw) >= 0) return true;
		return false;
	}
	
	protected boolean containskw(Supplier s, String kw){
		if (StringUtilities.from(s.getSupplierID()).indexOf(kw) >= 0) return true;
		if (s.getCompanyName().indexOf(kw) >= 0) return true;
		if (s.getContactName().indexOf(kw) >= 0) return true;
		if (s.getContactTitle().indexOf(kw) >= 0) return true;
		if (s.getAddress().indexOf(kw) >= 0) return true;
		if (s.getCity().indexOf(kw) >= 0) return true;
		if (s.getRegion().indexOf(kw) >= 0) return true;
		if (s.getPostalCode().indexOf(kw) >= 0) return true;
		if (s.getCountry().indexOf(kw) >= 0) return true;
		if (s.getPhone().indexOf(kw) >= 0) return true;
		if (s.getFax().indexOf(kw) >= 0) return true;
		if (s.getHomePage().indexOf(kw) >= 0) return true;
		return false;
	}
	
	@Override
	public ArrayList<XProduct> searchXProducts(String kw) {
		if ("".equalsIgnoreCase(kw)) return this.getXProducts();
		ArrayList<XProduct> xproducts = getXProducts();
		ArrayList<XProduct> result = new ArrayList<>();
		for (XProduct xp : xproducts){
			if (containskw(xp, kw)) result.add(xp);
		}
		return result;
	}

	@Override
	public ArrayList<Supplier> searchSuppliers(String kw) {
		if ("".equalsIgnoreCase(kw)) return this.getSuppliers();
		Collection<Supplier> suppliers = dictSuppliers.values();
		ArrayList<Supplier> result = new ArrayList<>();
		for (Supplier s : suppliers) {
			if (containskw(s, kw)) result.add(s);
		}
		return result;
	}

	@Override
	public ArrayList<Category> searchCategories(String kw) {
		if ("".equalsIgnoreCase(kw)) return this.getCategories();
		Collection<Category> categories = dictCategories.values();
		ArrayList<Category> result = new ArrayList<>();
		for (Category c : categories){
			if (containskw(c, kw)) result.add(c);
		}
		return result;
	}
	
	@Override
	public void fromDatabase(Database db) throws DAOException {
		List<Product> ps = db.getProducts().getProduct();
		List<Category> cs = db.getCategories().getCategory();
		List<Supplier> ss = db.getSuppliers().getSupplier();
		
		this.dictCategories.clear();
		this.dictProducts.clear();
		this.dictSuppliers.clear();
		
		this.mcid = 0;
		this.mpid = 0;
		this.msid = 0;
		for (Supplier s : ss) {
			int sid = s.getSupplierID().intValue();
			if (sid > this.msid) this.msid = sid;
			this.dictSuppliers.put(sid, s);
		}		
		for (Category c : cs) {
			int cid = c.getCategoryID().intValue();
			if (cid > this.mcid) this.mcid = cid;
			this.dictCategories.put(cid, c);
		}		
		for (Product p : ps) {
			int pid = p.getProductID().intValue();
			if (pid > this.mpid) this.mpid = pid;
			this.dictProducts.put(pid, p);
		}		
		if (this.auto_commit) {
			this.commit();
		}
	}
	
	@Override
	public Database toDatabase() throws DAOException {
		ObjectFactory fac = new ObjectFactory();
		Database db = fac.createDatabase();
		db.setName(this.dbname);
		Categories cs = fac.createDatabaseCategories();
		Suppliers ss = fac.createDatabaseSuppliers();
		Products ps = fac.createDatabaseProducts();
		Collection<Category> ccs = dictCategories.values();
		for (Category c : ccs) {
			cs.addCategory(c);
		}
		Collection<Supplier> css = dictSuppliers.values();
		for (Supplier s : css) {
			ss.addSupplier(s);
		}
		Collection<Product> cps = dictProducts.values();
		for (Product p : cps) {
			ps.addProduct(p);
		}
		db.setCategories(cs);
		db.setSuppliers(ss);
		db.setProducts(ps);
		return db;
	}
	
	@Override
	public void setAutoCommit(boolean b) {		
		this.auto_commit = b;
	}

	@Override
	public void commit() throws DAOException {
		this.toFile(this.dbname + extension);		
	}		
}