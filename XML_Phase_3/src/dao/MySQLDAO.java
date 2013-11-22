package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import beans.Categories;
import beans.Category;
import beans.Database;
import beans.ObjectFactory;
import beans.Product;
import beans.Products;
import beans.Supplier;
import beans.Suppliers;
import beans.XProduct;

public class MySQLDAO extends VDAO{
	
	private static MySQLDAO _dao;
	private static String _cn = "jdbc:mysql://localhost:3306/xml?user=root&password=mpwd";
	private static String SQL_ADD_PRODUCT = 		"INSERT INTO products(ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued) " + 
													"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String SQL_ADD_CATEGORY = 		"INSERT INTO categories(CategoryName, Description, Picture, File) " + 
													"VALUES (?, ?, ?, ?)";
	private static String SQL_ADD_SUPPLIER = 		"INSERT INTO suppliers(CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage) " + 
													"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String SQL_UPDATE_PRODUCT = 		"UPDATE products SET ProductName=?, SupplierID=?, CategoryID=?, QuantityPerUnit=?, UnitPrice=?, UnitsInStock=?, UnitsOnOrder=?, ReorderLevel=?, Discontinued=? " + 
													"WHERE ProductID=?";
	private static String SQL_UPDATE_CATEGORY = 	"UPDATE categories SET CategoryName=?, Description=?, Picture=?, File=? " +
													"WHERE CategoryID=?";
	private static String SQL_UPDATE_SUPPLIER = 	"UPDATE suppliers SET CompanyName=?, ContactName=?, ContactTitle=?, Address=?, City=?, Region=?, PostalCode=?, Country=?, Phone=?, Fax=?, HomePage=? " +
													"WHERE SupplierID=?";
	private static String SQL_DELETE_PRODUCT = 		"DELETE FROM products WHERE ProductID=?";
	private static String SQL_DELETE_CATEGORY = 	"DELETE FROM categories WHERE CategoryID=?";
	private static String SQL_DELETE_SUPPLIER = 	"DELETE FROM suppliers WHERE SupplierID=?";
	private static String SQL_CLEAR_PRODUCT =		"DELETE FROM products";
	private static String SQL_CLEAR_SUPPLIER = 		"DELETE FROM suppliers";
	private static String SQL_CLEAR_CATEGORY =		"DELETE FROM categories";
	private static String SQL_SELECT_PRODUCT =		"SELECT * FROM products";
	private static String SQL_SELECT_XPRODUCT = 	"SELECT p.*, c.CategoryName, s.CompanyName FROM products p " +
													"JOIN categories c ON p.CategoryID = c.CategoryID " +
													"JOIN suppliers s ON p.SupplierID = s.SupplierID";
	private static String SQL_IDCON_PRODUCT =		"WHERE ProductID = ?";
	private static String SQL_KWCON_XPRODUCT =		"WHERE ProductID LIKE ? OR " + 
													"ProductName LIKE ? OR " +
													"p.SupplierID LIKE ? OR " +
													"p.CategoryID LIKE ? OR " +
													"QuantityPerUnit LIKE ? OR " +
													"UnitPrice LIKE ? OR " +
													"UnitsInStock LIKE ? OR " +
													"UnitsOnOrder LIKE ? OR " +
													"ReorderLevel LIKE ? OR " +
													"Discontinued LIKE ? OR " +
													"c.CategoryName LIKE ? OR " +
													"s.CompanyName LIKE ?";	
	private static String SQL_SELECT_CATEGORY = 	"SELECT * FROM categories";
	private static String SQL_IDCON_CATEGORY =		"WHERE CategoryID = ?";
	private static String SQL_KWCON_CATEGORY =		"WHERE CategoryID LIKE ? OR " +
													"CategoryName LIKE ? OR " +
													"Description LIKE ? OR " +
													"File LIKE ?";
	private static String SQL_SELECT_SUPPLIER = 	"SELECT * FROM suppliers";
	private static String SQL_IDCON_SUPPLIER =		"WHERE SupplierID = ?";
	private static String SQL_KWCON_SUPPLIER = 		"WHERE SupplierID LIKE ? OR " +
													"CompanyName LIKE ? OR " +
													"ContactName LIKE ? OR " +
													"ContactTitle LIKE ? OR " +
													"Address LIKE ? OR " +
													"City LIKE ? OR " +
													"Region LIKE ? OR " +
													"PostalCode LIKE ? OR " +
													"Country LIKE ? OR " +
													"Phone LIKE ? OR " +
													"Fax LIKE ? OR " +
													"HomePage LIKE ?";
	
