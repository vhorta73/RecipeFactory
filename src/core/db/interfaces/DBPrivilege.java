package core.db.interfaces;

import java.util.List;

import core.tables.interfaces.Privilege;
/**
 * The DBPrivilege interface.
 * 
 * @author Vasco
 *
 */
public interface DBPrivilege {
    /**
     * Get an Privilege row by id.
     * 
     * @param privilegeId
     * @return Privilege
     */
    public Privilege getPrivilege(int privilegeId);

    /**
     * Create a new record with given information.
     * 
     * @param privilege Privilege
     */
    public void createRecord(Privilege privilege);
    
    /**
     * Create new records with given information.
     * 
     * @param privilegeList List Privilege
     */
    public void createRecords(List<Privilege> privilegeList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param privilege Privilege
     */
    public void updateRecord(Privilege privilege);
    
    /**
     * Update existing records with given information.
     * 
     * @param privilege List Privilege
     */
    public void updateRecords(List<Privilege> privilege);
}
