package core.tables.interfaces;


/**
 * The User interface.
 * 
 * @author Vasco
 *
 */
public interface User extends TableCommon {
	/**
	 * The primary id from the User table,
	 * used mostly internally for efficiency on updating
	 * or searching.
	 * 
	 * @return int user id
	 */
	public int getId();
	
	/**
	 * The username, unique key.
	 * 
	 * @return String username
	 */
	public String getUsername();
	
	/**
	 * The hashed password set against this username.
	 * 
	 * @return String hashed password
	 */
	public byte[] getPassword();
	
	/**
	 * The privilege id referring to the privileges
	 * this user has across the system.
	 * 
	 * @return int
	 */
	public int getPrivilegeId();
	
	/**
	 * The user status, which could be of many values.
	 * Please see UserStatus
	 * 
	 * @return UserStatus
	 */
	public String getStatus();
}
