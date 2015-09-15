package core.tables.interfaces;

import core.db.interfaces.TableCommon;

/**
 * The Feature interface.
 * 
 * @author Vasco
 * 
 */
public interface Feature extends TableCommon {
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
