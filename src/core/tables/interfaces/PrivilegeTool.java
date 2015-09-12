package core.tables.interfaces;

import core.db.interfaces.DBTableCommon;

/**
 * The Privilege Tool interface.
 * 
 * @author Vasco
 *
 */
public interface PrivilegeTool extends DBTableCommon {
	/**
	 * The auto-increment table unique id.
	 * 
	 * @return int
	 */
	public int getId();
	
	/**
	 * The privilege id.
	 * 
	 * @return int
	 */
	public int getPrivilegeId();
	
	/**
	 * The tool id.
	 * 
	 * @return int
	 */
	public int getToolId();
}
