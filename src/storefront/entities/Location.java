package storefront.entities;

public class Location {
	
	private int locationID;
	private String locationName;
	private double latitude;
	private double longitude;
	
	
	public int getLocationID() {
		return locationID;
	}
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Location [locationID=" + locationID + ", locationName=" + locationName + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
	

}
