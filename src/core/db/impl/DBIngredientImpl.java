package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBIngredient;
import core.tables.impl.IngredientImpl;
import core.tables.interfaces.Ingredient;

/**
 * The Ingredient table DB access.
 * 
 * @author Vasco
 *
 */
public class DBIngredientImpl implements DBIngredient {
	/**
	 * Access can only be granted if a session is open.
	 */
	private Session session;

	/**
	 * The final insert sql to create new records.
	 */
	private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getIngredientTable() 
            + " (name,description,notes,created_by,last_updated_by)"
            + " VALUES(?,?,?,?,?)";

	/**
	 * The final updating sql to update old records.
	 */
    private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getIngredientTable() 
    		+ " SET access_cd = ?, display_name = ?, description = ?,"
    		+ " last_updated_by = ? WHERE id = ? ";

	/**
	 * The Constructor requiring a valid initialised session
	 * to gain access to the database.
	 * 
	 * @param dbConnection
	 */
	public DBIngredientImpl(Session session) {
		this.session = session;
	}
	
	/**
	 * Select an Ingredient by name
	 * 
	 * @param name ingredient
	 * @return Ingredient 
	 */
	@Override
	public Ingredient getIngredient(String name) {
		// Validate argument
		if ( name == null ) throw new IllegalArgumentException("Ingredient name cannot be null.");

        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DatabaseTableName.getIngredientTable() + " WHERE name = '"+name+"'").get(0);
	}

	/**
	 * Select an Ingredient by id
	 * 
	 * @param id ingredient
	 * @return Ingredient
	 */
	@Override
	public Ingredient getIngredient(int id) {
        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DatabaseTableName.getIngredientTable() + " WHERE id = '"+id+"'").get(0);
	}
	
	/**
	 * Return all existing ingredients.
	 * 
	 * @return List Ingredient
	 */
	@Override
	public List<Ingredient> getIngredients() {
		return getResults("SELECT * FROM " + DatabaseTableName.getIngredientTable());
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
			prepSt = this.session.getDB()
					.getConnection(DatabaseTableName.getIngredientDatabase())
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

	/**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(Ingredient ingredient) {
        if ( ingredient == null ) throw new IllegalArgumentException("Ingredient record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getIngredientDatabase())
                    .prepareStatement(INSERT_SQL);
            
            prepSt.setString(1, ingredient.getName());
            prepSt.setString(2, ingredient.getDescription());
            prepSt.setString(3, ingredient.getNotes());
            prepSt.setString(4, ingredient.getCreatedBy());
            prepSt.setString(5, ingredient.getLastUpdatedBy());            
            prepSt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepSt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<Ingredient> ingredientList) {
        if ( ingredientList == null ) throw new IllegalArgumentException("Ingredient list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getIngredientDatabase())
            		.prepareStatement(INSERT_SQL);
    
            for( Ingredient ingredient : ingredientList ) {
                prepSt.setString(1, ingredient.getName());
                prepSt.setString(2, ingredient.getDescription());
                prepSt.setString(3, ingredient.getNotes());
                prepSt.setString(4, ingredient.getCreatedBy());
                prepSt.setString(5, ingredient.getLastUpdatedBy());            
                prepSt.addBatch();
            }
            prepSt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepSt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecord(Ingredient ingredient) {
        if ( ingredient == null ) throw new IllegalArgumentException("Ingredient record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getIngredientDatabase())
                    .prepareStatement(UPDATE_SQL);
            
            prepSt.setString(1, ingredient.getName());
            prepSt.setString(2, ingredient.getDescription());
            prepSt.setString(3, ingredient.getNotes());
            prepSt.setString(4, ingredient.getLastUpdatedBy());            
            prepSt.setInt(5, ingredient.getId());
            prepSt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepSt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<Ingredient> ingredientList) {
        if ( ingredientList == null ) throw new IllegalArgumentException("Ingredient list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getIngredientDatabase())
            		.prepareStatement(UPDATE_SQL);
                
            for( Ingredient ingredient : ingredientList ) {
                prepSt.setString(1, ingredient.getName());
                prepSt.setString(2, ingredient.getDescription());
                prepSt.setString(3, ingredient.getNotes());
                prepSt.setString(4, ingredient.getLastUpdatedBy());
                prepSt.setInt(5, ingredient.getId());            
                prepSt.addBatch();
            }
            prepSt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepSt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
