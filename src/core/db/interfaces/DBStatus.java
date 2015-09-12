package core.db.interfaces;

import core.tables.interfaces.Status;
/**
 * The DBStatus interface.
 * 
 * @author Vasco
 *
 */
public interface DBStatus {
	/**
	 * Select a Status by status_cd
	 * 
	 * @param status_cd String
	 * @return Status
	 */
	public Status getStatus(String status_cd);

	/**
	 * Select a Status by id
	 * 
	 * @param id statusId
	 * @return Status
	 */
	public Status getStatus(int statusId);

}