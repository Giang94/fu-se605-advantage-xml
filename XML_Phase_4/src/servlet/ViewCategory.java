package servlet;

import java.io.IOException;
import java.io.OutputStream;
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
import beans.Categories;
import beans.Category;

/**
 * Servlet implementation class ViewCategory
 */
@WebServlet("/ViewCategory")
public class ViewCategory extends HttpServlet {
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
				sql = "SELECT * FROM categories";
				Statement st = con.createStatement();
				rs = st.executeQuery(sql);
			} else {
				sql = "SELECT * FROM categories WHERE "
						+ "CategoryID LIKE ? OR "
						+ "CategoryName LIKE ? OR "
						+ "Description LIKE ? OR "
						+ "File LIKE ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, "%" + query + "%");
				pst.setString(2, "%" + query + "%");
				pst.setString(3, "%" + query + "%");
				pst.setString(4, "%" + query + "%");
				rs = pst.executeQuery();
			}
			
			Categories c = new Categories();
			
			while (rs.next()){
				Category cat = new Category();
				cat.setCategoryID(Integer.parseInt(rs.getString(1)));
				cat.setCategoryName(rs.getString(2));
				cat.setDescription(rs.getString(3));
				cat.setPicture("<img src='getImage?cat_id=" + cat.getCategoryID() + "'/>");
				cat.setFile(rs.getString(5));

				c.addCategory(cat);
			}
			request.setAttribute("category", c);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		RequestDispatcher dis = request.getRequestDispatcher("jsp/ViewCategory.jsp");
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
