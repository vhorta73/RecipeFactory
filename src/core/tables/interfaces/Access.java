package core.tables.interfaces;

import core.db.interfaces.TableCommon;

/**
 * The Access interface.
 * 
 * @author Vasco
 * 
 */
public interface Access extends TableCommon {
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
