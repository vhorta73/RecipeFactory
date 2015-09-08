package core.tables;

import java.sql.Timestamp;

import constants.PrivilegeStatus;
/**
 * The Privilege implementation.
 * 
 * @author Vasco
 *
 */
public class PrivilegeImpl implements Privilege {
	/**
	 * The auto-increment id.
	 */
	public final int ID;
	
	/**
	 * The privilege name.
	 */
	public final String NAME;
	
	/**
	 * The privilege status.
	 */
	public final PrivilegeStatus STATUS;
	
	/**
	 * The privilege description.
	 */
	public final String DESCRIPTION;

	/**
	 * The username that created this privilege.
	 */
	private final String CREATED_BY;
	
	/**
	 * The date and time this record was created.
	 */
	private final Timestamp CREATED_DATE;
	
	/**
	 * The username that last updated this record.
	 */
	private final String LAST_UPDATED_BY;
	
	/**
	 * The date and time this record was last updated.
	 */
	private final Timestamp LAST_UPDATED_DATE;

	/**
	 * Constructor requested each column from the table to be given.
	 * 
	 * @param id 
	 * @param name
	 * @param status
	 * @param description
	 * @param createdBy
	 * @param createdDate
	 * @param lastUpdatedBy
	 * @param lastUpdatedDate
	 */
	public PrivilegeImpl(int id, String name, 
			String status, String description, 
			String createdBy, Timestamp createdDate, 
			String lastUpdatedBy, Timestamp lastUpdatedDate  ) {
		this.ID = id;
		this.NAME = name;
		this.DESCRIPTION = description;
		this.CREATED_BY = createdBy;
		this.CREATED_DATE = createdDate;
		this.LAST_UPDATED_BY = lastUpdatedBy;
		this.LAST_UPDATED_DATE = lastUpdatedDate;

		String upperStatus = status.toUpperCase();
		switch (upperStatus) {
		    case "ACTIVE"         : { this.STATUS = PrivilegeStatus.ACTIVE;         break; }
    		case "INACTIVE"       : { this.STATUS = PrivilegeStatus.INACTIVE;       break; }
    		default               : { throw new IllegalArgumentException("Unknown status: "+status); }
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCreatedBy() {
		return CREATED_BY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp getCreatedDate() {
		return CREATED_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLastUpdatedBy() {
		return LAST_UPDATED_BY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp getLastUpdatedDate() {
		return LAST_UPDATED_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getId() {
		return ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrivilegeStatus getStatus() {
		return STATUS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
