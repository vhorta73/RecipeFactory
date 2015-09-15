package core.tables.impl;

import java.sql.Timestamp;

import core.tables.interfaces.PrivilegeToolFeatureAccess;

/**
 * The Privilege Tool Feature Access implementation.
 * 
 * @author Vasco
 *
 */
public class PrivilegeToolFeatureAccessImpl implements PrivilegeToolFeatureAccess {
	/**
	 * The privilege tool id.
	 */
	public final int PRIVILEGE_TOOL_ID;
	
	/**
	 * The feature id.
	 */
	public final int FEATURE_ID;
	
	/**
	 * The access id.
	 */
	public final int ACCESS_ID;

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
	 * The constructor.
	 * 
	 * @param privilegeToolId int
	 * @param featureId int
	 * @param accessId int
	 * @param createdBy String
	 * @param createdDate Timestamp
	 * @param lastUpdatedBy String 
	 * @param lastUpdatedDate Timestamp
	 */
	public PrivilegeToolFeatureAccessImpl(int privilegeToolId,
			int featureId, int accessId,
			String createdBy, Timestamp createdDate, 
			String lastUpdatedBy, Timestamp lastUpdatedDate  ) {
		this.PRIVILEGE_TOOL_ID = privilegeToolId;
		this.FEATURE_ID = featureId;
		this.ACCESS_ID = accessId;
		this.CREATED_BY = createdBy;
		this.CREATED_DATE = createdDate;
		this.LAST_UPDATED_BY = lastUpdatedBy;
		this.LAST_UPDATED_DATE = lastUpdatedDate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPrivilegeToolId() {
		return PRIVILEGE_TOOL_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getFeatureId() {
		return FEATURE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAccessId() {
		return ACCESS_ID;
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


}