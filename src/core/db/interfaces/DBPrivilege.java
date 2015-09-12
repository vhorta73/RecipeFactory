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

}
