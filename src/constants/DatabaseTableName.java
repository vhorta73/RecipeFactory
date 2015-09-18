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
     * The Privilege Tool database where the privilege_tool table can be found.
     * 
     * @return String database
     */
    public static String getPrivilegeToolDatabase() {
        return EnumDatabases.rfact_core.toString();
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

    /**
     * The Status database where the status table can be found.
     * 
     * @return String database
     */
    public static String getStatusDatabase() {
        return EnumDatabases.rfact_core.toString();
    }

    /**
     * The Status table name.
     * 
     * @return String status
     */
    public static String getStatusTable() {
        return EnumTables.status.toString();
    }

    /**
     * The Tool database where the tool table can be found.
     * 
     * @return String database
     */
    public static String getToolDatabase() {
        return EnumDatabases.rfact_core.toString();
    }

    /**
     * The Tool table name.
     * 
     * @return String tool
     */
    public static String getToolTable() {
        return EnumTables.tool.toString();
    }

    /**
     * The Feature database where the feature table can be found.
     * 
     * @return String database
     */
    public static String getFeatureDatabase() {
        return EnumDatabases.rfact_core.toString();
    }

    /**
     * The Feature table name.
     * 
     * @return String tool_feature
     */
    public static String getFeatureTable() {
        return EnumTables.tool_feature.toString();
    }

    /**
     * The access database where the access table can be found.
     * 
     * @return String database
     */
    public static String getAccessDatabase() {
        return EnumDatabases.rfact_core.toString();
    }

    /**
     * The Access table name.
     * 
     * @return String access
     */
    public static String getAccessTable() {
        return EnumTables.access.toString();
    }

}