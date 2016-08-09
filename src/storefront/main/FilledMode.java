package storefront.main;

import java.util.HashMap;

import storefront.entities.Customer;

public class FilledMode extends Simulation {
	
	@Override
	public void round(int roundIter){
		for (Customer customer: customerList){
			theSimService.randomlyAssignCustomerMachine(customer);
			System.out.println("*******" + customer.getName());
			HashMap<Integer,Integer> productsInMachine = theSimService.getProductsInMachine(customer.getCurrentMachineID());
			System.out.println(productsInMachine.toString());
			int productChosen = theSimService.chooseProduct(customer.getCustomerID());
			System.out.println("Product Chosen:" + productChosen);
			if (productsInMachine.containsKey(productChosen) && productsInMachine.get(productChosen)>=1){
				System.out.println("YES THE MACHINE HAS YOUR PRODUCT (and its stocked)!");
				recordTransaction(customer.getCustomerID(), productChosen, customer.getCurrentMachineID(), roundIter);
			}
			else {
				System.out.println("BOOOOOO! the machine doesn't have what you are looking for");
				recordRequest(customer.getCustomerID(), productChosen, customer.getCurrentMachineID(), roundIter);
			}
			
		}
	}

}
