package core.db.interfaces;

import java.util.List;

import core.tables.interfaces.Privilege;
import core.tables.interfaces.PrivilegeTool;
import core.tables.interfaces.PrivilegeToolFeatureAccess;

/**
 * The DBPrivilege interface.
 * 
 * @author Vasco
 *
 */
public interface DBPrivilege {
	/**
	 * The loaded Privilege row.
	 * 
	 * @return Privilege
	 */
	public Privilege getPrivilege();
	
	/**
	 * The loaded list of all linked Privilege Tools.
	 * 
	 * @return List PrivilegeTool 
	 */
	public List<PrivilegeTool> getPrivilegeTools();
	
	/**
	 * The loaded list of all respective Privilege Tool Feature Access.
	 * 
	 * @return List PrivilegeToolFeatureAccess
	 */
	public List<PrivilegeToolFeatureAccess> getToolFeatureAccess();

    /**
     * Create a new record with given information.
     * 
     * @param privilege Privilege
     */
    public void createRecord(Privilege privilege);
    
    /**
     * Create a new record with given information.
     * 
     * @param privilegeTool PrivilegeTool
     */
    public void createRecord(PrivilegeTool privilegeTool);
    
    /**
     * Create a new record with given information.
     * 
     * @param privilegeToolFeatureAccess PrivilegeToolFeatureAccess
     */
    public void createRecord(PrivilegeToolFeatureAccess privilegeToolFeatureAccess);
    
    /**
     * Create new records with given information.
     * 
     * @param privilegeList List Privilege
     */
    public void createPrivilegeRecords(List<Privilege> privilegeList);
    
    /**
     * Create new records with given information.
     * 
     * @param privilegeToolList List PrivilegeTool
     */
    public void createPrivilegeToolRecords(List<PrivilegeTool> privilegeToolList);
    
    /**
     * Create new records with given information.
     * 
     * @param privilegeToolFeatureAccessList List PrivilegeToolFeatureAccess
     */
    public void createPrivilegeToolFeatureAccessRecords(List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccessList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param privilege Privilege
     */
    public void updateRecord(Privilege privilege);
    
    /**
     * Update an existing record with given information.
     * 
     * @param privilegeTool PrivilegeTool
     */
    public void updateRecord(PrivilegeTool privilegeTool);
    
    /**
     * Update an existing record with given information.
     * 
     * @param privilegeToolFeatureAccess PrivilegeToolFeatureAccess
     */
    public void updateRecord(PrivilegeToolFeatureAccess privilegeToolFeatureAccess);
    
    /**
     * Update existing records with given information.
     * 
     * @param privilege List Privilege
     */
    public void updatePrivilegeRecords(List<Privilege> privilege);

    /**
     * Update existing records with given information.
     * 
     * @param privilegeTool List PrivilegeTool
     */
    public void updatePrivilegeToolRecords(List<PrivilegeTool> privilegeTool);

    /**
     * Update existing records with given information.
     * 
     * @param privilegeToolFeatureAccess List PrivilegeToolFeatureAccess
     */
    public void updatePrivilegeToolFeatureAccessRecords(List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccess);
}
