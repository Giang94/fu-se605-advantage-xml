<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add Category</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/validator.js"></script>

<script type="text/javascript">
	//Init for validate
	validate_params[0] = new ValidateArgs(isValidString, 'categoryName', false, 15);	
</script>

<title>Insert title here</title>
</head>
<body>
	<div>
		<h2>Database Northwind - Add category</h2>
	</div>

	<hr />
	<div>
		<form action="../AddCategory" method="post">
			<table>
				<!-- tr id="CategoryID_tr">
					<td align="right">CategoryID : </td>
					<td><input type="text" id="categoryID" /></td>
					<td><div id="categoryID_err"></div></td>
				</tr-->

				<tr id="CategoryName_tr">
					<td align="right">Category Name: </td>
					<td><input type="text" id="categoryName"
						name="categoryName"
						onblur="OnTextChange(0, 'categoryName_err', 'submit');" 
						onkeyup="OnTextChange(0, 'categoryName_err', 'submit');"/></td>
					<td><div id="categoryName_err"></div></td>
				</tr>
				<tr id="Description_tr">
					<td align="right">Description: </td>
					<td><input type="text" id="description" name="description"/></td>
					<td><div id="description_err"></div></td>
				</tr>
				<tr id="file_tr">
					<td align="right">File: </td>
					<td><input type="text" id="file" name="file"/></td>
					<td><div id="file_err"></div></td>
				</tr>
				<tr id="Image_tr">
					<td align="right">Image: </td>
					<td><input type="file" id="image" name="image"/></td>
					<td><div id="image_err"></div></td>
				</tr>
				<tr id="Summit_tr">
					<td></td>
					<td colspan="2"><input type="submit" id="submit" disabled="disabled" /></td>					
				</tr>
			</table>			
		</form>
	</div>
</body>
</html>