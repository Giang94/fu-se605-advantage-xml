package dao;

import java.util.ArrayList;

import beans.Category;
import beans.Product;
import beans.Supplier;
import beans.XProduct;

public interface IDAO {
	void setAutoCommit(boolean b);
	void init(String[] args);
	void addProduct(Product p) throws DAOException;
	void addCategory(Category c) throws DAOException;
	void addSupplier(Supplier s) throws DAOException;	
	void updateProduct(Product p) throws DAOException;
	void updateCategory(Category c) throws DAOException;
	void updateSupplier(Supplier s) throws DAOException;
	void deleteProduct(int pid) throws DAOException;
	void deleteCategory(int cid) throws DAOException;
	void deleteSupplier(int sid) throws DAOException;
	ArrayList<XProduct> getProducts();
	ArrayList<Supplier> getSuppliers();
	ArrayList<Category> getCategories();
	ArrayList<XProduct> searchProducts(String kw);
	ArrayList<Supplier> searchSuppliers(String kw);
	ArrayList<Category> searchCategories(String kw);	
	void fromFile(String path) throws DAOException;
	void toFile(String path) throws DAOException;
	void commit();
}
