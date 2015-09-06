package constants;
/**
 * List of all corresponding tables and databases for each object.
 * 
 * @author Vasco
 *
 */
public class DBTables {
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
}