	private String dbname = "Northwind";
	private Connection con;
	
	protected static MySQLDAO createNew(String connection_string) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		MySQLDAO sqldao = new MySQLDAO(); 
		sqldao.con = DriverManager.getConnection(connection_string);
		return sqldao;  
	}
	
	public static MySQLDAO create(String[] args) throws DAOException{
		if (args == null || args.length < 1) throw new DAOException("Not enoungh arguments, require connection string");
		String cn = args[0];
		if (_dao == null || !_cn.equalsIgnoreCase(cn)) {
			try {
				_dao = MySQLDAO.createNew(cn);
			} catch (ClassNotFoundException | SQLException e) {				
				throw new DAOException("Cannot create DAO instance. Class not found.");
			}			
		}
		return _dao;
	}
	
	@Override
	public void addProduct(Product p) throws DAOException {		
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_ADD_PRODUCT);
			ps.setString(1, p.getProductName());
			ps.setInt(2, p.getSupplierID().intValue());
			ps.setInt(3, p.getCategoryID().intValue());
			ps.setString(4, p.getQuantityPerUnit());
			ps.setDouble(5, p.getUnitPrice().doubleValue());
			ps.setInt(6, p.getUnitsInStock().intValue());
			ps.setInt(7, p.getUnitsOnOrder().intValue());
			ps.setInt(8, p.getReorderLevel().intValue());
			ps.setInt(9, p.getDiscontinued().intValue());
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage()); 
		}
	}	
	
	@Override
	public void addCategory(Category c) throws DAOException {		
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_ADD_CATEGORY);
			ps.setString(1, c.getCategoryName());
			ps.setString(2, c.getDescription());
			ps.setString(3, c.getPicture());
			ps.setString(4, c.getFile());
			ps.executeUpdate();
		} 
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public void addSupplier(Supplier s) throws DAOException {		
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_ADD_SUPPLIER);
			ps.setString(1, s.getCompanyName());
			ps.setString(2, s.getContactName());
			ps.setString(3, s.getContactTitle());
			ps.setString(4, s.getAddress());
			ps.setString(5, s.getCity());
			ps.setString(6, s.getRegion());
			ps.setString(7, s.getPostalCode());
			ps.setString(8, s.getCountry());
			ps.setString(9, s.getPhone());
			ps.setString(10, s.getFax());
			ps.setString(11, s.getHomePage());
			ps.executeUpdate();
		} 
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		} 
	}
	
	@Override
	public void updateProduct(Product p) throws DAOException {
		try{
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_UPDATE_PRODUCT);
			ps.setString(1, p.getProductName());
			ps.setInt(2, p.getSupplierID().intValue());
			ps.setInt(3, p.getCategoryID().intValue());
			ps.setString(4, p.getQuantityPerUnit());
			ps.setDouble(5, p.getUnitPrice().doubleValue());
			ps.setInt(6, p.getUnitsInStock().intValue());
			ps.setInt(7, p.getUnitsOnOrder().intValue());
			ps.setInt(8, p.getReorderLevel().intValue());
			ps.setInt(9, p.getDiscontinued().intValue());
			ps.setInt(10, p.getProductID().intValue());
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public void updateCategory(Category c) throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_UPDATE_CATEGORY);
			ps.setString(1, c.getCategoryName());
			ps.setString(2, c.getDescription());
			ps.setString(3, c.getPicture());
			ps.setString(4, c.getFile());
			ps.setInt(5, c.getCategoryID().intValue());
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public void updateSupplier(Supplier s) throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_UPDATE_SUPPLIER);
			ps.setString(1, s.getCompanyName());
			ps.setString(2, s.getContactName());
			ps.setString(3, s.getContactTitle());
			ps.setString(4, s.getAddress());
			ps.setString(5, s.getCity());
			ps.setString(6, s.getRegion());
			ps.setString(7, s.getPostalCode());
			ps.setString(8, s.getCountry());
			ps.setString(9, s.getPhone());
			ps.setString(10, s.getFax());
			ps.setString(11, s.getHomePage());
			ps.setInt(12, s.getSupplierID().intValue());
			ps.executeUpdate();
		} 
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public void deleteProduct(int pid) throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_DELETE_PRODUCT);
			ps.setInt(1, pid);
			ps.executeUpdate();
		} 
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage()); 
		}
	}
	
	@Override
	public void deleteCategory(int cid) throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_DELETE_CATEGORY);
			ps.setInt(1, cid);
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public void deleteSupplier(int sid) throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_DELETE_SUPPLIER);
			ps.setInt(1, sid);
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	} 
	
	static protected ArrayList<Product> getProducts(ResultSet rs) throws SQLException{
		ArrayList<Product> result = new ArrayList<>();
		while (rs.next()){
			Product pro = new Product();
			pro.setProductID(BigInteger.valueOf(Long.parseLong(rs.getString(1))));
			pro.setProductName(rs.getString(2));
			pro.setSupplierID(BigInteger.valueOf(Long.parseLong(rs.getString(3))));
			pro.setCategoryID(BigInteger.valueOf(Long.parseLong(rs.getString(4))));
			pro.setQuantityPerUnit(rs.getString(5));
			pro.setUnitPrice(BigDecimal.valueOf(Double.parseDouble(rs.getString(6))));
			pro.setUnitsInStock(Integer.parseInt(rs.getString(7)));
			pro.setUnitsOnOrder(Integer.parseInt(rs.getString(8)));
			pro.setReorderLevel(Integer.parseInt(rs.getString(9)));
			pro.setDiscontinued(BigInteger.valueOf(rs.getInt(10)));
			result.add(pro);
		}
		return result;
	}
	
	static protected ArrayList<XProduct> getXProducts(ResultSet rs) throws SQLException {
		ArrayList<XProduct> result = new ArrayList<>();
		while (rs.next()){
			XProduct pro = new XProduct();
			pro.setProductID(BigInteger.valueOf(Long.parseLong(rs.getString(1))));
			pro.setProductName(rs.getString(2));
			pro.setSupplierID(BigInteger.valueOf(Long.parseLong(rs.getString(3))));
			pro.setCategoryID(BigInteger.valueOf(Long.parseLong(rs.getString(4))));
			pro.setQuantityPerUnit(rs.getString(5));
			pro.setUnitPrice(BigDecimal.valueOf(Double.parseDouble(rs.getString(6))));
			pro.setUnitsInStock(Integer.parseInt(rs.getString(7)));
			pro.setUnitsOnOrder(Integer.parseInt(rs.getString(8)));
			pro.setReorderLevel(Integer.parseInt(rs.getString(9)));
			pro.setDiscontinued(BigInteger.valueOf(rs.getInt(10)));
			pro.setCategoryName(rs.getString(11));
			pro.setSupplierName(rs.getString(12));
			result.add(pro);
		}
		return result;
	}
	
	static protected ArrayList<Category> getCategories(ResultSet rs) throws SQLException {
		ArrayList<Category> result = new ArrayList<>();
		while (rs.next()){
			Category cat = new Category();
			cat.setCategoryID(BigInteger.valueOf(Long.parseLong(rs.getString(1))));
			cat.setCategoryName(rs.getString(2));
			cat.setDescription(rs.getString(3));
			cat.setPicture(rs.getString(4));
			cat.setFile(rs.getString(5));
			result.add(cat);
		}
		return result;
	}
	
	static protected ArrayList<Supplier> getSuppliers(ResultSet rs) throws SQLException {
		ArrayList<Supplier> result = new ArrayList<>();
		while (rs.next()){
			Supplier sup = new Supplier();
			sup.setSupplierID(BigInteger.valueOf(Long.parseLong(rs.getString(1))));
			sup.setCompanyName(rs.getString(2));
			sup.setContactName(rs.getString(3));
			sup.setContactTitle(rs.getString(4));
			sup.setAddress(rs.getString(5));
			sup.setCity(rs.getString(6));
			sup.setRegion(rs.getString(7));
			sup.setPostalCode(rs.getString(8));
			sup.setCountry(rs.getString(9));
			sup.setPhone(rs.getString(10));
			sup.setFax(rs.getString(11));
			sup.setHomePage(rs.getString(12));					
			result.add(sup);
		}
		return result;
	}
	
	@Override
	public ArrayList<Product> getProducts() throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_SELECT_PRODUCT);
			ResultSet rs = ps.executeQuery();
			return (getProducts(rs));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public ArrayList<XProduct> getXProducts() throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_SELECT_XPRODUCT);
			ResultSet rs = ps.executeQuery();
			return (getXProducts(rs));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public ArrayList<Category> getCategories() throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_SELECT_CATEGORY);
			ResultSet rs = ps.executeQuery();
			return (getCategories(rs));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public ArrayList<Supplier> getSuppliers() throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_ADD_SUPPLIER);
			ResultSet rs = ps.executeQuery();
			return (getSuppliers(rs));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public Product getProduct(int sid) throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_SELECT_PRODUCT + " " + MySQLDAO.SQL_IDCON_PRODUCT);
			ps.setInt(1, sid);
			ResultSet rs = ps.executeQuery();
			return (getProducts(rs).get(0));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public XProduct getXProduct(int sid) throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_SELECT_XPRODUCT + " " + MySQLDAO.SQL_IDCON_PRODUCT);
			ps.setInt(1, sid);
			ResultSet rs = ps.executeQuery();
			return (getXProducts(rs).get(0));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public Category getCategory(int cid) throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_SELECT_CATEGORY + " " + MySQLDAO.SQL_IDCON_CATEGORY);
			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();
			return (getCategories(rs).get(0));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public Supplier getSupplier(int sid) throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_SELECT_SUPPLIER + " " + MySQLDAO.SQL_IDCON_SUPPLIER);
			ps.setInt(1, sid);
			ResultSet rs = ps.executeQuery();
			return (getSuppliers(rs).get(0));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public ArrayList<XProduct> searchXProducts(String kw) {
		if ("".equals(kw)) return getXProducts();
		
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_SELECT_XPRODUCT + " " + MySQLDAO.SQL_KWCON_XPRODUCT);
			String pattern = "%" + kw + "%";
			for (int i = 1; i <= 12; i++) {
				ps.setString(i, pattern);
			}
			ResultSet rs = ps.executeQuery();
			return (getXProducts(rs));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public ArrayList<Category> searchCategories(String kw) {
		if ("".equals(kw)) return getCategories();
		
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_SELECT_CATEGORY + " " + MySQLDAO.SQL_KWCON_CATEGORY);
			String pattern = "%" + kw + "%";
			for (int i = 1; i <= 4; i++) {
				ps.setString(i, pattern);
			}
			ResultSet rs = ps.executeQuery();
			return (getCategories(rs));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
		}
	}
	
	@Override
	public ArrayList<Supplier> searchSuppliers(String kw) {
		if ("".equals(kw)) return getSuppliers();
		
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_SELECT_SUPPLIER + " " + MySQLDAO.SQL_KWCON_SUPPLIER);
			String pattern = "%" + kw + "%";
			for (int i = 1; i <= 12; i++) {
				ps.setString(i, pattern);
			}
			ResultSet rs = ps.executeQuery();
			return (getSuppliers(rs));
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage());
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
		Collection<Category> ccs = getCategories();
		for (Category c : ccs) {
			cs.addCategory(c);
		}
		Collection<Supplier> css = getSuppliers();
		for (Supplier s : css) {
			ss.addSupplier(s);
		}
		Collection<Product> cps = getProducts();
		for (Product p : cps) {
			ps.addProduct(p);
		}
		db.setCategories(cs);
		db.setSuppliers(ss);
		db.setProducts(ps);
		return db;
	}
	
	protected void clearProducts() throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_CLEAR_PRODUCT);			
			ps.executeUpdate();
		} 
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage()); 
		}
	}
	
	protected void clearSuppliers() throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_CLEAR_SUPPLIER);		
			ps.executeUpdate();
		} 
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage()); 
		}
	}
	
	protected void clearCategories() throws DAOException {
		try {
			PreparedStatement ps = this.con.prepareStatement(MySQLDAO.SQL_CLEAR_CATEGORY);		
			ps.executeUpdate();
		} 
		catch (SQLException sqle) {
			throw new DAOException(sqle.getMessage()); 
		}
	}
	
	@Override
	public void fromDatabase(Database db) throws DAOException {
		List<Product> ps = db.getProducts().getProduct();
		List<Category> cs = db.getCategories().getCategory();
		List<Supplier> ss = db.getSuppliers().getSupplier();
		
		this.clearCategories();
		this.clearSuppliers();
		this.clearProducts();
		
		for (Supplier s : ss) {
			this.addSupplier(s);
		}		
		for (Category c : cs) {
			this.addCategory(c);
		}		
		for (Product p : ps) {
			this.addProduct(p);
		}		
	}
}
