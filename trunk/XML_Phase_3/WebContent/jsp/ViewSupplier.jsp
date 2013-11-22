<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.*"%>
<%@ page errorPage="Error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Supplier</title>
</head>
<body>
	<div>
		<h1 style="float: left">View Supplier</h1>
		<div style="float: right; margin-top: 25px"
			title="Input text to search for Supplier">
			<form action="ViewSupplier" method="post">
				<input type="text" name="search" id="search" /> <input
					type="submit" name="submit" value="Search" />
			</form>
		</div>
	</div>
	<div style="clear: both;">
		<%
			Suppliers suppliers = (Suppliers) request.getAttribute("supplier");
			if (suppliers == null) {
				throw new RuntimeException("No data found for Supplier!!!");
			}
			if (suppliers.size() == 0) {
				out.println("No data found for Supplier!!!");
			} else {
				List<Supplier> sup = suppliers.getSupplier();
		%>
		<table border="1">
			<tr>
				<th>SupplierID</th>
				<th>CompanyName</th>
				<th>ContactName</th>
				<th>ContactTitle</th>
				<th>Address</th>
				<th>City</th>
				<th>Region</th>
				<th>PostalCode</th>
				<th>Country</th>
				<th>Phone</th>
				<th>Fax</th>
				<th>HomePage</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (int i = 0; i < sup.size(); i++) {
						Supplier s = (Supplier) sup.get(i);
			%>
			<tr>
				<%
					out.println("<td>" + s.getSupplierID() + "</td>");
					out.println("<td>" + s.getCompanyName() + "</td>");
					out.println("<td>" + s.getContactName() + "</td>");
					out.println("<td>" + s.getContactTitle() + "</td>");
					out.println("<td>" + s.getAddress() + "</td>");
					out.println("<td>" + s.getCity() + "</td>");
					out.println("<td>" + s.getRegion() + "</td>");
					out.println("<td>" + s.getPostalCode() + "</td>");
					out.println("<td>" + s.getCountry() + "</td>");
					out.println("<td>" + s.getPhone() + "</td>");
					out.println("<td>" + s.getFax() + "</td>");
					out.println("<td>" + s.getHomePage() + "</td>");
				%>
				<td><a href="UpdateSupplier.jsp">Update</a></td>
				<td><a href="DeleteSupplier.jsp">Delete</a></td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</div>
</body>
</html>