package Model;

/**
 * Represents a landlord with landlord's name (first and last), phone number, email
 * rating, and account name.
 * 
 * @author Qing Bai
 * @version 08/06/2015
 */
public class Landlord {
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String email;
	private int rating;
	private String accountName;
	
	/**
	 * initialize a landlord instance with the given parameters
	 * 
	 * @param firstName is first name of a landlord
	 * @param lastName is last name of a landlord
	 * @param phoneNum is phone number of a landlord
	 * @param email is email address of a landlord
	 * @param rating is rating of a landlord
	 * @param accountName is account name of landlord
	 * @throws IllegalArgumentException when string values are empty or null, or
	 * when length of first name, last name, or account name is greater than 20, 
	 * or length of phone number is not equal to 10, or length of email is greater 
	 * than 50, or rating is not between -1 and 5. -1 is used to indicate that
	 * no one rates this landlord.
	 */
	public Landlord(String firstName, String lastName, String phoneNum, 
			String email, int rating, String accountName) {
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNum(phoneNum);
		setEmail(email);
		setRating(rating);
		setAccountName(accountName);
	}
	
	/**
	 * set first name of this landlord. 
	 * 
	 * @param firstName is first name of a landlord
	 * @throws IllegalArgumentException when first name is empty, null
	 * or its length is greater than 20
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.isEmpty() || firstName.length() > 20) {
			throw new IllegalArgumentException("Please enter a valid first name");
		}
		
		this.firstName = firstName;
	}
	
	/**
	 * return first name of this landlord.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * set last name of this landlord.
	 * 
	 * @param lastName is last name of a landlord
	 * @throws IllegalArgumentExeption when last name is empty, null
	 * or its length is greater than 20
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.isEmpty() || lastName.length() > 20) {
			throw new IllegalArgumentException("Please enter a valid last name");
		}
		
		this.lastName = lastName;
	}
	
	/**
	 * return last name of this landlord.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * set phone number of this landlord.
	 * 
	 * @param phoneNum is phone number of this landlord
	 * @throws IllegalArgumentException when phone number is empty, null
	 * or length is not equal to 10
	 */
	public void setPhoneNum(String phoneNum) {
		if (phoneNum == null || phoneNum.isEmpty() || phoneNum.length() != 10) {
			throw new IllegalArgumentException("Please enter a valid phone number");
		}
		
		this.phoneNum = phoneNum;
	}
	
	/**
	 * return phone number of this landlord.
	 * 
	 * @return phone number
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	
	/**
	 * set email of this landlord.
	 * 
	 * @param email is an email address of this landlord
	 * @throws IllegalArgumentException when email is empty, null
	 * or length is greater than 50
	 */
	public void setEmail(String email) {
		if (email == null || email.isEmpty() || email.length() > 50) {
			throw new IllegalArgumentException("Please use a valid email address");
		}
		
		this.email = email;
	}
	
	/**
	 * return email address of this landlord.
	 * 
	 * @return email address
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * set rating of this landlord. Default value is -1.
	 * -1 shows that no one has rated this landlord yet.
	 * 
	 * @param rating is rating of this landlord.
	 * @throws IllegalArgumentException when rating > 5 or rating < -1
	 */
	public void setRating(int rating) {
		if (rating < -1 || rating > 5) {
			throw new IllegalArgumentException("Please use a valid rating number");
		}
		
		this.rating = rating;
	}
	
	/**
	 * return rating of this landlord.
	 * 
	 * @return rating
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * set account name of this landlord.
	 * 
	 * @param accountName is account name of this landlord
	 * @throws IllegalArgumentException when account name is null, empty,
	 * or length is greater than 20
	 */
	public void setAccountName(String accountName) {
		if (accountName == null || accountName.isEmpty() || accountName.length() > 20) {
			throw new IllegalArgumentException("Please enter a valid account name");
		}
		
		this.accountName = accountName;
	}
	
	/**
	 * return account name of this landlord.
	 * 
	 * @return account name
	 */
	public String getAccountName() {
		return accountName;
	}
	
	@Override
	public String toString() {
		return "Landlord [firstName = " + firstName + ", lastName = " + lastName +
				", phoneNum = " + phoneNum + ", email = " + email + ", rating = " +
				rating + ", accountName = " + accountName + "]";
	}
}
