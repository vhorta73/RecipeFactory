package core;

import java.sql.Timestamp;

/**
 * All the columns that should exist on all tables are added here.
 * This is to have one single point of entry to require them all to
 * exist and should make use of abstract methods case new ones are
 * introduced.
 * 
 * @author Vasco
 *
 */
public interface DBTableCommon {
	/**
	 * The user responsible for the creation of this
	 * record on the database.
	 * 
	 * @return String username
	 */
	public String getCreatedBy();
	
	/**
	 * The date when the user created this record
	 * on the database.
	 * 
	 * @return Timestamp
	 */
	public Timestamp getCreatedDate();
	
	/**
	 * The user that did the last update
	 * to this record on the database.
	 * 
	 * @return String username
	 */
	public String getLastUpdatedBy();

	/**
	 * The date and time when the user made 
	 * the last update on this record on the database.
	 * 
	 * @return Timestamp
	 */
	public Timestamp getLastUpdatedDate();
}
