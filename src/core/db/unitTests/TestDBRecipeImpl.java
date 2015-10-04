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
import core.db.impl.DBRecipeImpl;
import core.db.interfaces.DBRecipe;
import core.tables.impl.RecipeImpl;
import core.tables.interfaces.Recipe;
import core.tables.interfaces.User;

/**
 * Testing DBRecipe implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBRecipeImpl {
    /**
     * DBRecipe handle.
     */
    private DBRecipe dbRecipe;
    
    /** DATA **/
    private final int ID = 231;
    private final String NAME = "My Recipe name";
    private final double FINAL_QUANTITY = 24.25;
    private final String UNIT = EnumUnit.L.toString();
    private final String DESCRIPTION = "My description";
    private final String NOTES = "My notes";
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
        dbRecipe = new DBRecipeImpl(session);
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
     * Test getRecipe(id).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetRecipeById() throws SQLException {
        Recipe expectedRecipe = new RecipeImpl(ID, NAME, FINAL_QUANTITY, UNIT, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(NAME);
        Mockito.when(resultSet.getDouble(3)).thenReturn(FINAL_QUANTITY);
        Mockito.when(resultSet.getString(4)).thenReturn(UNIT);
        Mockito.when(resultSet.getString(5)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getString(6)).thenReturn(NOTES);
        Mockito.when(resultSet.getBoolean(7)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(8)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(9)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(11)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(12)).thenReturn(LAST_UPDATED_DATE);

        Recipe foundRecipe = dbRecipe.getRecipe(ID);

        assertNotNull(foundRecipe);

        verify(expectedRecipe,foundRecipe);
    }

    /**
     * Test getRecipe(recipeName).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetRecipeByName() throws SQLException {
        Recipe expectedRecipe = new RecipeImpl(ID, NAME, FINAL_QUANTITY, UNIT, DESCRIPTION, NOTES, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(NAME);
        Mockito.when(resultSet.getDouble(3)).thenReturn(FINAL_QUANTITY);
        Mockito.when(resultSet.getString(4)).thenReturn(UNIT);
        Mockito.when(resultSet.getString(5)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getString(6)).thenReturn(NOTES);
        Mockito.when(resultSet.getBoolean(7)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(8)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(9)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(11)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(12)).thenReturn(LAST_UPDATED_DATE);

        Recipe foundRecipe = dbRecipe.getRecipe(NAME);

        assertNotNull(foundRecipe);

        verify(expectedRecipe,foundRecipe);
    }

    /**
     * Test createRecord(recipe).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        Recipe expectedRecipe = new RecipeImpl(ID, NAME, FINAL_QUANTITY, UNIT, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbRecipe.createRecord(expectedRecipe);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(2, FINAL_QUANTITY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, UNIT);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(5, NOTES);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(6, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(7, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(8, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(9, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test createRecords(accessList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<Recipe> expectedRecipeList = new LinkedList<Recipe>();
        expectedRecipeList.add(new RecipeImpl(ID, NAME, FINAL_QUANTITY, UNIT, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedRecipeList.add(new RecipeImpl(ID+1, NAME+ID, FINAL_QUANTITY+ID, UNIT, DESCRIPTION+ID, NOTES, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbRecipe.createRecords(expectedRecipeList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(2, FINAL_QUANTITY+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(2, FINAL_QUANTITY);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(3, UNIT);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, DESCRIPTION+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(5, NOTES);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(6, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(7, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(8, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(9, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecord(access).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        Recipe expectedRecipe = new RecipeImpl(ID, NAME, FINAL_QUANTITY, UNIT, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbRecipe.updateRecord(expectedRecipe);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(2, FINAL_QUANTITY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, UNIT);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(5, NOTES);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(6, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(7, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(8, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(9, ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecords(accessList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecords() throws SQLException {
        List<Recipe> expectedRecipeList = new LinkedList<Recipe>();
        expectedRecipeList.add(new RecipeImpl(ID, NAME, FINAL_QUANTITY, UNIT, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedRecipeList.add(new RecipeImpl(ID+1, NAME+ID, FINAL_QUANTITY+ID, UNIT, DESCRIPTION+ID, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        

        dbRecipe.updateRecords(expectedRecipeList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(2, FINAL_QUANTITY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setDouble(2, FINAL_QUANTITY+ID);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(3, UNIT);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, DESCRIPTION+ID);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(5, NOTES);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(6, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(7, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(8, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(9, ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(9, ID+1);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Check if both given Recipe rows match.
     * 
     * @param expectedRecipe Recipe
     * @param foundRecipe Recipe
     */
    private void verify(Recipe expectedRecipe, Recipe foundRecipe) {
        assertNotNull(foundRecipe);
        assertEquals(expectedRecipe.getId(),foundRecipe.getId());
        assertEquals(expectedRecipe.getName(),foundRecipe.getName());
        assertTrue(expectedRecipe.getFinalQuantity() == foundRecipe.getFinalQuantity());
        assertEquals(expectedRecipe.getUnit(),foundRecipe.getUnit());
        assertEquals(expectedRecipe.getDescription(),foundRecipe.getDescription());
        assertEquals(expectedRecipe.getNotes(),foundRecipe.getNotes());
        assertEquals(expectedRecipe.isDeleted(),foundRecipe.isDeleted());
        assertEquals(expectedRecipe.isShow(),foundRecipe.isShow());
        assertEquals(expectedRecipe.getCreatedBy(),foundRecipe.getCreatedBy());
        assertEquals(expectedRecipe.getCreatedDate(),foundRecipe.getCreatedDate());
        assertEquals(expectedRecipe.getLastUpdatedBy(),foundRecipe.getLastUpdatedBy());
        assertEquals(expectedRecipe.getLastUpdatedDate(),foundRecipe.getLastUpdatedDate());
    }
}
