package core.tables.impl;

import java.sql.Timestamp;

import core.tables.interfaces.Tool;

/**
 * The Tool implementation.
 * 
 * @author Vasco
 *
 */
public class ToolImpl implements Tool {
	/**
	 * The Auto-increment id.
	 */
	private final int ID;
	
	/**
	 * The Tool unique code.
	 */
	private final String TOOL_CD;
	
	/**
	 * The Tool display name.
	 */
	private final String DISPLAY_NAME;
	
	/**
	 * The Tool description.
	 */
	private final String DESCRIPTION;
		
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
	 * @param toolCd String
	 * @param displayName String
	 * @param description String
	 * @param createdBy String
	 * @param createdDate Timestamp
	 * @param lastUpdatedBy String 
	 * @param lastUpdatedDate Timestamp
	 */
	public ToolImpl(int id, String toolCd, String displayName, String description, 
			String createdBy, Timestamp createdDate, String lastUpdatedBy, Timestamp lastUpdatedDate ) {
		this.ID = id;
		this.TOOL_CD = toolCd;
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
	public String getToolCd() {
		return TOOL_CD;
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
