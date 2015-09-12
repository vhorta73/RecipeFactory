package core;

import java.util.List;
import java.util.Optional;

import web.interfaces.Session;
import constants.EnumAccess;
import constants.EnumFeature;
import constants.EnumTool;
import core.db.impl.DBAccessImpl;
import core.db.impl.DBFeatureImpl;
import core.db.impl.DBPrivilegeImpl;
import core.db.impl.DBToolImpl;
import core.db.interfaces.DBPrivilege;
import core.db.interfaces.DBTool;
import core.tables.interfaces.PrivilegeTool;
import core.tables.interfaces.PrivilegeToolFeatureAccess;

/**
 * The user privilege checks.
 * 
 * @author Vasco
 *
 */
public class UserPrivilegeImpl implements UserPrivilege {
	private DBPrivilege privileges;
	private DBTool tools;
	private DBFeatureImpl features;
	private DBAccessImpl accesses;
	private List<PrivilegeTool> allowedTools;

	/**
	 * Constructor.
	 * 
	 * @param session Session
	 * @param user User
	 */
	public UserPrivilegeImpl(Session session) {
		// Validate input
		if ( session == null ) throw new IllegalArgumentException("Session cannot be null.");
		if ( !session.isLoggedIn() ) throw new IllegalArgumentException("User must be logged in at this point.");

    	this.privileges = new DBPrivilegeImpl(session);
    	this.tools = new DBToolImpl(session);
    	this.allowedTools = privileges.getPrivilegeTools();
    	this.features = new DBFeatureImpl(session);
    	this.accesses = new DBAccessImpl(session);
	}

	@Override
	public boolean can(EnumTool tool) {
    	if( getTool(tool).isPresent() ) return true;
		return false;
	}

	@Override
	public boolean can(EnumTool tool, EnumFeature feature) {
		if ( getFeature(tool, feature).isPresent() ) return true;
		return false;
	}

	@Override
	public boolean can(EnumTool tool, EnumFeature feature, EnumAccess access) {
		if ( !can(tool) ) return false;
		if ( !can(tool, feature) ) return false;

		Optional<PrivilegeToolFeatureAccess> privilegeToolFeatureAccess = getAccess(tool, feature, access);
		if ( privilegeToolFeatureAccess.isPresent() ) return true; 
		return false;
	}
	
	private Optional<PrivilegeTool> getTool(EnumTool tool) {
    	// There will be only one or no tool under this privilege matching given tool
		return allowedTools.stream().filter(p -> tools.getTool(p.getToolId())
				.getToolCd().equals(tool.toString().toLowerCase()))
				.findFirst();
	}
	
	private Optional<PrivilegeToolFeatureAccess> getFeature(EnumTool tool, EnumFeature feature) {
		// Get the tool details.
		Optional<PrivilegeTool> foundTool = getTool(tool);
		PrivilegeTool privilegeTool = foundTool.get();
		int privilegeToolId = privilegeTool.getId();

		List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccess = privileges.getToolFeatureAccess();
		Optional<PrivilegeToolFeatureAccess> featuresOpt = privilegeToolFeatureAccess.stream()
				.filter(p -> p.getPrivilegeToolId() == privilegeToolId )
				.filter(p -> features.getFeature(
						p.getFeatureId()).getFeatureCd().toLowerCase()
						.equals(feature.toString().toLowerCase()))
						.findFirst();
		
		return featuresOpt;
	}
	
	private Optional<PrivilegeToolFeatureAccess> getAccess(EnumTool tool, EnumFeature feature, EnumAccess access) {
		Optional<PrivilegeToolFeatureAccess> privilegeToolFeatureAccess = getFeature(tool, feature);
		
		// If present we must have the access id and code.
		if ( privilegeToolFeatureAccess.isPresent() ) {
			PrivilegeToolFeatureAccess toolFeatureAccess = privilegeToolFeatureAccess.get();
			int accessId = toolFeatureAccess.getAccessId();
			String accessCd = accesses.getAccess(accessId).getAccessCd();
			if ( accessCd.toLowerCase().equals(access.toString().toLowerCase()) ) return privilegeToolFeatureAccess;
		}

		return Optional.empty();
	}
}
