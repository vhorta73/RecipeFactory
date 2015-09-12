package core.tables.interfaces;

import core.db.interfaces.DBTableCommon;

/**
 * The Feature interface.
 * 
 * @author Vasco
 * 
 */
public interface Feature extends DBTableCommon {
	/**
	 * The primary id from the feature table,
	 * 
	 * @return feature id
	 */
	public int getId();
	
	/**
	 * The feature code.
	 * 
	 * @return String
	 */
	public String getFeatureCd();
	
	/**
	 * The feature display name.
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
