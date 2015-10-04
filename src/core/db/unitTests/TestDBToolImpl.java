package core.db.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import core.db.impl.DBToolImpl;
import core.db.interfaces.DBTool;
import core.tables.impl.ToolImpl;
import core.tables.interfaces.Tool;
import core.tables.interfaces.User;

/**
 * Testing DBTool implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBToolImpl {
    /**
     * DBTool handle.
     */
    private DBTool dbTool;
    
    /** DATA **/
    private final int ID = 231;
    private final String TOOL_CD = "My Tool code";
    private final String DISPLAY_NAME = "My display name";
    private final String DESCRIPTION = "My description";
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
        dbTool = new DBToolImpl(session);
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
     * Test getTool(id).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetToolById() throws SQLException {
        Tool expectedTool = new ToolImpl(ID, TOOL_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(TOOL_CD);
        Mockito.when(resultSet.getString(3)).thenReturn(DISPLAY_NAME);
        Mockito.when(resultSet.getString(4)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Tool foundTool = dbTool.getTool(ID);

        assertNotNull(foundTool);

        verify(expectedTool,foundTool);
    }

    /**
     * Test getTool(code).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetToolByCode() throws SQLException {
        Tool expectedTool = new ToolImpl(ID, TOOL_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(TOOL_CD);
        Mockito.when(resultSet.getString(3)).thenReturn(DISPLAY_NAME);
        Mockito.when(resultSet.getString(4)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Tool foundTool = dbTool.getTool(TOOL_CD);

        assertNotNull(foundTool);

        verify(expectedTool,foundTool);
    }

    /**
     * Test createRecord(tool).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        Tool expectedTool = new ToolImpl(ID, TOOL_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbTool.createRecord(expectedTool);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, TOOL_CD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DISPLAY_NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(4, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(5, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(6, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(7, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test createRecords(toolList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<Tool> expectedToolList = new LinkedList<Tool>();
        expectedToolList.add(new ToolImpl(ID, TOOL_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedToolList.add(new ToolImpl(ID+1, TOOL_CD+ID, DISPLAY_NAME+ID, DESCRIPTION+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbTool.createRecords(expectedToolList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, TOOL_CD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, TOOL_CD+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DISPLAY_NAME+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DISPLAY_NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, DESCRIPTION+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(4, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(5, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(6, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(7, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecord(tool).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        Tool expectedTool = new ToolImpl(ID, TOOL_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbTool.updateRecord(expectedTool);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, TOOL_CD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DISPLAY_NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(4, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(5, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(6, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecords(toolList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecords() throws SQLException {
        List<Tool> expectedToolList = new LinkedList<Tool>();
        expectedToolList.add(new ToolImpl(ID, TOOL_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedToolList.add(new ToolImpl(ID+1, TOOL_CD+ID, DISPLAY_NAME+ID, DESCRIPTION+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbTool.updateRecords(expectedToolList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, TOOL_CD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, TOOL_CD+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DISPLAY_NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(2, DISPLAY_NAME+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, DESCRIPTION+ID);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(4, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(5, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(6, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, ID+1);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Check if both given Tool rows match.
     * 
     * @param expectedTool Tool
     * @param foundTool Tool
     */
    private void verify(Tool expectedTool, Tool foundTool) {
        assertNotNull(foundTool);
        assertEquals(expectedTool.getId(),foundTool.getId());
        assertEquals(expectedTool.getToolCd(),foundTool.getToolCd());
        assertEquals(expectedTool.getDisplayName(),foundTool.getDisplayName());
        assertEquals(expectedTool.getDescription(),foundTool.getDescription());
        assertEquals(expectedTool.isDeleted(),foundTool.isDeleted());
        assertEquals(expectedTool.isShow(),foundTool.isShow());
        assertEquals(expectedTool.getCreatedBy(),foundTool.getCreatedBy());
        assertEquals(expectedTool.getCreatedDate(),foundTool.getCreatedDate());
        assertEquals(expectedTool.getLastUpdatedBy(),foundTool.getLastUpdatedBy());
        assertEquals(expectedTool.getLastUpdatedDate(),foundTool.getLastUpdatedDate());
    }
}
