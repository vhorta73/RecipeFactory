package core.tables.interfaces;


/**
 * The Privileges interface.
 * 
 * @author Vasco
 *
 */
public interface Privilege extends TableCommon {
	/**
	 * The auto-increment table unique id.
	 * 
	 * @return int
	 */
	public int getId();
	
	/**
	 * The privilege display name.
	 * 
	 * @return String
	 */
	public String getDisplayName();
	
	/**
	 * The privilege status id.
	 * 
	 * @return int 
	 */
	public int getStatusId();
	
	/**
	 * The privilege description.
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
