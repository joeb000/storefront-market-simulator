package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import storefront.entities.Location;
import storefront.entities.Machine;

public class LocationService {

	private static LocationService instance = null;
	protected LocationService() {
		// Exists only to defeat instantiation.
	}
	public static LocationService getInstance() {
		if(instance == null) {
			instance = new LocationService();
		}
		return instance;
	}

	public int locationIDCounter = 1;

	private ArrayList<Location> locationList = new ArrayList<Location>();

	public void addLocation(Location loc){
		locationList.add(loc);
	}
	
	public ArrayList<Location> getLocationList(){
		return locationList;
	}
	
	public Location getLocationByID(int id){
		for (Location l : locationList){
			if(l.getLocationID()==id){
				return l;
			}
		}
		return null;
	}


	public ArrayList<Location> readLocationsFromFile(File file) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNext()){
			String line = in.nextLine();
			addLocation(parseStringToLocation(line));
			locationIDCounter++;
		}

		return locationList;
	}

	private Location parseStringToLocation(String locationString){
		Location location = new Location();
		String[] split = locationString.split("\"");
		String[] attributes = split[0].split(",");
		String[] pList = split[1].split(",");
		location.setLocationID(Integer.parseInt(attributes[0]));
		location.setLocationName(attributes[1]);
		location.setLatitude(Double.parseDouble(attributes[2]));
		location.setLongitude(Double.parseDouble(attributes[3]));
		for (int i = 0; i < pList.length; i++) {
			location.addProuduct(ProductService.getInstance().getProductsByID(Integer.parseInt(pList[i])));
		}
		System.out.println(location.toString());
		return location;
	}
	
	public void addMachineIDToLocation(Location l, int mID){
		l.addMachineID(mID);
	}

}
