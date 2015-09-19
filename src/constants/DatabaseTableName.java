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
    public static String getIngredientTable() { return TableNames.getIngredientTable(); }

    /**
     * The Ingredient database where the Ingredient table can be found.
     * 
     * @return String database
     */
    public static String getIngredientDatabase() { return DatabaseNames.getCoreDatabase(); }

    /**
     * The User table name.
     * 
     * @return String user
     */
    public static String getUserTable() { return TableNames.getUserTable(); }

    /**
     * The User database where the User table can be found.
     * 
     * @return String database
     */
    public static String getUserDatabase() { return DatabaseNames.getCoreDatabase(); }

    /**
     * The Privilege database where the privilege table can be found.
     * 
     * @return String database
     */
    public static String getPrivilegeDatabase() { return DatabaseNames.getCoreDatabase(); }

    /**
     * The Privilege table name.
     * 
     * @return String privilege
     */
    public static String getPrivilegeTable() { return TableNames.getPrivilegeTable(); }
    
    /**
     * The Privilege Tool Feature Access database 
     * where the privilege_tool_feature_access table can be found.
     * 
     * @return String database
     */
    public static String getPrivilegeToolFeatureAccessDatabase() { return DatabaseNames.getCoreDatabase(); }

    /**
     * The Privilege Tool Feature Access table name.
     * 
     * @return String privilege_tool_feature_access
     */
    public static String getPrivilegeToolFeatureAccessTable() { return TableNames.getPrivilegeToolFeatureAccessTable(); }

    /**
     * The Status database where the status table can be found.
     * 
     * @return String database
     */
    public static String getStatusDatabase() { return DatabaseNames.getCoreDatabase(); }

    /**
     * The Status table name.
     * 
     * @return String status
     */
    public static String getStatusTable() { return TableNames.getStatusTable(); }

    /**
     * The Tool database where the tool table can be found.
     * 
     * @return String database
     */
    public static String getToolDatabase() { return DatabaseNames.getCoreDatabase(); }

    /**
     * The Tool table name.
     * 
     * @return String tool
     */
    public static String getToolTable() { return TableNames.getToolTable(); }

    /**
     * The Feature database where the feature table can be found.
     * 
     * @return String database
     */
    public static String getFeatureDatabase() { return DatabaseNames.getCoreDatabase(); }

    /**
     * The Feature table name.
     * 
     * @return String tool_feature
     */
    public static String getFeatureTable() { return TableNames.getFeatureTable(); }

    /**
     * The access database where the access table can be found.
     * 
     * @return String database
     */
    public static String getAccessDatabase() { return DatabaseNames.getCoreDatabase(); }

    /**
     * The Access table name.
     * 
     * @return String access
     */
    public static String getAccessTable() { return TableNames.getAccessTable(); }

    /**
     * The recipe database where the recipe table can be found.
     * 
     * @return String database
     */
    public static String getRecipeDatabase() { return DatabaseNames.getCoreDatabase(); }

    /**
     * The Recipe table name.
     * 
     * @return String access
     */
    public static String getRecipeTable() { return TableNames.getRecipeTable(); }
}