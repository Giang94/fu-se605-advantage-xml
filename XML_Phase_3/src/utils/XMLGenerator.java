package utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Categories;
import beans.Category;
import beans.Product;
import beans.Products;
import beans.Supplier;
import beans.Suppliers;

public class XMLGenerator {
	private final String login, passwd, host;

	public XMLGenerator(String login, String passwd, String host) {
		this.login = login;
		this.passwd = passwd;
		this.host = host;
	}

	public Categories getCategory() throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + host, login,
					passwd);

			st = con.createStatement();
			rs = st.executeQuery("select * from categories");

			Categories cat = new Categories();

			int num = 1;
			while (rs.next()) {
				Category c = new Category();
				c.setCategoryID(BigInteger.valueOf(Long.parseLong(rs.getString(1))));
				c.setCategoryName(rs.getString(2));
				c.setDescription(rs.getString(3));
//				Blob blob = rs.getBlob(4);
//				int blobLength = (int) blob.length();
//				byte[] blobAsBytes = blob.getBytes(1, blobLength);
//				blob.free();
//				c.setPicture(blobAsBytes);
				c.setPicture(null);
				c.setFile(rs.getString(5));

				cat.addCategory(c);

			}
			return cat;
		} catch (SQLException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
	}

	public Suppliers getSupplier() throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + host, login,
					passwd);

			st = con.createStatement();
			rs = st.executeQuery("select * from suppliers");

			Suppliers sup = new Suppliers();

			int num = 1;
			while (rs.next()) {
				Supplier s = new Supplier();
				s.setSupplierID(BigInteger.valueOf(Long.parseLong(rs.getString(1))));
				s.setCompanyName(rs.getString(2));
				s.setContactName(rs.getString(3));
				s.setContactTitle(rs.getString(4));
				s.setAddress(rs.getString(5));
				s.setCity(rs.getString(6));
				s.setRegion(rs.getString(7));
				s.setPostalCode(rs.getString(8));
				s.setCountry(rs.getString(9));
				s.setPhone(rs.getString(10));
				s.setFax(rs.getString(11));
				s.setHomePage(rs.getString(12));
				
				sup.addSupplier(s);
			}
			return sup;
		} catch (SQLException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
	}

	public Products getProducts() throws SQLException, ClassNotFoundException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + host, login,
					passwd);

			st = con.createStatement();
			rs = st.executeQuery("select * from products");

			Products pro = new Products();

			int num = 1;
			while (rs.next()) {
				Product p = new Product();
				p.setProductID(BigInteger.valueOf(Long.parseLong(rs.getString(1))));
				p.setProductName(rs.getString(2));
				p.setSupplierID(BigInteger.valueOf(Long.parseLong(rs.getString(3))));
				p.setCategoryID(BigInteger.valueOf(Long.parseLong(rs.getString(4))));
				p.setQuantityPerUnit(rs.getString(5));
				p.setUnitPrice(BigDecimal.valueOf(Double.parseDouble(rs
						.getString(6))));
				p.setUnitsInStock(Integer.parseInt(rs.getString(7)));
				p.setUnitsOnOrder(Integer.parseInt(rs.getString(8)));
				p.setReorderLevel(Integer.parseInt(rs.getString(9)));
				p.setDiscontinued(BigInteger.valueOf(rs.getInt(10)));
				
				
				pro.addProduct(p);
			}
			return pro;
		} catch (SQLException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (st != null)
				try {
					st.close();
				} catch (SQLException e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
	}
}
