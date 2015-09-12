package core.tables.impl;

import java.sql.Timestamp;

import core.tables.interfaces.Privilege;
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
	 * The privilege display name.
	 */
	public final String DISPLAY_NAME;
	
	/**
	 * The privilege status id.
	 */
	public final int STATUS;
	
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
	 * @param display_name
	 * @param status_id
	 * @param description
	 * @param createdBy
	 * @param createdDate
	 * @param lastUpdatedBy
	 * @param lastUpdatedDate
	 */
	public PrivilegeImpl(int id, String display_name, 
			int status_id, String description, 
			String createdBy, Timestamp createdDate, 
			String lastUpdatedBy, Timestamp lastUpdatedDate  ) {
		this.ID = id;
		this.DISPLAY_NAME = display_name;
		this.DESCRIPTION = description;
		this.CREATED_BY = createdBy;
		this.CREATED_DATE = createdDate;
		this.LAST_UPDATED_BY = lastUpdatedBy;
		this.LAST_UPDATED_DATE = lastUpdatedDate;
        this.STATUS = status_id;
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
	public String getDisplayName() {
		return DISPLAY_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getStatus() {
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
