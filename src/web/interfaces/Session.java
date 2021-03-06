package web.interfaces;

import mySQL.ConnectDB;
import core.interfaces.BEUserPrivilege;
import core.tables.interfaces.User;

/**
 * The Session interface.
 * 
 * @author Vasco
 *
 */
public interface Session {
	/**
	 * The DB handler to be set when log in is successful.
	 * 
	 * @param dbConnection ConnectDB
	 */
	public void setDB(ConnectDB dbConnection);
	
	/**
	 * The DB handler connection.
	 * 
	 * @return COnnectDB
	 */
	public ConnectDB getDB();
	
	/**
	 * True if logged in.
	 * 
	 * @return boolean
	 */
	public boolean isLoggedIn();
	
	/**
	 * True if session user has been validated.
	 * 
	 * @return boolean
	 */
	public boolean isUserValidated();
	
	/**
	 * Set user logged in status.
	 */
	public void setLoggedIn(boolean loginStatus);
	
	/**
	 * The loaded user details.
	 * 
	 * @return User
	 */
	public User getUser();

	/**
	 * Set the user details.
	 */
	public void setUser(User user);

	/**
	 * The loaded user privileges.
	 * 
	 * @return UserPrivileges
	 */
	public BEUserPrivilege getUserPrivileges();

	/**
	 * Set the user privileges details.
	 */
	public void setUserPrivileges(BEUserPrivilege userPrivilege);
}
