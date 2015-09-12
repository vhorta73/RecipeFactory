package core.tables.interfaces;

import core.db.interfaces.DBTableCommon;

/**
 * The Status interface.
 * 
 * @author Vasco
 * 
 */
public interface Status extends DBTableCommon {
	/**
	 * The primary id from the status table,
	 * 
	 * @return status id
	 */
	public int getId();
	
	/**
	 * The status code.
	 * 
	 * @return String
	 */
	public String getStatusCd();
	
	/**
	 * The status display name.
	 * 
	 * @return String
	 */
	public String getDisplayName();
	
	/**
	 * The description is an optional field where the user
	 * can say which type of ingredient it is if the name
	 * is not explicit enough.
	 * 
	 * @return String
	 */
	public String getDescription();
}
