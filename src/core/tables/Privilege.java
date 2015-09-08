package core.tables;

import constants.PrivilegeStatus;
import core.DBTableCommon;

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
	 * The privilege status.
	 * 
	 * @return PrivilegeStatus
	 */
	public PrivilegeStatus getStatus();
	
	/**
	 * The privilege description.
	 * 
	 * @return String
	 */
	public String getDescription();
}
