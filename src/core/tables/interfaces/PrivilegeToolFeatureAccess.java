package core.tables.interfaces;
/**
 * The Privilege Tool Feature Access interface.
 * 
 * @author Vasco
 *
 */
public interface PrivilegeToolFeatureAccess extends TableCommon {
    /**
     * The privilege id.
     * 
     * @return int
     */
    public int getPrivilegeId();
    
    /**
     * The feature id.
     * 
     * @return int
     */
    public int getFeatureId();
    
    /**
     * The tool id.
     * 
     * @return int
     */
    public int getToolId();
    
    /**
     * The access id.
     * 
     * @return int
     */
    public int getAccessId();

    /**
     * True if this record is deleted.
     * 
     * @return true if deleted.
     */
    public boolean isDeleted();

    /**
     * True if this record is showing.
     * 
     * @return true if showing
     */
    public boolean isShow();
}
