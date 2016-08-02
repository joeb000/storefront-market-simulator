package storefront.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

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

	public int locationIDCounter = 1;

	private ArrayList<Location> locationList = new ArrayList<Location>();

	public void addLocation(Location loc){
		locationList.add(loc);
	}
	
	public ArrayList<Location> getLocationList(){
		return locationList;
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
		String[] attributes = locationString.split(",");
		location.setLocationID(locationIDCounter);
		location.setLocationName(attributes[0]);
		location.setLatitude(Double.parseDouble(attributes[1]));
		location.setLongitude(Double.parseDouble(attributes[2]));
		System.out.println(location.toString());
		return location;
	}

}
