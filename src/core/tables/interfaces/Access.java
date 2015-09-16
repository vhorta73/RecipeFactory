package core.tables.interfaces;


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
