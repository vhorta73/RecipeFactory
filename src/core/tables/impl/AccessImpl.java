package core.tables.impl;

import java.sql.Timestamp;

import core.tables.interfaces.Access;

/**
 * The Access implementation.
 * 
 * @author Vasco
 *
 */
public class AccessImpl implements Access {
	/**
	 * The Auto-increment id.
	 */
	private final int ID;
	
	/**
	 * The Access unique code.
	 */
	private final String ACCESS_CD;
	
	/**
	 * The Access display name.
	 */
	private final String DISPLAY_NAME;
	
	/**
	 * The Feature description.
	 */
	private final String DESCRIPTION;
		
	/**
	 * Show or no show flag.
	 */
	private final boolean SHOW;
		
	/**
	 * The deleted flag.
	 */
	private final boolean DELETED;
		
	/**
	 * The username that created this record.
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
	 * Constructor.
	 * 
	 * @param id int
	 * @param accessCd String
	 * @param displayName String
	 * @param description String
	 * @param show Boolean
	 * @param deleted Boolean
	 * @param createdBy String
	 * @param createdDate Timestamp
	 * @param lastUpdatedBy String
	 * @param lastUpdatedDate Timestamp
	 */
	public AccessImpl(int id, String accessCd, String displayName, String description, boolean show, boolean deleted,
			String createdBy, Timestamp createdDate, String lastUpdatedBy, Timestamp lastUpdatedDate ) {
		this.ID = id;
		this.ACCESS_CD = accessCd;
		this.DISPLAY_NAME = displayName;
		this.DESCRIPTION = description;
		this.SHOW = show;
		this.DELETED = deleted;
		this.CREATED_BY = createdBy;
		this.CREATED_DATE = createdDate;
		this.LAST_UPDATED_BY = lastUpdatedBy;
		this.LAST_UPDATED_DATE = lastUpdatedDate;
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
	public String getAccessCd() {
		return ACCESS_CD;
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
	public String getDescription() {
		return DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDeleted() {
		return DELETED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isShow() {
		return SHOW;
	}
}
