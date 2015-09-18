package core.db.interfaces;

import java.util.List;

import core.tables.interfaces.PrivilegeTool;
/**
 * The DBPrivilegeTool interface.
 * 
 * @author Vasco
 *
 */
public interface DBPrivilegeTool {
    /**
     * Get a PrivilegeTool row by id.
     * 
     * @param id
     * @return PrivilegeTool
     */
    public PrivilegeTool getPrivilegeToolById(int id);

    /**
     * Get a list of PrivilegeTool rows by privilegeId.
     * 
     * @param privilegeId
     * @return PrivilegeTool List
     */
    public List<PrivilegeTool> getPrivilegeToolByPrivilegeId(int privilegeId);

    /**
     * Create a new record with given information.
     * 
     * @param privilegeTool PrivilegeTool
     */
    public void createRecord(PrivilegeTool privilegeTool);
    
    /**
     * Create new records with given information.
     * 
     * @param privilegeToolList List PrivilegeTool
     */
    public void createRecords(List<PrivilegeTool> privilegeToolList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param privilegeTool PrivilegeTool
     */
    public void updateRecord(PrivilegeTool privilegeTool);
    
    /**
     * Update existing records with given information.
     * 
     * @param privilegeTool List PrivilegeTool
     */
    public void updateRecords(List<PrivilegeTool> privilegeTool);
}
