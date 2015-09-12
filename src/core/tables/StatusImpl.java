package core.tables;

import java.sql.Timestamp;

/**
 * The Status implementation.
 * 
 * @author Vasco
 *
 */
public class StatusImpl implements Status {
	/**
	 * The Auto-increment id.
	 */
	private final int ID;
	
	/**
	 * The Status unique code.
	 */
	private final String STATUS_CD;
	
	/**
	 * The Status display code.
	 */
	private final String DISPLAY_NAME;
	
	/**
	 * The Ingredient description.
	 */
	private final String DESCRIPTION;
		
	/**
	 * The username that created this ingredient.
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
	 * This constructor requires all the table database columns
	 * that matter for the system, which must be always supplied
	 * and the construction time.
	 * 
	 * @param id int auto increment
	 * @param name of the ingredient
	 * @param description for the ingredient
	 * @param notes on the ingredient
	 * @param createdBy username
	 * @param createdDate String date time
	 * @param lastUpdatedBy username
	 * @param lastUpdatedDate String date time
	 */
	public StatusImpl(int id, String status_cd, String displayName, String description, 
			String createdBy, Timestamp createdDate, String lastUpdatedBy, Timestamp lastUpdatedDate ) {
		this.ID = id;
		this.STATUS_CD = status_cd;
		this.DISPLAY_NAME = displayName;
		this.DESCRIPTION = description;
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
	public String getStatusCd() {
		return STATUS_CD;
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
}
