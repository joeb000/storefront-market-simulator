package storefront.services;

import java.util.ArrayList;

import storefront.entities.Location;
import storefront.entities.Machine;


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
	
	public int machineIDCounter = 0;
	private ArrayList<Machine> machineList = new ArrayList<Machine>();
	LocationService locationService = LocationService.getInstance();


	public void createOneMachinePerLocation(){
		for (Location loc : locationService.getLocationList()) {
			Machine m = new Machine();
			m.setMachineID(machineIDCounter);
			m.setMachineLocation(loc);
			machineList.add(m);
			machineIDCounter++;
			System.out.println(m.toString());
		}
	}

}
