package constants;
/**
 * List of all corresponding tables and databases for each object.
 * 
 * @author Vasco
 *
 */
public class DatabaseTableName {
	/**
	 * The Ingredient table name.
	 * 
	 * @return String ingredient
	 */
	public static String getIngredientTable() { 
		return EnumTables.ingredient.toString(); 
	}
	
	/**
	 * The Ingredient database where the Ingredient table can be found.
	 * 
	 * @return String database
	 */
	public static String getIngredientDatabase() {
		return EnumDatabases.rfact_core.toString(); 
	}

	/**
	 * The User table name.
	 * 
	 * @return String user
	 */
	public static String getUserTable() {
		return EnumTables.user.toString();
	}
	
	/**
	 * The User database where the User table can be found.
	 * 
	 * @return String database
	 */
	public static String getUserDatabase() {
		return EnumDatabases.rfact_core.toString();
	}

	/**
	 * The Privilege database where the privilege table can be found.
	 * 
	 * @return String database
	 */
	public static String getPrivilegeDatabase() {
		return EnumDatabases.rfact_core.toString();
	}

	/**
	 * The Privilege table name.
	 * 
	 * @return String privilege
	 */
	public static String getPrivilegeTable() {
		return EnumTables.privilege.toString();
	}
	
	/**
	 * The Privilege Tool table name.
	 * 
	 * @return String privilege_tool
	 */
	public static String getPrivilegeToolTable() {
		return EnumTables.privilege_tool.toString();
	}
	
	/**
	 * The Privilege Tool Feature Access table name.
	 * 
	 * @return String privilege_tool_feature_access
	 */
	public static String getPrivilegeToolFeatureAccessTable() {
		return EnumTables.privilege_tool_feature_access.toString();
	}
}