package command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import dao.DAOException;
import dao.MemDAO;
import beans.Category;
import beans.Product;
import beans.Supplier;
import beans.XProduct;

public class Command {

	/**
	 * @param args
	 */	
	static MemDAO mem = MemDAO.create(new String[] {"Northwind"});
			
	public static boolean validateStr(String str)
	{
		if("".equalsIgnoreCase(str.trim()))
		{
			System.out.println("Not Null!");
			return false;
		}
		return true;
	}
	
	public static boolean validateNumber(String str)
	{
		try
		{
			Integer.parseInt(str);
		}
		catch (NumberFormatException nfe) 
		{
			System.out.println("ID must be a number!");
			return false;		
		}		
		return true;
	}
	
	public static boolean validateNumberValue(String str)
	{
		try
		{
			int i = Integer.parseInt(str);
			if(i != 0 && i != 1)
			{
				System.out.println("ID must be 1 or 0");
				return false;
			}			
		}
		catch (NumberFormatException nfe)
		{
			System.out.println("ID must be a number");
			return false;
		}	
		
		return true;
	}	
	
	public static boolean validateDefault(String str)
	{		
		if("".equalsIgnoreCase(str.trim()))
		{
			str = "0";
			return true;			
		}
		
		
		System.out.println("Must be a number!");
		return false;		
	}
	
	public static void displaySuccess()
	{
		System.out.println("Successful");
		System.out.println("---------------------");
		System.out.println();
	}
	
	public static Category categoryInfor(Category cat) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		while(true)
		{
			System.out.print("Enter Category Name: ");
			String catName = br.readLine();
			
			if(validateStr(catName))
			{
				cat.setCategoryName(catName);
				break;
			}
		}
		System.out.print("Enter Category Description: ");
		String catDes = br.readLine();		
		cat.setDescription(catDes);		
		System.out.print("Enter Category File: ");
		String catFile = br.readLine();		
		cat.setFile(catFile);
		
