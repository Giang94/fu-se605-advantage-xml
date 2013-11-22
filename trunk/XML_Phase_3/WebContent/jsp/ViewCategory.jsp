<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page errorPage="Error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Category</title>
</head>
<body>
	<div>
		<h1 style="float: left">View Category</h1>
		<div style="float: right; margin-top: 25px"
			title="Input text to search for Category">
			<form action="ViewCategory" method="post">
				<input type="text" name="search" id="search" /> <input
					type="submit" name="submit" value="Search" />
			</form>
		</div>
	</div>
	<div style="clear: both;">
		<%
			Categories categories = (Categories) request
					.getAttribute("category");
			if (categories == null) {
				throw new RuntimeException("No data found!!!");
			}
			if (categories.size() == 0) {
				out.println("No data found!!!");
			} else {

				List<Category> cat = categories.getCategory();
		%>
		<table border="1">
			<tr>
				<th>CategoryID</th>
				<th>CategoryName</th>
				<th>Description</th>
				<th>Picture</th>
				<th>File</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (int i = 0; i < cat.size(); i++) {
						Category c = (Category) cat.get(i);
			%>
			<tr>
				<%
					out.println("<td>" + c.getCategoryID() + "</td>");
					out.println("<td>" + c.getCategoryName() + "</td>");
					out.println("<td>" + c.getDescription() + "</td>");
					out.println("<td>" + c.getPicture() + "</td>");
					out.println("<td>" + c.getFile() + "</td>");
				%>
				<td><a href="UpdateCategory.jsp">Update</a></td>
				<td><a href="DeleteCategory.jsp">Delete</a></td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</div>
</body>
</html>