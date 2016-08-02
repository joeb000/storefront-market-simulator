package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

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

	public int productIDCounter = 1;

	private HashMap<Integer,Product> productsMap = new HashMap<Integer,Product>();
	
	public Product getProductsByID(int id){
		return productsMap.get(id);
	}
	
	public void addProduct(int id, Product p){
		productsMap.put(id, p);
	}
	
	
	public HashMap<Integer,Product> readProductsFromFile(File file) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNext()){
			String line = in.nextLine();
			addProduct(productIDCounter,parseStringToProduct(line));
			productIDCounter++;
		}

		return productsMap;
	}

	private Product parseStringToProduct(String productString){
		Product product = new Product();
		String[] attributes = productString.split(",");
		product.setProductID(productIDCounter);
		product.setProductName(attributes[0]);
		product.setPrice(Float.parseFloat(attributes[1]));
		System.out.println(product.toString());
		return product;
	}
	
	

}
