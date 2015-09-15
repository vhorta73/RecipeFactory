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
	public int getStatus();
	
	/**
	 * The privilege description.
	 * 
	 * @return String
	 */
	public String getDescription();
}
