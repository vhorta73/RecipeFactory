package core;

import constants.EnumAccess;
import constants.EnumFeature;
import constants.EnumTool;

/**
 * User Privilege interface.
 * 
 * List of attributes for tools and features to restrict
 * this user's access to it.
 * 
 * @author Vasco
 *
 */
public interface UserPrivilege {
	/**
	 * True if user can access to given tool.
	 * 
	 * @param tool EnumTool
	 * @return true if allowed
	 */
	public boolean can(EnumTool tool);

	/**
	 * True if user can access given feature.
	 * 
	 * @param tool EnumTool
	 * @param feature EnumFeature
	 * @return true if any access
	 */
	public boolean can(EnumTool tool, EnumFeature feature);

	/**
	 * True if this user has specific access set to given tool and feature.
	 * 
	 * @param tool EnumTool
	 * @param feature EnumFeature
	 * @param access EnumAccess
	 * @return true if allowed
	 */
	public boolean can(EnumTool tool, EnumFeature feature, EnumAccess access);
}
