package core.tables.interfaces;


/**
 * The Status interface.
 * 
 * @author Vasco
 * 
 */
public interface Status extends TableCommon {
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
