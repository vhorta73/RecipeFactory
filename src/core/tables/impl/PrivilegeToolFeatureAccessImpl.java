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
    public final int PRIVILEGE_ID;
    
    /**
     * The tool id.
     */
    public final int TOOL_ID;
    
    /**
     * The feature id.
     */
    public final int FEATURE_ID;
    
    /**
     * The access id.
     */
    public final int ACCESS_ID;
    
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
     * The constructor.
     * 
     * @param privilegeId int
     * @param featureId int
     * @param accessId int
     * @param createdBy String
     * @param createdDate Timestamp
     * @param lastUpdatedBy String 
     * @param lastUpdatedDate Timestamp
     */
    public PrivilegeToolFeatureAccessImpl(int privilegeId, int toolId,
            int featureId, int accessId, boolean show, boolean deleted,
            String createdBy, Timestamp createdDate, 
            String lastUpdatedBy, Timestamp lastUpdatedDate  ) {
        this.PRIVILEGE_ID = privilegeId;
        this.TOOL_ID = toolId;
        this.FEATURE_ID = featureId;
        this.ACCESS_ID = accessId;
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
    public int getPrivilegeId() {
        return PRIVILEGE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getToolId() {
        return TOOL_ID;
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