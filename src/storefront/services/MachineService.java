package storefront.services;

import java.util.ArrayList;

import storefront.entities.Location;
import storefront.entities.Machine;
import storefront.entities.Product;


public class MachineService {
	private static MachineService instance = null;
	protected MachineService() {
		// Exists only to defeat instantiation.
	}
	public static MachineService getInstance() {
		if(instance == null) {
			instance = new MachineService();
		}
		return instance;
	}
	public int defaultMachineCapacity = 4;
	public int machineIDCounter = 1;
	private ArrayList<Machine> machineList = new ArrayList<Machine>();
	LocationService locationService = LocationService.getInstance();



	public void createOneMachinePerLocation(){
		//System.out.println(locationService.getLocationList().size());
		for (Location loc : locationService.getLocationList()) {
			Machine m = new Machine();
			m.setMachineID(machineIDCounter);
			m.setMachineLocation(loc);
			m.setCapacity(defaultMachineCapacity);
			machineList.add(m);
			locationService.addMachineIDToLocation(loc,machineIDCounter);
//			for (Product p : loc.getProductList()){
//				m.addProductUnits(p.getProductID(), 2);
			machineIDCounter++;

//			}
			this.stockProducts(m);
			System.out.println(m.toString());
		}
	}

	public Machine getMachineByID(int mID){
		for (Machine m : machineList){
			if(m.getMachineID()==mID){
				return m;
			}
		}
		return null;
	}
	
	public void stockProducts(Machine m){
		for (Product p : m.getMachineLocation().getProductList()){
			m.addProductUnits(p.getProductID(), 2);

		}
	}

}
