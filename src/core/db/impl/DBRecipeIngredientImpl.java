package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBRecipeIngredient;
import core.tables.impl.RecipeIngredientImpl;
import core.tables.interfaces.RecipeIngredient;

/**
 * The RecipeIngredient table DB access.
 * 
 * @author Vasco
 *
 */
public class DBRecipeIngredientImpl implements DBRecipeIngredient {
    /**
     * Access can only be granted if a session is open.
     */
    private Session session;

    /**
     * The insert sql for new records.
     */
    private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getRecipeIngredientTable() 
            + " (recipe_id,ingredient_id,quantity,unit,`show`,deleted,created_by,last_updated_by)"
            + " VALUES(?,?,?,?,?,?,?,?)";
    
    /**
     * The update sql for old record changes.
     */
    private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getRecipeIngredientTable() 
            + " SET quantity = ?, unit = ?, `show` = ?, deleted = ?, last_updated_by = ? "
            + " WHERE recipe_id = ? AND ingredient_id = ?";

    /**
     * The Constructor requiring a valid initialised session
     * to gain access to the database.
     * 
     * @param dbConnection
     */
    public DBRecipeIngredientImpl(Session session) {
        this.session = session;
    }
    
    /**
     * Select a RecipeIngredient by id
     * 
     * @param recipeIngredientId int
     * @return RecipeIngredient
     */
    @Override
    public List<RecipeIngredient> getRecipeIngredient(int recipeId) {
        // Initialise the final Recipe object to be returned.
        return getResults("SELECT * FROM " + DatabaseTableName.getRecipeIngredientTable() + " WHERE recipe_id = '"+recipeId+"'");
    }

    /**
     * Get a list of results from the database.
     * 
     * @param sql
     * @return List RecipeIngredient
     */
    private List<RecipeIngredient> getResults(String sql) {
        // Validate argument
        if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

        // Initialise the final Recipe object to be returned.
        List<RecipeIngredient> finalRecipeIngredientList = new LinkedList<RecipeIngredient>();

        ResultSet rs = null;
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getRecipeIngredientDatabase())
                    .prepareStatement(sql);

            rs = prepSt.executeQuery();

            while ( rs.next() ) {
                int recipeId                = rs.getInt(1);
                int ingredientId            = rs.getInt(2);
                double finalQuantity        = rs.getDouble(3);
                String unit                 = rs.getString(4);
                boolean show                = rs.getBoolean(5);
                boolean deleted             = rs.getBoolean(6);
                String created_by           = rs.getString(7);
                Timestamp created_date      = rs.getTimestamp(8);
                String last_updated_by      = rs.getString(9);
                Timestamp last_updated_date = rs.getTimestamp(10);

                finalRecipeIngredientList.add(new RecipeIngredientImpl(recipeId, ingredientId, finalQuantity, unit, show, deleted,
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

        return finalRecipeIngredientList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(RecipeIngredient recipeIngredient) {
        if ( recipeIngredient == null ) throw new IllegalArgumentException("RecipeIngredient record cannot be null.");
        List<RecipeIngredient> recipeIngredientList = new LinkedList<RecipeIngredient>();
        recipeIngredientList.add(recipeIngredient);
        createRecords(recipeIngredientList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<RecipeIngredient> recipeIngredientList) {
        if ( recipeIngredientList == null ) throw new IllegalArgumentException("RecipeIngredient list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getRecipeIngredientDatabase())
                    .prepareStatement(INSERT_SQL);
    
            for( RecipeIngredient recipeIngredient : recipeIngredientList ) {
                prepSt.setInt(1, recipeIngredient.getRecipeId());
                prepSt.setInt(2, recipeIngredient.getIngredientId());
                prepSt.setDouble(3, recipeIngredient.getQuantity());
                prepSt.setString(4, recipeIngredient.getUnit());
                prepSt.setBoolean(5, recipeIngredient.isShow());
                prepSt.setBoolean(6, recipeIngredient.isDeleted());
                prepSt.setString(7, recipeIngredient.getCreatedBy());
                prepSt.setString(8, recipeIngredient.getLastUpdatedBy());            
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
    public void updateRecord(RecipeIngredient recipeIngredientIngredient) {
        if ( recipeIngredientIngredient == null ) throw new IllegalArgumentException("RecipeIngredient record cannot be null.");
        List<RecipeIngredient> recipeList = new LinkedList<RecipeIngredient>();
        recipeList.add(recipeIngredientIngredient);
        updateRecords(recipeList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<RecipeIngredient> recipeIngredientList) {
        if ( recipeIngredientList == null ) throw new IllegalArgumentException("RecipeIngredient list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getRecipeIngredientDatabase())
                    .prepareStatement(UPDATE_SQL);
    
            for( RecipeIngredient recipeIngredient : recipeIngredientList ) {
                prepSt.setDouble(1, recipeIngredient.getQuantity());
                prepSt.setString(2, recipeIngredient.getUnit());
                prepSt.setBoolean(3, recipeIngredient.isShow());
                prepSt.setBoolean(4, recipeIngredient.isDeleted());
                prepSt.setString(5, recipeIngredient.getLastUpdatedBy());
                prepSt.setInt(6, recipeIngredient.getRecipeId());
                prepSt.setInt(7, recipeIngredient.getIngredientId());            
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