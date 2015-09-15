package core.db.interfaces;

import java.util.List;

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

    /**
     * Create a new record with given information.
     * 
     * @param user User
     */
    public void createRecord(User user);
    
    /**
     * Create new records with given information.
     * 
     * @param userList User Access
     */
    public void createRecords(List<User> userList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param user User
     */
    public void updateRecord(User user);
    
    /**
     * Update existing records with given information.
     * 
     * @param user List User
     */
    public void updateRecords(List<User> user);
}