package core.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import web.interfaces.Session;
import constants.EnumAccess;
import constants.EnumFeature;
import constants.EnumTool;
import core.db.impl.DBAccessImpl;
import core.db.impl.DBFeatureImpl;
import core.db.impl.DBPrivilegeToolFeatureAccessImpl;
import core.db.impl.DBToolImpl;
import core.db.interfaces.DBAccess;
import core.db.interfaces.DBFeature;
import core.db.interfaces.DBPrivilegeToolFeatureAccess;
import core.db.interfaces.DBTool;
import core.interfaces.UserPrivilege;
import core.tables.interfaces.Access;
import core.tables.interfaces.Feature;
import core.tables.interfaces.PrivilegeToolFeatureAccess;
import core.tables.interfaces.Tool;

/**
 * The user privilege checks.
 * 
 * This class is instantiated at the login time and expects for all
 * required data to be loaded then for the user just finishing login.
 * 
 * Stored information about this user will remain stale for the whole
 * duration of the session until logged out, and it is to be used by 
 * front end tools to query about privileges, checking if current 
 * user has enough privileges to access whole or parts of a tool.
 * 
 * @author Vasco
 *
 */
public class UserPrivilegeImpl implements UserPrivilege {
    /**
     * Loaded user Privileges
     */
    private final List<PrivilegeToolFeatureAccess> allowedPrivileges;
    
    /**
     * The Map for tools from code to id.
     */
    private final Map<String, Integer> toolMap;

    /**
-     * The Map for features from code to id.
     */
    private final Map<String, Integer> featureMap;
    
    /**
     * The Map for accesses from code to id.
     */
    private final Map<String, Integer> accessMap;

    /**
     * Constructor, called immediately after the user successfully
     * entered username and password.
     * 
     * @param session
     */
    public UserPrivilegeImpl(Session session) {
        // Validate session.
        if ( session == null ) throw new IllegalArgumentException("Session cannot be null.");
        if ( !session.isLoggedIn() ) throw new IllegalStateException("You must be logged in to proceed.");

         // Get all the user privileges and finalise it.
        DBPrivilegeToolFeatureAccess privilegeToolFeatureAccessDB = new DBPrivilegeToolFeatureAccessImpl(session);
        this.allowedPrivileges = privilegeToolFeatureAccessDB
        		.getPrivilegeToolFeatureAccessesByPrivilegeId(session.getUser().getPrivilegeId());

        // Load all associated maps to the found privileges for easier checks and finalise it.
        accessMap  = new HashMap<String, Integer>();
        featureMap = new HashMap<String, Integer>();
        toolMap    = new HashMap<String, Integer>();
        
        // Instantiate required db connections.
        DBTool toolDB       = new DBToolImpl(session);
        DBFeature featureDB = new DBFeatureImpl(session);
        DBAccess accessDB   = new DBAccessImpl(session);

        // Find the code -> id mappings for all allowed privileges for this user.
        for ( PrivilegeToolFeatureAccess privilegeToolFeatureAccess : allowedPrivileges ) {
            Tool tool = toolDB.getTool(privilegeToolFeatureAccess.getToolId());
            toolMap.put(tool.getToolCd(), privilegeToolFeatureAccess.getToolId());

            Feature feature = featureDB.getFeature(privilegeToolFeatureAccess.getFeatureId());
            featureMap.put(feature.getFeatureCd(), privilegeToolFeatureAccess.getFeatureId());
            
            Access access = accessDB.getAccess(privilegeToolFeatureAccess.getAccessId());
            accessMap.put(access.getAccessCd(), privilegeToolFeatureAccess.getAccessId());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean can(EnumTool tool) {
        // Validate input.
        if ( tool == null ) throw new IllegalArgumentException("Tool cannot be null.");

        // Internally convert all codes to upper case to match enum java naming standards.
        String toolStr = tool.toString();

        // Find the map id of the code.
        Integer toolId = toolMap.get(toolStr);
        
        // If no id found, then nothing assigned to this privileged for the required args.
        if ( toolId == null ) return false;
        
        // Find the first corresponding user privilege record of the found id.
        Optional<PrivilegeToolFeatureAccess> result = allowedPrivileges.stream().filter(p -> p.getToolId() == toolId ).findFirst();

        // If present, then live is good.
        if ( result.isPresent() ) return true;

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean can(EnumTool tool, EnumFeature feature) {
        // Validate input.
        if ( tool == null ) throw new IllegalArgumentException("Tool cannot be null.");
        if ( feature == null ) throw new IllegalArgumentException("Feature cannot be null.");
        
        // Internally convert all codes to upper case to match enum java naming standards.
        String toolStr = tool.toString();
        String featureStr = feature.toString();

        // Find the map id of the code.
        Integer toolId = toolMap.get(toolStr);
        Integer featureId = featureMap.get(featureStr);
        
        // If no id found, then nothing assigned to this privileged for the required args.
        if ( toolId == null ) return false;
        if ( featureId == null ) return false;

        // Find the first corresponding user privilege record of the found id.
        Optional<PrivilegeToolFeatureAccess> result = allowedPrivileges.stream()
                .filter(p -> p.getToolId() == toolId)
                .filter(p -> p.getFeatureId() == featureId)
                .findFirst();

        // If present, then live is good.
        if ( result.isPresent() ) return true;

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean can(EnumTool tool, EnumFeature feature, EnumAccess access) {
        // Validate input.
        if ( tool == null ) throw new IllegalArgumentException("Tool cannot be null.");
        if ( feature == null ) throw new IllegalArgumentException("Feature cannot be null.");
        if ( access == null ) throw new IllegalArgumentException("Access cannot be null.");
        
        // Internally convert all codes to upper case to match enum java naming standards.
        String toolStr = tool.toString();
        String featureStr = feature.toString();
        String accessStr = access.toString();

        // Find the map id of the code.
        Integer toolId = toolMap.get(toolStr);
        Integer featureId = featureMap.get(featureStr);
        Integer accessId = accessMap.get(accessStr);

        // If no id found, then nothing assigned to this privileged for the required args.
        if ( toolId == null ) return false;
        if ( featureId == null ) return false;
        if ( accessId == null ) return false;
        
        // Find the first corresponding user privilege record of the found id.
        Optional<PrivilegeToolFeatureAccess> result = allowedPrivileges.stream()
                .filter(p -> p.getToolId() == toolId)
                .filter(p -> p.getFeatureId() == featureId)
                .filter(p -> p.getAccessId() == accessId)
                .findFirst();

        // If present, then live is good.
        if ( result.isPresent() ) return true;

        return false;
    }
}
