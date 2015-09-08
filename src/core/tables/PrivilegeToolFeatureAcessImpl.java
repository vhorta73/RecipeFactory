package core.tables;

import java.sql.Timestamp;

/**
 * The Privilege Tool Feature Access implementation.
 * 
 * @author Vasco
 *
 */
public class PrivilegeToolFeatureAcessImpl implements
		PrivilegeToolFeatureAccess {
	/**
	 * The privilege tool id.
	 */
	public final int PRIVILEGE_TOOL_ID;
	
	/**
	 * The feature code.
	 */
	public final String FEATURE_CD;
	
	/**
	 * The access code.
	 */
	public final String ACCESS_CD;

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
	 * @param featureCd String
	 * @param accessCd String
	 * @param createdBy String
	 * @param createdDate Timestamp
	 * @param lastUpdatedBy String 
	 * @param lastUpdatedDate Timestamp
	 */
	public PrivilegeToolFeatureAcessImpl(int privilegeToolId,
			String featureCd, String accessCd,
			String createdBy, Timestamp createdDate, 
			String lastUpdatedBy, Timestamp lastUpdatedDate  ) {
		this.PRIVILEGE_TOOL_ID = privilegeToolId;
		this.FEATURE_CD = featureCd;
		this.ACCESS_CD = accessCd;
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
	public String getFeatureCd() {
		return FEATURE_CD;
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