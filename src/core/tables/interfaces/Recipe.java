package core.tables.interfaces;


/**
 * The Recipe interface.
 * 
 * @author Vasco
 *
 */
public interface Recipe extends TableCommon {
	/**
	 * The auto-increment id.
	 * 
	 * @return int
	 */
	public int getId();
	
	/**
	 * The unique recipe name.
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * The recipe final quantity.
	 * 
	 * @return double
	 */
	public double getFinalQuantity();

	/**
	 * The final quantity unit.
	 * 
	 * @return String
	 */
	public String getUnit();

	/**
	 * The recipe description.
	 * 
	 * @return String
	 */
	public String getDescription();

	/**
	 * The recipe notes.
	 * 
	 * @return String
	 */
	public String getNotes();

	/**
	 * True if this record is showing.
	 * 
	 * @return true if showing
	 */
	public boolean isShow();

	/**
	 * True if this record is deleted.
	 * 
	 * @return true if deleted.
	 */
	public boolean isDeleted();
}
