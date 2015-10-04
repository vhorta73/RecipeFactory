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
import core.ConnectDB;
import core.db.impl.DBIngredientImpl;
import core.db.interfaces.DBIngredient;
import core.tables.impl.IngredientImpl;
import core.tables.interfaces.Ingredient;
import core.tables.interfaces.User;

/**
 * Testing DBIngredient implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBIngredientImpl {
    /**
     * DBIngredient handle.
     */
    private DBIngredient dbIngredient;
    
    /** DATA **/
    private final int ID = 231;
    private final String NAME = "Ingredient name";
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
        dbIngredient = new DBIngredientImpl(session);
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
     * Test getIngredient(id).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetIngredientById() throws SQLException {
        Ingredient expectedIngredient = new IngredientImpl(ID, NAME, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(NAME);
        Mockito.when(resultSet.getString(3)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getString(4)).thenReturn(NOTES);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Ingredient foundIngredient = dbIngredient.getIngredient(ID);

        assertNotNull(foundIngredient);

        verify(expectedIngredient,foundIngredient);
    }

    /**
     * Test getIngredient(code).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetIngredientByName() throws SQLException {
        Ingredient expectedIngredient = new IngredientImpl(ID, NAME, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(NAME);
        Mockito.when(resultSet.getString(3)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getString(4)).thenReturn(NOTES);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Ingredient foundIngredient = dbIngredient.getIngredient(NAME);

        assertNotNull(foundIngredient);

        verify(expectedIngredient,foundIngredient);
    }

    /**
     * Test getIngredients().
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetIngredients() throws SQLException {
        Ingredient expectedIngredient1 = new IngredientImpl(ID, NAME, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
        Ingredient expectedIngredient2 = new IngredientImpl(ID+1, NAME+ID, DESCRIPTION+ID, NOTES+ID,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID).thenReturn(ID+1);
        Mockito.when(resultSet.getString(2)).thenReturn(NAME).thenReturn(NAME+ID);
        Mockito.when(resultSet.getString(3)).thenReturn(DESCRIPTION).thenReturn(DESCRIPTION+ID);
        Mockito.when(resultSet.getString(4)).thenReturn(NOTES).thenReturn(NOTES+ID);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE).thenReturn(LAST_UPDATED_DATE);

        List<Ingredient> foundIngredientList = dbIngredient.getIngredients();

        assertNotNull(foundIngredientList);
        assertTrue(foundIngredientList.size() == 2);

        verify(expectedIngredient1,foundIngredientList.get(0));
        verify(expectedIngredient2,foundIngredientList.get(1));
    }

    /**
     * Test createRecord(ingredient).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        Ingredient expectedIngredient = new IngredientImpl(ID, NAME, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbIngredient.createRecord(expectedIngredient);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, NOTES);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(4, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(5, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(6, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(7, LAST_UPDATED_BY);
    }

    /**
     * Test createRecords(ingredientList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<Ingredient> expectedIngredientList = new LinkedList<Ingredient>();
        expectedIngredientList.add(new IngredientImpl(ID, NAME, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedIngredientList.add(new IngredientImpl(ID+1, NAME+ID, DESCRIPTION+ID, NOTES+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbIngredient.createRecords(expectedIngredientList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DESCRIPTION+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, NOTES+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, NOTES);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(4, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(5, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(6, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(7, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecord(ingredient).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        Ingredient expectedIngredient = new IngredientImpl(ID, NAME, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbIngredient.updateRecord(expectedIngredient);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, NOTES);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(4, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(5, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(6, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, ID);
    }

    /**
     * Test updateRecords(ingredientList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecords() throws SQLException {
        List<Ingredient> expectedIngredientList = new LinkedList<Ingredient>();
        expectedIngredientList.add(new IngredientImpl(ID, NAME, DESCRIPTION, NOTES,
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedIngredientList.add(new IngredientImpl(ID+1, NAME+ID, DESCRIPTION+ID, NOTES+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbIngredient.updateRecords(expectedIngredientList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, NAME+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DESCRIPTION+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, NOTES+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, NOTES);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(4, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(5, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(6, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, ID+1);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Check if both given Ingredient rows match.
     * 
     * @param expectedIngredient Ingredient
     * @param foundIngredient Ingredient
     */
    private void verify(Ingredient expectedIngredient, Ingredient foundIngredient) {
        assertNotNull(foundIngredient);
        assertEquals(expectedIngredient.getId(),foundIngredient.getId());
        assertEquals(expectedIngredient.getName(),foundIngredient.getName());
        assertEquals(expectedIngredient.getDescription(),foundIngredient.getDescription());
        assertEquals(expectedIngredient.getNotes(),foundIngredient.getNotes());
        assertEquals(expectedIngredient.isDeleted(),foundIngredient.isDeleted());
        assertEquals(expectedIngredient.isShow(),foundIngredient.isShow());
        assertEquals(expectedIngredient.getCreatedBy(),foundIngredient.getCreatedBy());
        assertEquals(expectedIngredient.getCreatedDate(),foundIngredient.getCreatedDate());
        assertEquals(expectedIngredient.getLastUpdatedBy(),foundIngredient.getLastUpdatedBy());
        assertEquals(expectedIngredient.getLastUpdatedDate(),foundIngredient.getLastUpdatedDate());
    }
}
