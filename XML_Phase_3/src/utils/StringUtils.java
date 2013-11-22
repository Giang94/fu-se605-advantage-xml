package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import beans.Categories;
import beans.Database;
import beans.ObjectFactory;
import beans.Products;
import beans.Suppliers;

public class StringUtils {
	public static String connection_String = "jdbc:mysql://localhost:3306/xml?user=root&password=";

	public static String generateXML() throws ClassNotFoundException, SQLException, JAXBException {
		XMLGenerator gen = new XMLGenerator("root", "", "localhost:3306/xml");

		Categories cat = gen.getCategory();
		Suppliers sup = gen.getSupplier();
		Products pro = gen.getProducts();

		ObjectFactory factory = new ObjectFactory();
		Database db = factory.createDatabase();
		db.setName("northwind");
		db.setCategories(cat);
		db.setProducts(pro);
		db.setSuppliers(sup);

		JAXBContext context = JAXBContext.newInstance(Database.class);
		Marshaller marsal = context.createMarshaller();
		marsal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		File fileHandler = new File("Northwind.xml");

		marsal.marshal(db, fileHandler);
		// marsal.marshal(db, System.out);

		System.out.println("Convert to Northwind.xml completed!"
				+ fileHandler.getAbsolutePath());
		return fileHandler.getAbsolutePath();

	}

	public byte[] getBytesFromInputStream(InputStream inStream)
			throws IOException {

		// Get the size of the file
		long streamLength = inStream.available();

		if (streamLength > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) streamLength];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = inStream.read(bytes, offset, bytes.length
						- offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file ");
		}

		// Close the input stream and return bytes
		inStream.close();
		return bytes;
	}
}
