package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import beans.Category;
import beans.Database;
import beans.Product;
import beans.Supplier;
import beans.XProduct;

public class VDAO {	
	public void setAutoCommit(boolean b){};
	//public void init(String[] args){};
	public static VDAO create(String[] args) throws DAOException{
		return new VDAO();
	}
	public void addProduct(Product p) throws DAOException{}
	public void addCategory(Category c) throws DAOException{}
	public void addSupplier(Supplier s) throws DAOException{}	
	public void updateProduct(Product p) throws DAOException{}
	public void updateCategory(Category c) throws DAOException{}
	public void updateSupplier(Supplier s) throws DAOException{}
	public void deleteProduct(int pid) throws DAOException{}
	public void deleteCategory(int cid) throws DAOException{}
	public void deleteSupplier(int sid) throws DAOException{}
	public ArrayList<Product> getProducts(){return new ArrayList<>();}
	public ArrayList<XProduct> getXProducts(){return new ArrayList<>();}
	public ArrayList<Supplier> getSuppliers(){return new ArrayList<>();}
	public ArrayList<Category> getCategories(){return new ArrayList<>();}
	public Product getProduct(int pid) {return new Product();}
	public XProduct getXProduct(int pid) {return new XProduct();}
	public Category getCategory(int cid) {return new Category();}
	public Supplier getSupplier(int sid) {return new Supplier();}	
	public ArrayList<XProduct> searchXProducts(String kw){return new ArrayList<>();}
	public ArrayList<Supplier> searchSuppliers(String kw){return new ArrayList<>();}
	public ArrayList<Category> searchCategories(String kw){return new ArrayList<>();}
	public Database toDatabase() throws DAOException {return new Database();}
	public void fromDatabase(Database db) throws DAOException {}
	public void fromFile(String path) throws DAOException {
		JAXBContext context;
		Database db;
		try {
			context = JAXBContext.newInstance(Database.class);
			Unmarshaller umar = context.createUnmarshaller();
			db = (Database)umar.unmarshal(new File(path));
		} catch (JAXBException e) {
			throw new DAOException(e.getMessage());
		}
		this.fromDatabase(db);
	}
	public void toFile(String path) throws DAOException {
		Database db = this.toDatabase();
		try {
		JAXBContext context = JAXBContext.newInstance(Database.class);
		Marshaller mars = context.createMarshaller();
		mars.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		mars.marshal(db, new FileOutputStream(path));
		}
		catch (FileNotFoundException fnfe) {
			throw new DAOException(fnfe.getMessage());
		}
		catch (Exception ex) {
			throw new DAOException(ex.getMessage());
		}
	}
	
	public void commit() throws DAOException{}
}
