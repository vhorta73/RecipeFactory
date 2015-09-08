package core.tables;

import java.sql.Timestamp;

/**
 * The Privilege Tool implementation.
 * 
 * @author Vasco
 *
 */
public class PrivilegeToolImpl implements PrivilegeTool {
	/**
	 * The auto-increment id.
	 */
	public final int ID;
	
	/**
	 * The privilege id.
	 */
	public final int PRIVILEGE_ID;
	
	/**
	 * The tool code.
	 */
	public final String TOOL_CD;

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
	 * The Constructor.
	 * 
	 * @param id int
	 * @param privilegeId int
	 * @param toolCd String
	 * @param createdBy String
	 * @param createdDate Timestamp
	 * @param lastUpdatedBy String
	 * @param lastUpdatedDate Timestamp
	 */
	public PrivilegeToolImpl(int id, int privilegeId, String toolCd,
			String createdBy, Timestamp createdDate, 
			String lastUpdatedBy, Timestamp lastUpdatedDate  ) {
		this.ID = id;
		this.PRIVILEGE_ID = privilegeId;
		this.TOOL_CD = toolCd;
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
	public int 	getPrivilegeId() {
		return PRIVILEGE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getToolCd() {
		return TOOL_CD;
	}
}