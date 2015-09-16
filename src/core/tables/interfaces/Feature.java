package core.tables.interfaces;


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

	/**
	 * True if this record is deleted.
	 * 
	 * @return true if deleted.
	 */
	public boolean isDeleted();

	/**
	 * True if this record is showing.
	 * 
	 * @return true if showing
	 */
	public boolean isShow();
}
