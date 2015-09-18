package core.db.interfaces;

import java.util.List;

import core.tables.interfaces.PrivilegeToolFeatureAccess;
/**
 * The DBPrivilegeToolFeatureAccess interface.
 * 
 * @author Vasco
 *
 */
public interface DBPrivilegeToolFeatureAccess {
    /**
     * Get PrivilegeToolFeatureAccess by a privilegeId.
     * 
     * @param privilegeId
     * 
     * @return PrivilegeToolFeatureAccess List
     */
    public List<PrivilegeToolFeatureAccess> getPrivilegeToolFeatureAccessesByPrivilegeId(int privilegeId);

    /**
     * Create a new record with given information.
     * 
     * @param privilegeToolFeatureAccess PrivilegeToolFeatureAccess
     */
    public void createRecord(PrivilegeToolFeatureAccess privilegeToolFeatureAccess);
    
    /**
     * Create new records with given information.
     * 
     * @param privilegeToolFeatureAccessList List PrivilegeToolFeatureAccess
     */
    public void createRecords(List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccessList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param privilegeToolFeatureAccess PrivilegeToolFeatureAccess
     */
    public void updateRecord(PrivilegeToolFeatureAccess privilegeToolFeatureAccess);
    
    /**
     * Update existing records with given information.
     * 
     * @param privilegeToolFeatureAccess List PrivilegeToolFeatureAccess
     */
    public void updateRecords(List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccess);
}
