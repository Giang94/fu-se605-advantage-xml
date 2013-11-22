package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.StringUtils;
import beans.Product;
import beans.Products;

/**
 * Servlet implementation class ViewProduct
 */
@WebServlet("/ViewProduct")
public class ViewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager
					.getConnection(StringUtils.connection_String);
			
			String sql = "";
			ResultSet rs = null;

			String query = request.getParameter("search");
			if (query == null || query.equals("")) {
				sql = "SELECT p.*, c.CategoryName, s.CompanyName FROM products p "
						+ "JOIN categories c ON p.CategoryID = c.CategoryID "
						+ "JOIN suppliers s ON p.SupplierID = s.SupplierID";
				Statement st = con.createStatement();
				rs = st.executeQuery(sql);
			} else {
				sql = "SELECT p.*, c.CategoryName, s.CompanyName FROM products p "
						+ "JOIN categories c ON p.CategoryID = c.CategoryID "
						+ "JOIN suppliers s ON p.SupplierID = s.SupplierID WHERE "
						+ "p.ProductID LIKE ? OR "
						+ "p.ProductName LIKE ? OR "
						+ "p.QuantityPerUnit LIKE ? OR "
						+ "p.UnitPrice LIKE ? OR "
						+ "p.UnitsInStock LIKE ? OR "
						+ "p.UnitsOnOrder LIKE ? OR "
						+ "p.ReorderLevel LIKE ? OR "
						+ "p.Discontinued LIKE ? OR "
						+ "c.CategoryName LIKE ? OR "
						+ "s.CompanyName LIKE ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, "%" + query + "%");
				pst.setString(2, "%" + query + "%");
				pst.setString(3, "%" + query + "%");
				pst.setString(4, "%" + query + "%");
				pst.setString(5, "%" + query + "%");
				pst.setString(6, "%" + query + "%");
				pst.setString(7, "%" + query + "%");
				pst.setString(8, "%" + (query.equalsIgnoreCase("yes")?"1":"0") + "%");
				pst.setString(9, "%" + query + "%");
				pst.setString(10, "%" + query + "%");
				rs = pst.executeQuery();
			}

			Products p = new Products();

			while (rs.next()) {
				Product pro = new Product();
				pro.setProductID(BigInteger.valueOf(Long.parseLong(rs
						.getString(1))));
				pro.setProductName(rs.getString(2));
				pro.setSupplierID(BigInteger.valueOf(Long.parseLong(rs
						.getString(3))));
				pro.setCategoryID(BigInteger.valueOf(Long.parseLong(rs
						.getString(4))));
				pro.setQuantityPerUnit(rs.getString(5));
				pro.setUnitPrice(BigDecimal.valueOf(Double.parseDouble(rs
						.getString(6))));
				pro.setUnitsInStock(Integer.parseInt(rs.getString(7)));
				pro.setUnitsOnOrder(Integer.parseInt(rs.getString(8)));
				pro.setReorderLevel(Integer.parseInt(rs.getString(9)));
				pro.setDiscontinued(BigInteger.valueOf(rs.getInt(10)));
				pro.setCategoryName(rs.getString(11));
				pro.setSupplierName(rs.getString(12));

				p.addProduct(pro);
			}
			request.setAttribute("product", p);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher dis = request
				.getRequestDispatcher("jsp/ViewProduct.jsp");
		dis.forward(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
