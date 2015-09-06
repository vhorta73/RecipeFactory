package core;

import constants.PrivilegeAccess;
import constants.PrivilegeStatus;
import constants.PrivilegeType;

/**
 * The Privileges interface.
 * 
 * @author Vasco
 *
 */
public interface Privilege extends DBTableCommon {
	/**
	 * The auto-increment table unique id.
	 * 
	 * @return int
	 */
	public int getId();
	
	/**
	 * The privilege name.
	 * 
	 * @return String
	 */
	public String getName();
	
	/**
	 * The privilege type.
	 * 
	 * @return PrivilegeType
	 */
	public PrivilegeType getType();
	
	/**
	 * The privilege status.
	 * 
	 * @return PrivilegeStatus
	 */
	public PrivilegeStatus getStatus();
	
	/**
	 * The privilege access.
	 * 
	 * @return PrivilegeAccess
	 */
	public PrivilegeAccess getAccess();
	
	/**
	 * The privilege description.
	 * 
	 * @return String
	 */
	public String getDescription();
}
