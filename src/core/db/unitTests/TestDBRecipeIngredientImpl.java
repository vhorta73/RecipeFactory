package core.db.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import web.interfaces.Session;
import constants.EnumUnit;
import core.ConnectDB;
import core.db.impl.DBRecipeIngredientImpl;
import core.db.interfaces.DBRecipeIngredient;
import core.tables.impl.RecipeIngredientImpl;
import core.tables.interfaces.RecipeIngredient;
import core.tables.interfaces.User;

/**
 * Testing DBRecipeIngredient implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBRecipeIngredientImpl {
    /**
     * DBRecipeIngredient handle.
     */
    private DBRecipeIngredient dbRecipeIngredient;
    
    /** DATA **/
    private final int RECIPE_ID = 231;
    private final int INGREDIENT_ID = 1;
    private final double QUANTITY = 24.25;
    private final String UNIT = EnumUnit.L.toString();
    private final boolean DELETED = false;
    private final boolean SHOW = true;
    private final String CREATED_BY = "Me";
    private final Timestamp CREATED_DATE = Timestamp.valueOf("2015-09-16 21:51:12");
    private final String LAST_UPDATED_BY = "Meself";
    private final Timestamp LAST_UPDATED_DATE = Timestamp.valueOf("2015-09-16 21:51:12");

    /** MOCKITO **/
    private Session session = Mockito.mock(Session.class);
    private ConnectDB connect = Mockito.mock(ConnectDB.class);
    private Connection connection = Mockito.mock(Connection.class);
    private PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    private ResultSet resultSet = Mockito.mock(ResultSet.class);
    private User user = Mockito.mock(User.class);

    /**
     * Preparations for next test.
     */
    @Before
    public void before() {
        Mockito.when(session.getUser()).thenReturn(user);
        Mockito.when(session.isLoggedIn()).thenReturn(true);
        Mockito.when(session.isUserValidated()).thenReturn(true);

        Mockito.when(session.getDB()).thenReturn(connect);
        Mockito.when(connect.getConnection(Mockito.anyString())).thenReturn(connection);
        try {
            Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
            Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbRecipeIngredient = new DBRecipeIngredientImpl(session);
    }
    
    /**
     * Check if methods are always called at the end.
     * 
     * @throws SQLException 
     */
    @After
    public void after() throws SQLException {
        Mockito.verify(preparedStatement, Mockito.times(1)).close();
    }

    /**
     * Test getRecipeIngredient(recipeId).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetRecipeIngredient() throws SQLException {
        RecipeIngredient expectedRecipeIngredient = new RecipeIngredientImpl(RECIPE_ID, INGREDIENT_ID, QUANTITY, UNIT,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(RECIPE_ID);
        Mockito.when(resultSet.getInt(2)).thenReturn(INGREDIENT_ID);
        Mockito.when(resultSet.getDouble(3)).thenReturn(QUANTITY);
        Mockito.when(resultSet.getString(4)).thenReturn(UNIT);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        List<RecipeIngredient> foundRecipeIngredientList = dbRecipeIngredient.getRecipeIngredient(RECIPE_ID);

        assertNotNull(foundRecipeIngredientList);
        assertTrue(foundRecipeIngredientList.size() == 1);

        verify(expectedRecipeIngredient,foundRecipeIngredientList.get(0));
    }

    /**
     * Test createRecord(recipeIngredient).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        RecipeIngredient expectedRecipeIngredient = new RecipeIngredientImpl(RECIPE_ID, INGREDIENT_ID, QUANTITY, UNIT,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbRecipeIngredient.createRecord(expectedRecipeIngredient);

        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, RECIPE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, INGREDIENT_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(3, QUANTITY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, UNIT);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(5, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(6, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(7, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(8, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test createRecords(recipeIngredientList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<RecipeIngredient> expectedRecipeIngredientList = new LinkedList<RecipeIngredient>();
        expectedRecipeIngredientList.add(new RecipeIngredientImpl(RECIPE_ID, INGREDIENT_ID, QUANTITY, UNIT,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedRecipeIngredientList.add(new RecipeIngredientImpl(RECIPE_ID+1, INGREDIENT_ID+1, QUANTITY+1, UNIT, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbRecipeIngredient.createRecords(expectedRecipeIngredientList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, RECIPE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, RECIPE_ID+1);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, INGREDIENT_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, INGREDIENT_ID+1);
        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(3, QUANTITY+1);
        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(3, QUANTITY);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(4, UNIT);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(5, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(6, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(7, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(8, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecord(recipeIngredient).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        RecipeIngredient expectedRecipeIngredient = new RecipeIngredientImpl(RECIPE_ID, INGREDIENT_ID, QUANTITY, UNIT,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbRecipeIngredient.updateRecord(expectedRecipeIngredient);

        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(1, QUANTITY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, UNIT);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(3, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(4, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(5, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(6, RECIPE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, INGREDIENT_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecords(recipeIngredientList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecords() throws SQLException {
        List<RecipeIngredient> expectedRecipeIngredientList = new LinkedList<RecipeIngredient>();
        expectedRecipeIngredientList.add(new RecipeIngredientImpl(RECIPE_ID, INGREDIENT_ID, QUANTITY, UNIT,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedRecipeIngredientList.add(new RecipeIngredientImpl(RECIPE_ID+1, INGREDIENT_ID+1, QUANTITY+1, UNIT,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbRecipeIngredient.updateRecords(expectedRecipeIngredientList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(1, QUANTITY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(1, QUANTITY+1);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(2, UNIT);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(3, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(4, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(5, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(6, RECIPE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(6, RECIPE_ID+1);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, INGREDIENT_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, INGREDIENT_ID+1);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Check if both given RecipeIngredient rows match.
     * 
     * @param expectedRecipeIngredient RecipeIngredient
     * @param foundRecipeIngredient RecipeIngredient
     */
    private void verify(RecipeIngredient expectedRecipeIngredient, RecipeIngredient foundRecipeIngredient) {
        assertNotNull(foundRecipeIngredient);
        assertEquals(expectedRecipeIngredient.getRecipeId(),foundRecipeIngredient.getRecipeId());
        assertEquals(expectedRecipeIngredient.getIngredientId(),foundRecipeIngredient.getIngredientId());
        assertTrue(expectedRecipeIngredient.getQuantity() == foundRecipeIngredient.getQuantity());
        assertEquals(expectedRecipeIngredient.getUnit(),foundRecipeIngredient.getUnit());
        assertEquals(expectedRecipeIngredient.isDeleted(),foundRecipeIngredient.isDeleted());
        assertEquals(expectedRecipeIngredient.isShow(),foundRecipeIngredient.isShow());
        assertEquals(expectedRecipeIngredient.getCreatedBy(),foundRecipeIngredient.getCreatedBy());
        assertEquals(expectedRecipeIngredient.getCreatedDate(),foundRecipeIngredient.getCreatedDate());
        assertEquals(expectedRecipeIngredient.getLastUpdatedBy(),foundRecipeIngredient.getLastUpdatedBy());
        assertEquals(expectedRecipeIngredient.getLastUpdatedDate(),foundRecipeIngredient.getLastUpdatedDate());
    }
}
