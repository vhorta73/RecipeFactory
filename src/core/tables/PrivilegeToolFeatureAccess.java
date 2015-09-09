package core.tables;

import core.db.DBTableCommon;

/**
 * The Privilege Tool Feature Access interface.
 * 
 * @author Vasco
 *
 */
public interface PrivilegeToolFeatureAccess extends DBTableCommon {
	/**
	 * The privilege tool id.
	 * 
	 * @return int
	 */
	public int getPrivilegeToolId();
	
	/**
	 * The feture code.
	 * 
	 * @return String
	 */
	public String getFeatureCd();
	
	/**
	 * The access code.
	 * 
	 * @return String
	 */
	public String getAccessCd();
}
