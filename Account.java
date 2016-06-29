package Model;


/**
 * Represents an account with an account name, password, account type
 * and an attribute showing whether or not the account is in use.
 * 
 * @author Qing Bai
 * @Version 08/06/2015
 */
public class Account {
	private String name;
	private String password;
	private String type;
	private boolean inUse;
	
	/**
	 * Initialize an account instance with the given parameters.
	 * 
	 * @param name is user name of this account
	 * @param password is password of this account
	 * @param type is type of this account (landlord or tenant)
	 * @param inUse shows whether or not this account is active.
	 * @throw IllegalArgumentException whenever length of user name or password
	 * is longer than 20, length of user type is greater than 10 or any of them 
	 * is null or empty. Also when type is neither landlord or tenant.
	 */
	public Account(String name, String password, String type, boolean inUse) {
		setName(name);
		setPassword(password);
		setType(type);
		setInUse(inUse);
	}
	
	/**
	 * set the account name.
	 * 
	 * @param name is a given account name
	 * @throws IllegalArgumentException when the given name is null, empty, or its length
	 * is greater than 20
	 */
	public void setName(String name) {
		if (name == null || name.isEmpty() || name.length() > 20) {
			throw new IllegalArgumentException("Please enter a valid user name");
		}
		
		this.name = name;
	}
	
	/**
	 * return name of this account.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set the password of this account.
	 * 
	 * @param password is a given password
	 * @throws IllegalArgumentException when the given password is null, empty, or 
	 * its length is greater than 20
	 */
	public void setPassword(String password) {
		if (password == null || password.isEmpty() || password.length() > 20) {
			throw new IllegalArgumentException("Please enter a valid password");
		}
		
		this.password = password;
	}
	
	/**
	 * return password of this account.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * set the type of this account.
	 * 
	 * @param type is a type of account (either landlord or tenant)
	 * @throws IllegalArgumentException when type is neither landlord or tenant.
	 * Also when type is null, empty or its length is greater than 10.
	 */
	public void setType(String type) {
		if (type == null || type.isEmpty() || type.length() > 10 ||
				(!type.equalsIgnoreCase("Landlord") && !type.equalsIgnoreCase("Tenant"))) {
			throw new IllegalArgumentException("Please use a valid account type");
		}
		
		this.type = type;
	}
	
	/**
	 * return type of this account.
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * set whether or not this account is in use.
	 * 
	 * @param inUse is a boolean value showing whether or not this account
	 * is in use
	 */
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	
	/**
	 * return a boolean value showing whether or not this account is in use.
	 * 
	 * @return true if this account is in use. Otherwise, false
	 */
	public boolean getInUse() {
		return inUse;
	}
	
	@Override
	public String toString() {
		return "Account [accountName = " + name + ", accountPassword = " + password +
				", accountType = " + type + ", accountInUse = " + inUse + "]";
	}
}
