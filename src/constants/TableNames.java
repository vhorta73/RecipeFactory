package constants;

/**
 * Known database tables.
 * 
 * @author Vasco
 *
 */
public class TableNames {
	public static String getIngredientTable()                 { return "ingredient"; }                    // The ingredient list
	public static String getUserTable()                       { return "user"; }                          // The user list
	public static String getPrivilegeTable()                  { return "privilege"; }                     // Privilege names
	public static String getPrivilegeToolFeatureAccessTable() { return "privilege_tool_feature_access"; } // For a privilege id, which tool-feature have access
	public static String getStatusTable()                     { return "status"; }                        // List of all possible statuses
	public static String getToolTable()                       { return "tool"; }                          // List of all known tools
	public static String getFeatureTable()                    { return "feature"; }                       // List of all features tools may have
	public static String getAccessTable()                     { return "access"; }                        // List of all types of known accesses
	public static String getRecipeTable()                     { return "recipe"; }                        // The recipe list
	public static String getRecipeIngredientTable()           { return "recipe_ingredient"; }             // The recipe ingredient list
}
