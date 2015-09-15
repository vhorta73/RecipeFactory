package core.db.interfaces;

import java.util.List;

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

    /**
     * Create a new record with given information.
     * 
     * @param status Status
     */
    public void createRecord(Status status);
    
    /**
     * Create new records with given information.
     * 
     * @param statusList List Status
     */
    public void createRecords(List<Status> statusList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param status Status
     */
    public void updateRecord(Status status);
    
    /**
     * Update existing records with given information.
     * 
     * @param status List Status
     */
    public void updateRecords(List<Status> status);
}