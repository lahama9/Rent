package Model;
import java.sql.Date;

/**
 * Represents a request with address (street, city, state, zip code), request
 * detail, post date, ID of tenant who posts this request.
 * 
 * @author Qing Bai
 * @version 08/06/2015
 */
public class Request {
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String detail;
	private Date postDate;
	private int tenantID;
	
	/**
	 * initialize a request instance with the given parameters.
	 * 
	 * @param street is street 
	 * @param city is city
	 * @param state is state
	 * @param zipCode is zip code
	 * @param detail is detail of request
	 * @param postDate is the date when this request is posted
	 * @param tenantID is id of tenant who posts request
	 * @throws IllegalArgumentException when city, state, or zipCode is null or empty.
	 * But street and detail can be null but not empty. Exception will also be thrown  
	 * when length of street is greater than 50, length of city and state is greater 
	 * than 20, length of zip code is greater than 5, length of detail is greater than
	 * 200, postDate is null, or tenantID < 0
	 */
	public Request(String street, String city, String state, String zipCode, 
			String detail, Date postDate, int tenantID) {
		setStreet(street);
		setCity(city);
		setState(state);
		setZipCode(zipCode);
		setDetail(detail);
		setPostDate(postDate);
		setTenantID(tenantID);
	}
	
	/**
	 * set street of this request.
	 * 
	 * @param street is street of this request
	 * @throws IllegalArgumentException when street empty
	 * or length is greater than 50
	 */
	public void setStreet(String street) {
		if (street != null && (street.isEmpty() || street.length() > 50)) {
			throw new IllegalArgumentException("Please enter a valid street");
		}
		
		this.street = street;
	}
	
	/**
	 * return street of this request.
	 * 
	 * @return street
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * set city of this request.
	 * 
	 * @param city is city of this request
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
	 * return city of this request.
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * set state of this request.
	 * 
	 * @param state is state of this request
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
	 * return state of this request.
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * set zip code of this request.
	 * 
	 * @param zipCode is zip code of this request
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
	 * return zip code of this request.
	 * 
	 * @return zip code
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * set detail of a request.
	 * 
	 * @param detail is request detail of a tenant
	 * @throws IllegalArgumentException when detail is empty or
	 * length is greater than 200
	 */
	public void setDetail(String detail) {
		if (detail != null && (detail.isEmpty() || detail.length() > 200)) {
			throw new IllegalArgumentException("Please enter a valid detail");
		}
		
		this.detail = detail;
	}
	
	/**
	 * return detail of this request.
	 * 
	 * @return detail
	 */
	public String getDetail() {
		return detail;
	}
	
	/**
	 * set post date of this request.
	 * 
	 * @param postDate is post date
	 * @throws IllegalArgumentException when postDate is null
	 */
	public void setPostDate(Date postDate) {
		if (postDate == null) {
			throw new IllegalArgumentException("Please enter a date");
		}
		
		this.postDate = postDate;
	}
	
	/**
	 * return post date of this request.
	 * 
	 * @return post date
	 */
	public Date getPostDate() {
		return postDate;
	}
	
	/**
	 * set ID of tenant of this request.
	 * 
	 * @param tenantID is ID of tenant
	 * @throws IllegalArgumentException when tenantID < 0
	 */
	public void setTenantID(int tenantID) {
		if (tenantID < 0) {
			throw new IllegalArgumentException("Please enter a valid tenant ID");
		}
		
		this.tenantID = tenantID;
	}
	
	/**
	 * return ID of tenant of this request.
	 * 
	 * @return ID of tenant
	 */
	public int getTenantID() {
		return tenantID;
	}
	
	@Override
	public String toString() {
		return "Request [street = " + street + ", city = " + city + ", state = " + state +
				", zip code = " + zipCode + ", detail = " + detail + ", postDate = " + postDate + 
				", tenantID = " + tenantID + "]";
	}
}

