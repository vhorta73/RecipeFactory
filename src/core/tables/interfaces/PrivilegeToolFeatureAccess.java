package core.tables.interfaces;


/**
 * The Privilege Tool Feature Access interface.
 * 
 * @author Vasco
 *
 */
public interface PrivilegeToolFeatureAccess extends TableCommon {
	/**
	 * The privilege tool id.
	 * 
	 * @return int
	 */
	public int getPrivilegeToolId();
	
	/**
	 * The feature id.
	 * 
	 * @return int
	 */
	public int getFeatureId();
	
	/**
	 * The access id.
	 * 
	 * @return int
	 */
	public int getAccessId();
}
