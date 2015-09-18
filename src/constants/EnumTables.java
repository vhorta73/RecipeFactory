package constants;

/**
 * Known database tables.
 * 
 * @author Vasco
 *
 */
public enum EnumTables {
	ingredient,                            // The ingredient list
	user,                                  // The user list
	privilege,                             // Privilege names
	privilege_tool_feature_access,         // For a privilege id, which tool-feature have access
	status,                                // List of all possible statuses
	tool,                                  // List of all known tools
	feature,                               // List of all features tools may have
	access,                                // List of all types of known accesses
	
}