		return cat;
	}	
	
	public static void addCategory() throws IOException
	{				
		Category category = new Category();		
				
		mem.addCategory(categoryInfor(category));	
		
		displaySuccess();
	}
	
	public static void updateCategory() throws IOException
	{				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Category category = new Category();
		
		while(true)
		{
			System.out.print("Update Category ID: ");			
			String catId = br.readLine();
			
			if(validateStr(catId) && validateNumber(catId))				
			{
				category.setCategoryID(BigInteger.valueOf(Long.parseLong(catId)));
				mem.updateCategory(categoryInfor(category));
				displaySuccess();
				break;						
			}
		}
	}
	
	public static void deleteCategory() throws IOException
	{							
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{
			System.out.print("Delete Category ID: ");			
			String catId = br.readLine();
			
			if(validateStr(catId) && validateNumber(catId))
			{
				mem.deleteCategory(Integer.parseInt(catId));
				displaySuccess();
				break;
			}
		}					
	}
	
	public static Supplier supplierInfor(Supplier sup) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		while(true)
		{
			System.out.print("Enter Company Name: ");
			String comName = br.readLine();
			
			if(validateStr(comName))
			{
				sup.setCompanyName(comName);
				break;
			}
		}
		System.out.print("Enter Contact Name: ");
		String conName = br.readLine();	
		sup.setContactName(conName);
		System.out.print("Enter Contact Title: ");
		String conTitle = br.readLine();	
		sup.setContactTitle(conTitle);
		System.out.print("Enter Address: ");
		String address = br.readLine();	
		sup.setAddress(address);
		System.out.print("Enter City: ");
		String city = br.readLine();
		sup.setCity(city);
		System.out.print("Enter Region: ");
		String region = br.readLine();
		sup.setRegion(region);
		System.out.print("Enter Postal Code: ");
		String posCode = br.readLine();
		sup.setPostalCode(posCode);
		System.out.print("Enter Country: ");
		String country = br.readLine();
		sup.setCountry(country);
		System.out.print("Enter Phone: ");
		String phone = br.readLine();
		sup.setPhone(phone);
		System.out.print("Enter Fax: ");
		String fax = br.readLine();
		sup.setFax(fax);
		System.out.print("Enter HomePage: ");
		String hmPage = br.readLine();
		sup.setHomePage(hmPage);
		
		return sup;
	}
	
	public static void addSupplier() throws IOException
	{				
		Supplier supplier = new Supplier();
					
		mem.addSupplier(supplierInfor(supplier));	
		
		displaySuccess();
	}
	
	public static void updateSupplier() throws IOException
	{			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Supplier supplier = new Supplier();
		
		while(true)
		{
			System.out.print("Update Supplier ID: ");
			String supId = br.readLine();
			
			if(validateStr(supId) && validateNumber(supId))				
			{
				supplier.setSupplierID(BigInteger.valueOf(Long.parseLong(supId)));
				mem.updateSupplier(supplierInfor(supplier));
				displaySuccess();
				break;
			}
		}				
	}
	
	public static void deleteSupplier() throws IOException
	{				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{
			System.out.print("Delete Supplier ID: ");
			String supId = br.readLine();
			
			if(validateStr(supId) && validateNumber(supId))			
			{
				mem.deleteSupplier(Integer.parseInt(supId));
				displaySuccess();
				break;
			}
		}		
	}
	
	public static Product productInfor(Product pro) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		while(true)
		{
			System.out.print("Enter Product Name: ");			
			String proName = br.readLine();
			if(validateStr(proName))
			{
				pro.setProductName(proName);
				break;
			}
		}	
		
		while(true)
		{
			System.out.print("Enter Supplier ID: ");
			String supId = br.readLine();
			
			if(validateStr(supId) && validateNumber(supId))			
			{
				pro.setSupplierID(BigInteger.valueOf(Long.parseLong(supId)));
				break;
			}
		}
		
		while(true)
		{
			System.out.print("Enter Category ID: ");
			String catId = br.readLine();
			
			if(validateStr(catId) && validateNumber(catId))
			{
				pro.setCategoryID(BigInteger.valueOf(Long.parseLong(catId)));
				break;
			}
		}
		
		while(true)
		{
			System.out.print("Enter Quantity Per Unit: ");
			String quantity = br.readLine();
					
			if("".equalsIgnoreCase(quantity))
			{
				quantity = "0";
				pro.setQuantityPerUnit(quantity);
				break;
			}
			else
				if(validateNumber(quantity))
				{
					pro.setQuantityPerUnit(quantity);
					break;
				}			
		}
		
		while(true)
		{
			System.out.print("Enter Unit Price: ");
			String unitPrice = br.readLine();
			
			if("".equalsIgnoreCase(unitPrice))
			{
				unitPrice = "0";
				pro.setUnitPrice(BigDecimal.valueOf(Double.parseDouble(unitPrice)));
				break;
			}
			else
				if(validateNumber(unitPrice))
				{
					pro.setUnitPrice(BigDecimal.valueOf(Double.parseDouble(unitPrice)));
					break;
				}
		}
		
		while(true)
		{
			System.out.print("Enter Units In Stock: ");
			String unitsInStock = br.readLine();
			
			if("".equalsIgnoreCase(unitsInStock))
			{
				unitsInStock = "0";
				pro.setUnitsInStock(Integer.parseInt(unitsInStock));
				break;
			}
			else
				if(validateNumber(unitsInStock))
				{
					pro.setUnitsInStock(Integer.parseInt(unitsInStock));
					break;
				}
		}
		
		while(true)
		{			
			System.out.print("Enter Units On Order: ");
			String unitsOnOrder = br.readLine();

			if("".equalsIgnoreCase(unitsOnOrder))
			{
				unitsOnOrder = "0";
				pro.setUnitsOnOrder(Integer.parseInt(unitsOnOrder));
				break;
			}
			else
				if(validateNumber(unitsOnOrder))
				{
					pro.setUnitsOnOrder(Integer.parseInt(unitsOnOrder));
					break;
				}
		}
		
		while(true)
		{
			System.out.print("Enter Reorder Level: ");
			String level = br.readLine();
			
			if("".equalsIgnoreCase(level))
			{
				level = "0";
				pro.setReorderLevel(Integer.parseInt(level));
				break;
			}
			else				
				if(validateNumber(level))
				{
					pro.setReorderLevel(Integer.parseInt(level));
					break;
				}
		}
		
		while(true)
		{
			System.out.print("Enter Discountinued: ");
			String discountinued = br.readLine();
			
			if(validateStr(discountinued))
				if(validateNumberValue(discountinued))
				{
					pro.setDiscontinued(BigInteger.valueOf(Long.parseLong(discountinued)));
					break;
				}
		}
		
		return pro;
	}
	
	public static void addProduct() throws IOException
	{			
		mem.setAutoCommit(true);
		Product product = new Product();
					
		mem.addProduct(productInfor(product));
		
		displaySuccess();		
	}	
	
	public static void updateProduct() throws IOException 
	{
		mem.setAutoCommit(true);	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Product product = new Product();
		
		while(true)
		{
			System.out.print("Update Product ID: ");
			String proId = br.readLine();
			
			if(validateStr(proId))
				if(validateNumber(proId))
				{
					product.setProductID(BigInteger.valueOf(Long.parseLong(proId)));
					mem.updateProduct(productInfor(product));					
					displaySuccess();
					break;
				}
		}		
	}
	
	public static void deleteProduct() throws IOException
	{
		mem.setAutoCommit(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{
			System.out.print("Delete Product ID: ");
			String proId = br.readLine();
			
			if(validateStr(proId))
				if(validateNumber(proId))
				{
					mem.deleteProduct(Integer.parseInt(proId));
					displaySuccess();
					break;
				}			
		}			
	}	
	
	public static void showCategory(Category c)
	{
		System.out.print("Category Id: " + c.getCategoryID() + " | ");
		System.out.print("Category Name: " + c.getCategoryName() + " | ");
		System.out.print("Category Description: " + c.getDescription() + " | ");	
		System.out.println("File: " + c.getFile());
	}
	
	public static void showCategories(ArrayList<Category> cs)
	{
		for (Category c : cs) 
		{
			showCategory(c);
		}
		System.out.println("Total: " + cs.size());
		System.out.println("---------------------");
		System.out.println();
	}
	
	public static void showCategories()
	{
		showCategories(mem.getCategories());		
	}
	
	public static void showSupplier(Supplier s)
	{
		System.out.print("Supplier Id: " + s.getSupplierID() + " | ");
		System.out.print("Company Name: " + s.getCompanyName() + " | ");
		System.out.print("Contact Name: " + s.getContactName() + " | ");		
		System.out.print("Contact Title: " + s.getContactTitle() + " | ");
		System.out.print("Address: " + s.getAddress() + " | ");
		System.out.print("City: " + s.getCity() + " | ");
		System.out.print("Postal Code: " + s.getPostalCode() + " | ");
		System.out.print("Country: " + s.getCountry() + " | ");
		System.out.print("Phone: " + s.getPhone() + " | ");
		System.out.print("Fax: " + s.getFax() + " | ");
		System.out.println("HomePage: " + s.getHomePage());				
	}
	
	public static void showSuppliers(ArrayList<Supplier> sp)
	{
		for(Supplier s : sp)
		{
			showSupplier(s);
		}
		
		System.out.println("Total: " + sp.size());
		System.out.println("---------------------");
		System.out.println();
	}
	
	public static void showSuppliers()
	{
		showSuppliers(mem.getSuppliers());		
	}
	
	public static void showProduct(Product p)
	{
		System.out.print("Product Id: " + p.getProductID() + " | ");
		System.out.print("Product Name: " + p.getProductName() + " | ");
		System.out.print("Supplier Id: " + p.getSupplierID() + " | ");		
		System.out.print("Category Id: " + p.getCategoryID() + " | ");
		System.out.print("Quantity Per Unit: " + p.getQuantityPerUnit() + " | ");
		System.out.print("Unit Price: " + p.getUnitPrice() + " | ");
		System.out.print("Unit In Stock: " + p.getUnitsInStock() + " | ");
		System.out.print("Unit On Order: " + p.getUnitsOnOrder() + " | ");
		System.out.print("Phone: " + p.getReorderLevel() + " | ");
		System.out.println("Fax: " + p.getDiscontinued());				
	}
	
	public static void showProducts(ArrayList<XProduct> pr)
	{
		for(Product p: pr)
		{
			showProduct(p);
		}
		
		System.out.println("Total: " + pr.size());
		System.out.println("---------------------");
		System.out.println();
	}
	
	public static void showProducts()
	{
		showProducts((mem.getXProducts()));		
	}
	
	public static void searchCategory() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		while(true)
		{
			System.out.print("Search Category Keywords: ");
			String catId = br.readLine();
			
			if(validateStr(catId))				
			{
				showCategories(mem.searchCategories(catId));
				break;
			}
		}				
	}
	
	public static void searchSupplier() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		while(true)
		{
			System.out.print("Search Supplier Keywords: ");
			String supId = br.readLine();
			
			if(validateStr(supId))				
			{
				showSuppliers(mem.searchSuppliers(supId));
				break;
			}
		}				
	}
	
	public static void searchProduct() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		while(true)
		{
			System.out.print("Search Product Keywords: ");
			String proId = br.readLine();
			
			if(validateStr(proId))				
			{
				showProducts(mem.searchXProducts(proId));
				break;
			}
		}				
	}
	
	public static String readString() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter File Name:");
		String str = br.readLine();
		return str;		
	}
	
	public static void importXML()
	{
		try {
			mem.fromFile(readString());
		} catch (DAOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void exportXML()
	{
		try {
			mem.toFile(readString());
		} catch (DAOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		mem.setAutoCommit(true);
		
		boolean found = true;
		
		while(found == true) 
		{
			System.out.println("Northwind Database");
			System.out.print("1.Add New Category  ");
			System.out.print("2.Update A Category  ");
			System.out.println("3.Delete A Category  ");
			System.out.print("4.Add New Supplier  ");
			System.out.print("5.Update A Supplier  ");
			System.out.println("6.Delete A Supplier");
			System.out.print("7.Add New Product   ");
			System.out.print("8.Update A Product   ");
			System.out.println("9.Delete A Product");
			System.out.print("10.Show Categories ");
			System.out.print("11.Show Suppliers    ");
			System.out.println("12.Show Products");
			System.out.print("13.Search Category ");
			System.out.print("14.Search Supplier   ");
			System.out.println("15.Search Product");
			System.out.print("16.Import XML      ");
			System.out.println("17.Export XML");
			System.out.println("0.Exit");			
			
			Scanner scanner = new Scanner(new InputStreamReader(System.in));
			System.out.print("Enter Your Choice: ");			
			int number = scanner.nextInt();				
			
			switch(number)
			{
				case 1: addCategory();				
				break;
				case 2: updateCategory();
				break;
				case 3: deleteCategory();
				break;
				case 4: addSupplier();
				break;
				case 5: updateSupplier();
				break;
				case 6: deleteSupplier();
				break;
				case 7: addProduct();
				break;
				case 8: updateProduct();
				break;
				case 9: deleteProduct();
				break;
				case 10: showCategories();
				break;
				case 11: showSuppliers();
				break;
				case 12: showProducts();
				break;
				case 13: searchCategory();
				break;
				case 14: searchSupplier();
				break;
				case 15: searchProduct();
				break;
				case 16: importXML();
				break;
				case 17: exportXML();
				break;
				case 0: found = false;
				break;
				default: System.out.println("Not Found");
				break;			
			}			
		}		
	}
}
