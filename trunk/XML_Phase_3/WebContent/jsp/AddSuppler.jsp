<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add Supplier</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../js/validator.js"></script>
<script type="text/javascript">
	validate_params[0] = new ValidateArgs(isValidString, 'companyName', false, 40);
	validate_params[1] = new ValidateArgs(isValidString, 'contactName', true, 30);
	validate_params[2] = new ValidateArgs(isValidString, 'contactTitle', true, 30);
	validate_params[3] = new ValidateArgs(isValidString, 'address', true, 60);
	validate_params[4] = new ValidateArgs(isValidString, 'city', true, 15);
	validate_params[5] = new ValidateArgs(isValidString, 'region', true, 15);
	validate_params[6] = new ValidateArgs(isValidString, 'postalCode', true, 10);
	validate_params[7] = new ValidateArgs(isValidString, 'country', true, 15);
	validate_params[8] = new ValidateArgs(isValidString, 'phone', true, 24);
	validate_params[9] = new ValidateArgs(isValidString, 'fax', true, 24);	
</script>
</head>
<body>
	<h2>Database Northwind - Add Supplier</h2>
	<hr />
	<form action="../AddSupplier" method="post">	
		<table>
			<!-- tr id="SupplierID_tr">
                        <td align="right"><p>SupplierID : </p></td>
                        <td><input type="text" id="supplierID" /></td>
                        <td><div id="supplierID_err"></div></td> 
                </tr-->

			<tr id="CompanyName_tr">
				<td align="right">Company Name: </td>
				<td><input type="text" id="companyName"
					name="companyName"
					onblur="OnTextChange(0, 'companyName_err', 'submit')" 
					onkeyup="OnTextChange(0, 'companyName_err', 'submit')"/></td>
				<td><div id="companyName_err"></div></td>
			</tr>
			<tr id="ContactName_tr">
				<td align="right">Contact Name: </td>
				<td><input type="text" id="contactName"
					name="contactName"
					onblur="OnTextChange(1, 'contactName_err', 'submit')"
					onkeyup="OnTextChange(1, 'contactName_err', 'submit')"/></td>
				<td><div id="contactName_err"></div></td>
			</tr>
			<tr id="ContactTitle_tr">
				<td align="right">Contact Title: </td>
				<td><input type="text" id="contactTitle"
					name="contactTitle"
					onblur="OnTextChange(2, 'contactTitle_err', 'submit')" 
					onkeyup="OnTextChange(2, 'contactTitle_err', 'submit')"/></td>
				<td><div id="contactTitle_err"></div></td>
			</tr>
			<tr id="Address_tr">
				<td align="right">Address: </td>
				<td><input type="text" id="address"
					name="address"
					onblur="OnTextChange(3, 'address_err', 'submit')"
					onkeyup="OnTextChange(3, 'address_err', 'submit')"/></td>
				<td><div id="address_err"></div></td>
			</tr>
			<tr id="City_tr">
				<td align="right">City: </td>
				<td><input type="text" id="city"
					name="city"
					onblur="OnTextChange(4, 'city_err', 'submit')" 
					onkeyup="OnTextChange(4, 'city_err', 'submit')"/></td>
				<td><div id="city_err"></div></td>
			</tr>
			<tr id="Region_tr">
				<td align="right">Region: </td>
				<td><input type="text" id="region"
					name="region"
					onblur="OnTextChange(5, 'region_err', 'submit')"
					onkeyup="OnTextChange(5, 'region_err', 'submit')"
					/></td>
				<td><div id="region_err"></div></td>
			</tr>
			<tr id="PostalCode_tr">
				<td align="right">Postal Code: </td>
				<td><input type="text" id="postalCode"
					name="postalCode"
					onblur="OnTextChange(6, 'postalCode_err', 'submit')"
					onkeyup="OnTextChange(6, 'postalCode_err', 'submit')" /></td>
				<td><div id="postalCode_err"></div></td>
			</tr>
			<tr id="Country_tr">
				<td align="right">Country: </td>
				<td><input type="text" id="country"
					name="country"
					onblur="OnTextChange(7, 'country_err', 'submit')" 
					onkeyup="OnTextChange(7, 'country_err', 'submit')"
					/></td>
				<td><div id="country_err"></div></td>
			</tr>
			<tr id="Phone_tr">
				<td align="right">Phone: </td>
				<td><input type="text" id="phone"
					name="phone"
					onblur="OnTextChange(8, 'phone_err', 'submit')" 
					onkeyup="OnTextChange(8, 'phone_err', 'submit')" /></td>
				<td><div id="phone_err"></div></td>
			</tr>
			<tr id="Fax_tr">
				<td align="right">Fax: </td>
				<td><input type="text" id="fax"
					name="fax"
					onblur="OnTextChange(9, 'fax_err', 'submit')" 
					onkeyup="OnTextChange(9, 'fax_err', 'submit')" /></td>
				<td><div id="fax_err"></div></td>
			</tr>
			<tr id="Hompage_tr">
				<td align="right">HomePage: </td>
				<td><input type="text" id="homePage" name="honePage"/></td>
				<td><div id="homePage_err"></div></td>
			</tr>
			<tr id="button_tr">
				<td></td>
				<td><input type="submit" id="submit" value="Submit" name="submit" /></td>
				<td></td>
			</tr>
		</table>
	</form>
</body>

</html>