package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import mySQL.ConnectDB;
import constants.DBTables;

/**
 * The Ingredient table DB access.
 * 
 * @author Vasco
 *
 */
public class DBIngredient extends ConnectDB {
	/**
	 * Select an Ingredient by name
	 * 
	 * @param name ingredient
	 * @return Ingredient 
	 */
	public Ingredient getIngredient(String name) {
		// Validate argument
		if ( name == null ) throw new IllegalArgumentException("Ingredient name cannot be null.");

        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DBTables.getIngredientTable() + " WHERE name = '"+name+"'").get(0);
	}

	/**
	 * Select an Ingredient by id
	 * 
	 * @param id ingredient
	 * @return Ingredient
	 */
	public Ingredient getIngredient(int id) {
        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DBTables.getIngredientTable() + " WHERE id = '"+id+"'").get(0);
	}
	
	/**
	 * Return all existing ingredients.
	 * 
	 * @return List Ingredient
	 */
	public List<Ingredient> getIngredients() {
		return getResults("SELECT * FROM " + DBTables.getIngredientTable());
	}

	/**
	 * Get a list of results from the database.
	 * 
	 * @param sql
	 * @return List Ingredient 
	 */
	private List<Ingredient> getResults(String sql) {
		// Validate argument
		if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

		// Initialise the final Ingredient object to be returned.
		List<Ingredient> finalIngredientList = new LinkedList<Ingredient>();

		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = super
					.getConnection(DBTables.getIngredientDatabase())
					.prepareStatement(sql);
			rs = prepSt
					.executeQuery();

			while ( rs.next() ) {
				int id                 = rs.getInt(1);
				String name            = rs.getString(2);
				String description     = rs.getString(3);
				String notes           = rs.getString(4);
				String created_by      = rs.getString(5);
				Timestamp created_date      = rs.getTimestamp(6);
				String last_updated_by = rs.getString(7);
				Timestamp last_updated_date = rs.getTimestamp(8);

				finalIngredientList.add(new IngredientImpl(id, name, description, notes, created_by, 
						created_date, last_updated_by, last_updated_date));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepSt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return finalIngredientList;
	}
}
