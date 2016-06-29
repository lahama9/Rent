package View;

/**
 * Static class that keeps track of the system user.
 * 
 * @author LA Hamaker - lahama9
 * @version 1
 * @date 10 AUG 2015
 */
final class LogStatus {

	public static final int GUEST = 0;
	public static final int TENANT = 1;
	public static final int LANDLORD = 2;
	
	//Initial conditions
	private static String accountName = ""; 
	private static int status = 0;
	
	//Required constructor.
	private LogStatus(){
		//empty
	}
	//Set the log in status and account type
	public static void setStatus(int i){
		status = i;
	}
	
	//Return the system status
	public static int getStatus(){
		return status;
	}
	
	//Return the account String
	public static String accountName(){
		return accountName;
	}
	
	//Set the account name of the user logged in
	public static void setAccountName(final String n){
		accountName = n;
	}
	
	public static String getAccountName(){
		return accountName;
	}
}
