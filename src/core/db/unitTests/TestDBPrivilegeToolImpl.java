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

import mySQL.ConnectDB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import web.interfaces.Session;
import core.db.impl.DBPrivilegeToolImpl;
import core.db.interfaces.DBPrivilegeTool;
import core.tables.impl.PrivilegeToolImpl;
import core.tables.interfaces.PrivilegeTool;
import core.tables.interfaces.User;

/**
 * Testing DBPrivilegeTool implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBPrivilegeToolImpl {
    /**
     * DBPrivilegeTool handle.
     */
    private DBPrivilegeTool dbPrivilegeTool;
    
    /** DATA **/
    private final int ID = 231;
    private final int PRIVILEGE_ID = 123;
    private final int STATUS_ID = 5;
    private final boolean SHOW = true;
    private final boolean DELETED = false;
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
        dbPrivilegeTool = new DBPrivilegeToolImpl(session);
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
     * Test getPrivilegeToolId(id).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetPrivilegeToolId() throws SQLException {
        PrivilegeTool expectedPrivilegeTool = new PrivilegeToolImpl(ID, PRIVILEGE_ID, STATUS_ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getInt(2)).thenReturn(PRIVILEGE_ID);
        Mockito.when(resultSet.getInt(3)).thenReturn(STATUS_ID);
        Mockito.when(resultSet.getBoolean(4)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(6)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(7)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(8)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(9)).thenReturn(LAST_UPDATED_DATE);

        PrivilegeTool foundPrivilegeTool = dbPrivilegeTool.getPrivilegeTool(ID);

        assertNotNull(foundPrivilegeTool);

        verify(expectedPrivilegeTool,foundPrivilegeTool);
    }

    /**
     * Test createRecord(privilegeTool).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        PrivilegeTool expectedPrivilegeTool = new PrivilegeToolImpl(ID, PRIVILEGE_ID, STATUS_ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbPrivilegeTool.createRecord(expectedPrivilegeTool);

        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, STATUS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(3, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(4, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(5, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(6, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test createRecords(privilegeToolList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<PrivilegeTool> expectedPrivilegeToolList = new LinkedList<PrivilegeTool>();
        expectedPrivilegeToolList.add(new PrivilegeToolImpl(ID, PRIVILEGE_ID, STATUS_ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedPrivilegeToolList.add(new PrivilegeToolImpl(ID+1, PRIVILEGE_ID+ID, STATUS_ID+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbPrivilegeTool.createRecords(expectedPrivilegeToolList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, PRIVILEGE_ID+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, STATUS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, STATUS_ID+ID);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(3, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(4, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(5, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(6, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecord(privilegeTool).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        PrivilegeTool expectedPrivilegeTool = new PrivilegeToolImpl(ID, PRIVILEGE_ID, STATUS_ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbPrivilegeTool.updateRecord(expectedPrivilegeTool);

        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(1, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(2, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(4, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(5, STATUS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecords(privilegeList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecords() throws SQLException {
        List<PrivilegeTool> expectedPrivilegeToolList = new LinkedList<PrivilegeTool>();
        expectedPrivilegeToolList.add(new PrivilegeToolImpl(ID, PRIVILEGE_ID, STATUS_ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedPrivilegeToolList.add(new PrivilegeToolImpl(ID+1, PRIVILEGE_ID+ID, STATUS_ID+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        

        dbPrivilegeTool.updateRecords(expectedPrivilegeToolList);

        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(1, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(2, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(3, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(4, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(4, PRIVILEGE_ID+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(5, STATUS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(5, STATUS_ID+ID);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Check if both given PrivilegeTool rows match.
     * 
     * @param expectedPrivilegeTool PrivilegeTool
     * @param foundPrivilegeTool PrivilegeTool
     */
    private void verify(PrivilegeTool expectedPrivilegeTool, PrivilegeTool foundPrivilegeTool) {
        assertNotNull(foundPrivilegeTool);
        assertEquals(expectedPrivilegeTool.getId(),foundPrivilegeTool.getId());
        assertEquals(expectedPrivilegeTool.getPrivilegeId(),foundPrivilegeTool.getPrivilegeId());
        assertEquals(expectedPrivilegeTool.getToolId(),foundPrivilegeTool.getToolId());
        assertEquals(expectedPrivilegeTool.isDeleted(),foundPrivilegeTool.isDeleted());
        assertEquals(expectedPrivilegeTool.isShow(),foundPrivilegeTool.isShow());
        assertEquals(expectedPrivilegeTool.getCreatedBy(),foundPrivilegeTool.getCreatedBy());
        assertEquals(expectedPrivilegeTool.getCreatedDate(),foundPrivilegeTool.getCreatedDate());
        assertEquals(expectedPrivilegeTool.getLastUpdatedBy(),foundPrivilegeTool.getLastUpdatedBy());
        assertEquals(expectedPrivilegeTool.getLastUpdatedDate(),foundPrivilegeTool.getLastUpdatedDate());
    }
}
