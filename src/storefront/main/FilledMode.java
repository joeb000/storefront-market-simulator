package storefront.main;

import java.util.HashMap;

import org.apache.log4j.Logger;

import storefront.entities.Customer;

public class FilledMode extends Simulation {
	
	final static Logger log = Logger.getLogger(FilledMode.class);

	
	@Override
	public void round(int roundIter){
		for (Customer customer: customerList){
			theSimService.randomlyAssignCustomerMachine(customer);
			log.info("*******" + customer.getName());
			HashMap<Integer,Integer> productsInMachine = theSimService.getProductsInMachine(customer.getCurrentMachineID());
			log.info(productsInMachine.toString());
			int productChosen = theSimService.chooseProduct(customer.getCustomerID());
			log.info("Product Chosen:" + productChosen);
			if (productsInMachine.containsKey(productChosen) && productsInMachine.get(productChosen)>=1){
				log.info("YES THE MACHINE HAS YOUR PRODUCT (and its stocked)!");
				recordTransaction(customer.getCustomerID(), productChosen, customer.getCurrentMachineID(), roundIter);
			}
			else {
				log.info("BOOOOOO! the machine doesn't have what you are looking for");
				recordRequest(customer.getCustomerID(), productChosen, customer.getCurrentMachineID(), roundIter);
			}
			
		}
	}

}
