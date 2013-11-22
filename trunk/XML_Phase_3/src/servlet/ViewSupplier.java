package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import beans.Supplier;
import beans.Suppliers;

/**
 * Servlet implementation class TestMySQL
 */
@WebServlet("/ViewSupplier")
public class ViewSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(StringUtils.connection_String);
			
			String sql = "";
			ResultSet rs = null;
			
			String query = request.getParameter("search");
			if (query == null || query.equals("")){
				sql = "SELECT * FROM suppliers";
				Statement st = con.createStatement();
				rs = st.executeQuery(sql);
			} else {
				sql = "SELECT * FROM suppliers WHERE "
						+ "SupplierID LIKE ? OR "
						+ "CompanyName LIKE ? OR "
						+ "ContactName LIKE ? OR "
						+ "ContactTitle LIKE ? OR "
						+ "Address LIKE ? OR "
						+ "City LIKE ? OR "
						+ "Region LIKE ? OR "
						+ "PostalCode LIKE ? OR "
						+ "Country LIKE ? OR "
						+ "Phone LIKE ? OR "
						+ "Fax LIKE ? OR "
						+ "HomePage LIKE ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, "%" + query + "%");
				pst.setString(2, "%" + query + "%");
				pst.setString(3, "%" + query + "%");
				pst.setString(4, "%" + query + "%");
				pst.setString(5, "%" + query + "%");
				pst.setString(6, "%" + query + "%");
				pst.setString(7, "%" + query + "%");
				pst.setString(8, "%" + query + "%");
				pst.setString(9, "%" + query + "%");
				pst.setString(10, "%" + query + "%");
				pst.setString(11, "%" + query + "%");
				pst.setString(12, "%" + query + "%");
				rs = pst.executeQuery();
			}
			
			Suppliers s = new Suppliers();
			
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
						
				s.addSupplier(sup);
			}
			request.setAttribute("supplier", s);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		RequestDispatcher dis = request.getRequestDispatcher("jsp/ViewSupplier.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
