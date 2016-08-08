package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import storefront.dao.ProductDAO;
import storefront.entities.Product;

public class SimulatorService {
	
	private static SimulatorService instance = null;
	protected SimulatorService() {
		// Exists only to defeat instantiation.
	}
	public static SimulatorService getInstance() {
		if(instance == null) {
			instance = new SimulatorService();
		}
		return instance;
	}
	
	
	private ProductDAO dao = new ProductDAO();

	public void readProductsFromFile(File file) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNext()){
			String line = in.nextLine();
			Product aProduct = parseStringToProduct(line);
			System.out.println("Product ID: #"+commitNewProduct(aProduct)+" added to DB");
		}
	}

	private Product parseStringToProduct(String productString){
		Product product = new Product();
		String[] attributes = productString.split(",");
		product.setProductID(Integer.parseInt(attributes[0]));
		product.setProductName(attributes[1]);
		product.setPrice(Float.parseFloat(attributes[2]));
		System.out.println(product.toString());
		return product;
		
	}
	public int commitNewProduct(Product p){
		return dao.insertNewProduct(p.getProductName(), p.getPrice());
	}
	
}
