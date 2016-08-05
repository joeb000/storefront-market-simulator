package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import storefront.dao.ProductDAO;
import storefront.entities.Customer;
import storefront.entities.Product;

public class ProductService {

	private static ProductService instance = null;
	protected ProductService() {
		// Exists only to defeat instantiation.
	}
	public static ProductService getInstance() {
		if(instance == null) {
			instance = new ProductService();
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

	public ArrayList<Product> retrieveAllProducts() {
		ArrayList<Product> productList = new ArrayList<Product>();

		//TODO
		productList = dao.readAllProductsFromDB();


		return productList;
	}



}
