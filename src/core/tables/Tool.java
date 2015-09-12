package core.tables;

import core.db.DBTableCommon;

/**
 * The Tool interface.
 * 
 * @author Vasco
 * 
 */
public interface Tool extends DBTableCommon {
	/**
	 * The primary id from the tool table,
	 * 
	 * @return tool id
	 */
	public int getId();
	
	/**
	 * The tool code.
	 * 
	 * @return String
	 */
	public String getToolCd();
	
	/**
	 * The tool display name.
	 * 
	 * @return String
	 */
	public String getDisplayName();
	
	/**
	 * The tool description.
	 * 
	 * @return String
	 */
	public String getDescription();
}
