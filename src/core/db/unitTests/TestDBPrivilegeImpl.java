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
import core.db.impl.DBPrivilegeImpl;
import core.db.interfaces.DBPrivilege;
import core.tables.impl.PrivilegeImpl;
import core.tables.interfaces.Privilege;
import core.tables.interfaces.User;

/**
 * Testing DBPrivilege implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBPrivilegeImpl {
    /**
     * DBPrivilege handle.
     */
    private DBPrivilege dbPrivilege;
    
    /** DATA **/
    private final int ID = 231;
    private final String DISPLAY_NAME = "My display name";
    private final int STATUS_ID = 5;
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
        dbPrivilege = new DBPrivilegeImpl(session);
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
     * Test getPrivilege(id).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetPrivilegeId() throws SQLException {
        Privilege expectedPrivilege = new PrivilegeImpl(ID, DISPLAY_NAME, STATUS_ID, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(DISPLAY_NAME);
        Mockito.when(resultSet.getInt(3)).thenReturn(STATUS_ID);
        Mockito.when(resultSet.getString(4)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Privilege foundPrivilege = dbPrivilege.getPrivilege(ID);

        assertNotNull(foundPrivilege);

        verify(expectedPrivilege,foundPrivilege);
    }

    /**
     * Test createRecord(privilege).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        Privilege expectedPrivilege = new PrivilegeImpl(ID, DISPLAY_NAME, STATUS_ID, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbPrivilege.createRecord(expectedPrivilege);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, DISPLAY_NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, STATUS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(4, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(5, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(6, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(7, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test createRecords(privilegeList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<Privilege> expectedPrivilegeList = new LinkedList<Privilege>();
        expectedPrivilegeList.add(new PrivilegeImpl(ID, DISPLAY_NAME, STATUS_ID, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedPrivilegeList.add(new PrivilegeImpl(ID+1, DISPLAY_NAME+ID, STATUS_ID+ID, DESCRIPTION+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbPrivilege.createRecords(expectedPrivilegeList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, DISPLAY_NAME+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, DISPLAY_NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, STATUS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, STATUS_ID+ID);
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
     * Test updateRecord(privilege).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        Privilege expectedPrivilege = new PrivilegeImpl(ID, DISPLAY_NAME, STATUS_ID, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbPrivilege.updateRecord(expectedPrivilege);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, DISPLAY_NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, STATUS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, DESCRIPTION);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(4, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(5, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(6, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, ID);
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
        List<Privilege> expectedPrivilegeList = new LinkedList<Privilege>();
        expectedPrivilegeList.add(new PrivilegeImpl(ID, DISPLAY_NAME, STATUS_ID, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedPrivilegeList.add(new PrivilegeImpl(ID+1, DISPLAY_NAME+ID, STATUS_ID+ID, DESCRIPTION+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        

        dbPrivilege.updateRecords(expectedPrivilegeList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, DISPLAY_NAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, DISPLAY_NAME+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, STATUS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, STATUS_ID+ID);
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
     * Check if both given Privilege rows match.
     * 
     * @param expectedPrivilege Privilege
     * @param foundPrivilege Privilege
     */
    private void verify(Privilege expectedPrivilege, Privilege foundPrivilege) {
        assertNotNull(foundPrivilege);
        assertEquals(expectedPrivilege.getId(),foundPrivilege.getId());
        assertEquals(expectedPrivilege.getDisplayName(),foundPrivilege.getDisplayName());
        assertEquals(expectedPrivilege.getStatusId(),foundPrivilege.getStatusId());
        assertEquals(expectedPrivilege.getDescription(),foundPrivilege.getDescription());
        assertEquals(expectedPrivilege.isDeleted(),foundPrivilege.isDeleted());
        assertEquals(expectedPrivilege.isShow(),foundPrivilege.isShow());
        assertEquals(expectedPrivilege.getCreatedBy(),foundPrivilege.getCreatedBy());
        assertEquals(expectedPrivilege.getCreatedDate(),foundPrivilege.getCreatedDate());
        assertEquals(expectedPrivilege.getLastUpdatedBy(),foundPrivilege.getLastUpdatedBy());
        assertEquals(expectedPrivilege.getLastUpdatedDate(),foundPrivilege.getLastUpdatedDate());
    }
}
