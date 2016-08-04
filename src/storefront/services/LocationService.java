package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import storefront.dao.LocationDAO;
import storefront.entities.Location;

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

	private ArrayList<Location> locationList = new ArrayList<Location>();
	private LocationDAO dao = new LocationDAO();
	public int defaultMachineCapacity = 4;


	public Location getLocationByID(int id){
		for (Location l : locationList){
			if(l.getLocationID()==id){
				return l;
			}
		}
		return null;
	}

	public void readLocationsFromFile(File file) throws FileNotFoundException{
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNext()){
			String line = in.nextLine();
			Location loc = parseStringToLocation(line);
			int locationid = commitNewLocation(loc);
			System.out.println("New Location "+ loc.getLocationName() + "(#"+locationid + " ) added to DB");
		}
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
//		for (int i = 0; i < pList.length; i++) {
//			location.addProuduct(ProductService.getInstance().getProductsByID(Integer.parseInt(pList[i])));
//		}
		return location;
	}
	
	public void addMachineIDToLocation(Location l, int mID){
		l.addMachineID(mID);
	}
	

	
	public int commitNewLocation(Location l){
		return dao.insertNewLocation(l.getLocationName(),l.getLatitude(),l.getLongitude());
	}

}
