package Model;

/**
 * Represents a property with address (street, city, state, zip code), type (
 * apartment or house), rental rate, and its owner.
 * 
 * @author Qing Bai
 * @version 08/06/2015
 */

public class Property {
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String type;
	private double rate;
	private int landlordID;
	
	/**
	 * initialize a property instance with the given parameters.
	 * 
	 * @param street is street 
	 * @param city is city
	 * @param state is state
	 * @param zipCode is zip code
	 * @param type is type of this property (either apartment or house)
	 * @param rate is rental rate
	 * @param landlordID is id of owner of this property
	 * @throws IllegalArgumentException when string values are null or empty, 
	 * length of street is greater than 50, length of city and state is greater 
	 * than 20, length of zip code is greater than 5, length of type is greater 
	 * than 10, rate < 0 or landlordID < 0
	 */
	public Property(String street, String city, String state, String zipCode, 
			String type, double rate, int landlordID) {
		setStreet(street);
		setCity(city);
		setState(state);
		setZipCode(zipCode);
		setType(type);
		setRate(rate);
		setLandlordID(landlordID);
	}
	
	/**
	 * set street of this property.
	 * 
	 * @param street is street of this property
	 * @throws IllegalArgumentException when street is null, empty,
	 * or length is greater than 50
	 */
	public void setStreet(String street) {
		if (street == null || street.isEmpty() || street.length() > 50) {
			throw new IllegalArgumentException("Please enter a valid street");
		}
		
		this.street = street;
	}
	
	/**
	 * return street of this property.
	 * 
	 * @return street
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * set city of this property.
	 * 
	 * @param city is city of this property
	 * @throws IllegalArgumentException when city is null, empty,
	 * or length is greater than 20
	 */
	public void setCity(String city) {
		if (city == null || city.isEmpty() || city.length() > 20) {
			throw new IllegalArgumentException("Please enter a valid city");
		}
		
		this.city = city;
	}
	
	/**
	 * return city of this property.
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * set state of this property.
	 * 
	 * @param state is state of this property
	 * @throws IllegalArgumentException when state is null, empty,
	 * or length is greater than 20
	 */
	public void setState(String state) {
		if (state == null || state.isEmpty() || state.length() > 20) {
			throw new IllegalArgumentException("Please enter a valid state");
		}
		
		this.state = state;
	}
	
	/**
	 * return state of this property.
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * set zip code of this property.
	 * 
	 * @param zipCode is zip code of this property
	 * @throws IllegalArgumentException when zip code is null, empty,
	 * or length is greater than 5
	 */
	public void setZipCode(String zipCode) {
		if (zipCode == null || zipCode.isEmpty() || zipCode.length() > 5) {
			throw new IllegalArgumentException("Please enter a valid zip code");
		}
		
		this.zipCode = zipCode;
	}
	
	/**
	 * return zip code of this property.
	 * 
	 * @return zip code
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * set type of this property.
	 * 
	 * @param type is type of this property (either apartment or house)
	 * @throws IllegalArgumentException when type is null, empty, length
	 * of type is greater than 10, or type does not match either house or
	 * apartment
	 */
	public void setType(String type) {
		if (type == null || type.isEmpty() || type.length() > 10 ||
				(!type.equalsIgnoreCase("House") && !type.equalsIgnoreCase("Apartment"))) {
			throw new IllegalArgumentException("Please enter a valid property type");
		}
		
		this.type = type;
	}
	
	/**
	 * return type of this property
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * set rental rate of this property.
	 * 
	 * @param rate is rental rate
	 * @throws IllegalArgumentException when rate is less than 0
	 */
	public void setRate(double rate) {
		if (rate < 0) {
			throw new IllegalArgumentException("Please enter a valid rate");
		}
		
		this.rate = rate;
	}
	
	/**
	 * return rental rate of this property.
	 * 
	 * @return rate
	 */
	public double getRate() {
		return rate;
	}
	
	/**
	 * set ID of landlord of this property.
	 * 
	 * @param landlordID is ID of landlord
	 * @throws IllegalArgumentException when landlordID < 0
	 */
	public void setLandlordID(int landlordID) {
		if (landlordID < 0) {
			throw new IllegalArgumentException("Please enter a valid landlord ID");
		}
		
		this.landlordID = landlordID;
	}
	
	/**
	 * return ID of landlord of this property.
	 * 
	 * @return ID of landlord
	 */
	public int getLandlordID() {
		return landlordID;
	}
	
	@Override
	public String toString() {
		return "Property [street = " + street + ", city = " + city + ", state = " + state +
				", zip code = " + zipCode + ", type = " + type + ", rate = " + rate + 
				", landlordID = " + landlordID + "]";
	}
}
