package core.db;

import core.tables.interfaces.User;
/**
 * The DBUser interface.
 * 
 * @author Vasco
 *
 */
public interface DBUser {
	/**
	 * Return the loaded user
	 * 
	 * @return User
	 */
	public User getUser();

	/**
	 * save the given password into this user records.
	 * 
	 * @param password
	 */
	public void savePassword(byte[] password);

}