package core.db.interfaces;

import java.util.List;

import core.tables.interfaces.Access;
/**
 * The DBAccess interface.
 * 
 * @author Vasco
 *
 */
public interface DBAccess {
    /**
     * Get an Access row by id.
     * 
     * @param accessId
     * @return Access
     */
    public Access getAccess(int accessId);

    /**
     * Get and Access row by access code.
     * 
     * @param accessCd
     * @return Access
     */
    public Access getAccess(String accessCd);
    
    /**
     * Create a new record with given information.
     * 
     * @param access Access
     */
    public void createRecord(Access access);
    
    /**
     * Create new records with given information.
     * 
     * @param accessList List Access
     */
    public void createRecords(List<Access> accessList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param access Access
     */
    public void updateRecord(Access access);
    
    /**
     * Update existing records with given information.
     * 
     * @param access List Access
     */
    public void updateRecords(List<Access> access);
}
