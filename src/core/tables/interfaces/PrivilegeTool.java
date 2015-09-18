package core.tables.interfaces;


/**
 * The Privilege Tool interface.
 * 
 * @author Vasco
 *
 */
public interface PrivilegeTool extends TableCommon {
    /**
     * The auto-increment table unique id.
     * 
     * @return int
     */
    public int getId();
    
    /**
     * The privilege id.
     * 
     * @return int
     */
    public int getPrivilegeId();
    
    /**
     * The tool id.
     * 
     * @return int
     */
    public int getToolId();

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
