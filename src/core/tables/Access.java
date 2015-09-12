package core.tables;

import core.db.DBTableCommon;

/**
 * The Access interface.
 * 
 * @author Vasco
 * 
 */
public interface Access extends DBTableCommon {
	/**
	 * The primary id from the access table,
	 * 
	 * @return access id
	 */
	public int getId();
	
	/**
	 * The access code.
	 * 
	 * @return String
	 */
	public String getAccessCd();
	
	/**
	 * The access display name.
	 * 
	 * @return String
	 */
	public String getDisplayName();
	
	/**
	 * The access description.
	 * 
	 * @return String
	 */
	public String getDescription();
}
