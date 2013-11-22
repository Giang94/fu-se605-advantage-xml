<%@page import="java.math.BigInteger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.*"%>
<%@ page import=" java.math.*"%>
<%@ page errorPage="Error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Product</title>
</head>
<body>
	<div>
		<h1 style="float: left">View Product</h1>
		<div style="float: right; margin-top: 25px"
			title="Input text to search for Supplier">
			<form action="ViewProduct" method="post">
				<input type="text" name="search" id="search" /> <input
					type="submit" name="submit" value="Search" />
			</form>
		</div>
	</div>
	<div style="clear: both;">
		<%
			Products product = (Products) request.getAttribute("product");
			if (product == null) {
				throw new RuntimeException("No data found!!!");
			}
			if (product.size() == 0) {
				out.println("No data found!!!");
			} else {
				List<Product> pro = product.getProduct();
		%>
		<table border="1">
			<tr>
				<th>Product ID</th>
				<th>Product Name</th>
				<th>Category Name</th>
				<th>Supplier Name</th>
				<th>Quantity Per Unit</th>
				<th>Unit Price</th>
				<th>Units In Stock</th>
				<th>Units On Order</th>
				<th>Reorder Level</th>
				<th>Discontinued</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (int i = 0; i < pro.size(); i++) {
						Product p = (Product) pro.get(i);
			%>
			<tr>
				<%
					out.println("<td>" + p.getProductID() + "</td>");
					out.println("<td>" + p.getProductName() + "</td>");
					out.println("<td>" + p.getCategoryName() + "</td>");
					out.println("<td>" + p.getSupplierName() + "</td>");
					out.println("<td>" + p.getQuantityPerUnit() + "</td>");
					out.println("<td>" + p.getUnitPrice() + "</td>");
					out.println("<td>" + p.getUnitsInStock() + "</td>");
					out.println("<td>" + p.getUnitsOnOrder() + "</td>");
					out.println("<td>" + p.getReorderLevel() + "</td>");
					out.println("<td>" + (p.getDiscontinued() == BigInteger.ONE?"Yes":"No") + "</td>");
				%>
				<td><a href="UpdateProduct.jsp">Update</a></td>
				<td><a href="DeleteProduct.jsp">Delete</a></td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</div>

</body>
</html>