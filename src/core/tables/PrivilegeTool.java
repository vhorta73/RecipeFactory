package core.tables;

import core.db.DBTableCommon;

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
	 * The tool code.
	 * 
	 * @return String
	 */
	public String getToolCd();
}
