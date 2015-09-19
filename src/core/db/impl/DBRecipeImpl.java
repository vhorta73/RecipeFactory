package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBRecipe;
import core.tables.impl.RecipeImpl;
import core.tables.interfaces.Recipe;

/**
 * The Recipe table DB access.
 * 
 * @author Vasco
 *
 */
public class DBRecipeImpl implements DBRecipe {
    /**
     * Access can only be granted if a session is open.
     */
    private Session session;

    /**
     * The insert sql for new records.
     */
    private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getRecipeTable() 
            + " (name,final_quantity,unit,description,notes,`show`,deleted,created_by,last_updated_by)"
            + " VALUES(?,?,?,?,?,?,?,?,?)";
    
    /**
     * The update sql for old record changes.
     */
    private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getRecipeTable() 
            + " SET name = ?, final_quantity = ?, unit = ?, description = ?, notes = ?, `show` = ?, deleted = ?, "
            + " last_updated_by = ? WHERE id = ?";

    /**
     * The Constructor requiring a valid initialised session
     * to gain access to the database.
     * 
     * @param dbConnection
     */
    public DBRecipeImpl(Session session) {
        this.session = session;
    }
    
    /**
     * Select a Recipe by id
     * 
     * @param recipeId int
     * @return Recipe
     */
    @Override
    public Recipe getRecipe(int recipeId) {
        // Initialise the final Recipe object to be returned.
        return getResults("SELECT * FROM " + DatabaseTableName.getRecipeTable() + " WHERE id = '"+recipeId+"'").get(0);
    }

    /**
     * Select an Recipe by name
     * 
     * @param recipeName String
     * @return Recipe
     */
    @Override
    public Recipe getRecipe(String recipeName) {
        // Initialise the final Recipe object to be returned.
        return getResults("SELECT * FROM " + DatabaseTableName.getRecipeTable() + " WHERE name = '"+recipeName+"'").get(0);
    }
    
    /**
     * Get a list of results from the database.
     * 
     * @param sql
     * @return List Recipe
     */
    private List<Recipe> getResults(String sql) {
        // Validate argument
        if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

        // Initialise the final Recipe object to be returned.
        List<Recipe> finalRecipeList = new LinkedList<Recipe>();

        ResultSet rs = null;
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getRecipeDatabase())
                    .prepareStatement(sql);

            rs = prepSt
                    .executeQuery();

            while ( rs.next() ) {
                int id                      = rs.getInt(1);
                String recipeName           = rs.getString(2);
                double finalQuantity        = rs.getDouble(3);
                String unit                 = rs.getString(4);
                String description          = rs.getString(5);
                String notes                = rs.getString(6);
                boolean show                = rs.getBoolean(7);
                boolean deleted             = rs.getBoolean(8);
                String created_by           = rs.getString(9);
                Timestamp created_date      = rs.getTimestamp(10);
                String last_updated_by      = rs.getString(11);
                Timestamp last_updated_date = rs.getTimestamp(12);

                finalRecipeList.add(new RecipeImpl(id, recipeName, finalQuantity, unit, description, notes, show, deleted,
                        created_by, created_date, last_updated_by, last_updated_date));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepSt.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return finalRecipeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(Recipe recipe) {
        if ( recipe == null ) throw new IllegalArgumentException("Recipe record cannot be null.");
        List<Recipe> recipeList = new LinkedList<Recipe>();
        recipeList.add(recipe);
        createRecords(recipeList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<Recipe> recipeList) {
        if ( recipeList == null ) throw new IllegalArgumentException("Recipe list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getRecipeDatabase())
            		.prepareStatement(INSERT_SQL);
    
            for( Recipe recipe : recipeList ) {
                prepSt.setString(1, recipe.getName());
                prepSt.setDouble(2, recipe.getFinalQuantity());
                prepSt.setString(3, recipe.getUnit());
                prepSt.setString(4, recipe.getDescription());
                prepSt.setString(5, recipe.getNotes());
                prepSt.setBoolean(6, recipe.isShow());
                prepSt.setBoolean(7, recipe.isDeleted());
                prepSt.setString(8, recipe.getCreatedBy());
                prepSt.setString(9, recipe.getLastUpdatedBy());            
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
    public void updateRecord(Recipe recipe) {
        if ( recipe == null ) throw new IllegalArgumentException("Recipe record cannot be null.");
        List<Recipe> recipeList = new LinkedList<Recipe>();
        recipeList.add(recipe);
        updateRecords(recipeList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<Recipe> recipeList) {
        if ( recipeList == null ) throw new IllegalArgumentException("Recipe list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getRecipeDatabase())
            		.prepareStatement(UPDATE_SQL);
    
            for( Recipe recipe : recipeList ) {
                prepSt.setString(1, recipe.getName());
                prepSt.setDouble(2, recipe.getFinalQuantity());
                prepSt.setString(3, recipe.getUnit());
                prepSt.setString(4, recipe.getDescription());
                prepSt.setString(5, recipe.getNotes());
                prepSt.setBoolean(6, recipe.isShow());
                prepSt.setBoolean(7, recipe.isDeleted());
                prepSt.setString(8, recipe.getLastUpdatedBy());
                prepSt.setInt(9, recipe.getId());            
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