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
import core.db.impl.DBPrivilegeToolFeatureAccessImpl;
import core.db.interfaces.DBPrivilegeToolFeatureAccess;
import core.tables.impl.PrivilegeToolFeatureAccessImpl;
import core.tables.interfaces.PrivilegeToolFeatureAccess;
import core.tables.interfaces.User;

/**
 * Testing DBPrivilegeToolFeatureAccess implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBPrivilegeToolFeatureAccessImpl {
    /**
     * DBPrivilegeToolFeatureAccess handle.
     */
    private DBPrivilegeToolFeatureAccess dbPrivilegeToolFeatureAccess;
    
    /** DATA **/
    private final int PRIVILEGE_ID = 231;
    private final int TOOL_ID = 5;
    private final int FEATURE_ID = 4;
    private final int ACCESS_ID = 1;
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
        dbPrivilegeToolFeatureAccess = new DBPrivilegeToolFeatureAccessImpl(session);
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
     * Test getPrivilegeToolFeatureAccessesByPrivilegeId(id).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetPrivilegeToolFeatureAccessesByPrivilegeId() throws SQLException {
        List<PrivilegeToolFeatureAccess> expectedPrivilegeToolFeatureAccessList = new LinkedList<PrivilegeToolFeatureAccess>();
        expectedPrivilegeToolFeatureAccessList.add(new PrivilegeToolFeatureAccessImpl(
                PRIVILEGE_ID, TOOL_ID, FEATURE_ID, ACCESS_ID, SHOW, DELETED, 
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        expectedPrivilegeToolFeatureAccessList.add(new PrivilegeToolFeatureAccessImpl(
                PRIVILEGE_ID, TOOL_ID+1, FEATURE_ID+1, ACCESS_ID+1, SHOW, DELETED, 
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(PRIVILEGE_ID).thenReturn(PRIVILEGE_ID);
        Mockito.when(resultSet.getInt(2)).thenReturn(TOOL_ID).thenReturn(TOOL_ID+1);
        Mockito.when(resultSet.getInt(3)).thenReturn(FEATURE_ID).thenReturn(FEATURE_ID+1);
        Mockito.when(resultSet.getInt(4)).thenReturn(ACCESS_ID).thenReturn(ACCESS_ID+1);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE).thenReturn(LAST_UPDATED_DATE);

        List<PrivilegeToolFeatureAccess> foundPrivilegeToolFeatureAccessList = 
                dbPrivilegeToolFeatureAccess.getPrivilegeToolFeatureAccessesByPrivilegeId(PRIVILEGE_ID);

        assertNotNull(foundPrivilegeToolFeatureAccessList);
        assertEquals(expectedPrivilegeToolFeatureAccessList.size(), foundPrivilegeToolFeatureAccessList.size());

        for( int index = 0 ; index < expectedPrivilegeToolFeatureAccessList.size(); index++ ) {
            verify(expectedPrivilegeToolFeatureAccessList.get(index),foundPrivilegeToolFeatureAccessList.get(index));
        }
    }

    /**
     * Test createRecord(privilegeToolFeatureAccess).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        PrivilegeToolFeatureAccess expectedPrivilegeToolFeatureAccess = new PrivilegeToolFeatureAccessImpl(
                PRIVILEGE_ID, TOOL_ID, FEATURE_ID, ACCESS_ID, SHOW, DELETED, 
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbPrivilegeToolFeatureAccess.createRecord(expectedPrivilegeToolFeatureAccess);

        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, TOOL_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(3, FEATURE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(4, ACCESS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(5, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(6, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(7, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(8, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test createRecords(privilegeToolFeatureAccessList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<PrivilegeToolFeatureAccess> expectedPrivilegeToolFeatureAccessList = new LinkedList<PrivilegeToolFeatureAccess>();
        expectedPrivilegeToolFeatureAccessList.add(new PrivilegeToolFeatureAccessImpl(
                PRIVILEGE_ID, TOOL_ID, FEATURE_ID, ACCESS_ID, SHOW, DELETED, 
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedPrivilegeToolFeatureAccessList.add(new PrivilegeToolFeatureAccessImpl(
                PRIVILEGE_ID+1, TOOL_ID+PRIVILEGE_ID, FEATURE_ID+PRIVILEGE_ID, ACCESS_ID+PRIVILEGE_ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbPrivilegeToolFeatureAccess.createRecords(expectedPrivilegeToolFeatureAccessList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, PRIVILEGE_ID+1);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, TOOL_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, TOOL_ID+PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(3, FEATURE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(3, FEATURE_ID+PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(4, ACCESS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(4, ACCESS_ID+PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(5, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(6, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(7, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(8, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecord(privilegeToolFeatureAccess).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        PrivilegeToolFeatureAccess expectedPrivilegeToolFeatureAccess = new PrivilegeToolFeatureAccessImpl(
                PRIVILEGE_ID, TOOL_ID, FEATURE_ID, ACCESS_ID, SHOW, DELETED, 
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbPrivilegeToolFeatureAccess.updateRecord(expectedPrivilegeToolFeatureAccess);

        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, ACCESS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(2, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(1)).setBoolean(3, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(5, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(6, TOOL_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, FEATURE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecords(privilegeToolFeatureAccessList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecords() throws SQLException {
        List<PrivilegeToolFeatureAccess> expectedPrivilegeToolFeatureAccessList = new LinkedList<PrivilegeToolFeatureAccess>();
        expectedPrivilegeToolFeatureAccessList.add(new PrivilegeToolFeatureAccessImpl(
                PRIVILEGE_ID, TOOL_ID, FEATURE_ID, ACCESS_ID, SHOW, DELETED, 
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedPrivilegeToolFeatureAccessList.add(new PrivilegeToolFeatureAccessImpl(
                PRIVILEGE_ID+1, TOOL_ID+PRIVILEGE_ID, FEATURE_ID+PRIVILEGE_ID, ACCESS_ID+PRIVILEGE_ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbPrivilegeToolFeatureAccess.updateRecords(expectedPrivilegeToolFeatureAccessList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, ACCESS_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(1, ACCESS_ID+PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(2, SHOW);
        Mockito.verify(preparedStatement, Mockito.times(2)).setBoolean(3, DELETED);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(4, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(5, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(5, PRIVILEGE_ID+1);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(6, TOOL_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(6, TOOL_ID+PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, FEATURE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(7, FEATURE_ID+PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Check if both given Privilege rows match.
     * 
     * @param expectedPrivilegeToolFeatureAccess Privilege
     * @param foundPrivilegeToolFeatureAccess Privilege
     */
    private void verify(PrivilegeToolFeatureAccess expectedPrivilegeToolFeatureAccess, PrivilegeToolFeatureAccess foundPrivilegeToolFeatureAccess) {
        assertNotNull(foundPrivilegeToolFeatureAccess);
        assertEquals(expectedPrivilegeToolFeatureAccess.getPrivilegeId(),foundPrivilegeToolFeatureAccess.getPrivilegeId());
        assertEquals(expectedPrivilegeToolFeatureAccess.getToolId(),foundPrivilegeToolFeatureAccess.getToolId());
        assertEquals(expectedPrivilegeToolFeatureAccess.getFeatureId(),foundPrivilegeToolFeatureAccess.getFeatureId());
        assertEquals(expectedPrivilegeToolFeatureAccess.getAccessId(),foundPrivilegeToolFeatureAccess.getAccessId());
        assertEquals(expectedPrivilegeToolFeatureAccess.isShow(),foundPrivilegeToolFeatureAccess.isShow());
        assertEquals(expectedPrivilegeToolFeatureAccess.isDeleted(),foundPrivilegeToolFeatureAccess.isDeleted());
        assertEquals(expectedPrivilegeToolFeatureAccess.getCreatedBy(),foundPrivilegeToolFeatureAccess.getCreatedBy());
        assertEquals(expectedPrivilegeToolFeatureAccess.getCreatedDate(),foundPrivilegeToolFeatureAccess.getCreatedDate());
        assertEquals(expectedPrivilegeToolFeatureAccess.getLastUpdatedBy(),foundPrivilegeToolFeatureAccess.getLastUpdatedBy());
        assertEquals(expectedPrivilegeToolFeatureAccess.getLastUpdatedDate(),foundPrivilegeToolFeatureAccess.getLastUpdatedDate());
    }
}
