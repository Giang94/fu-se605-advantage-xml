package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.StringUtils;

/**
 * Servlet implementation class getImage
 */
@WebServlet("/getImage")
public class getImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int img_id = Integer.parseInt(request.getParameter("cat_id"));
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(StringUtils.connection_String);
			PreparedStatement pstmt = null;
			OutputStream oImage;
			try {
				pstmt = con.prepareStatement("SELECT Picture FROM Categories WHERE CategoryID = ?");
				pstmt.setInt(1, img_id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					byte barray[] = rs.getBytes(1);
					response.setContentType("image/gif");
					oImage = response.getOutputStream();
					oImage.write(barray);
					oImage.flush();
					oImage.close();
				}
			} catch (Exception ex) {
				// ex.printStackTrace();
			} finally {
				try {
					if (con != null)
						con.close();
				} catch (Exception ex) {
					// ex.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
