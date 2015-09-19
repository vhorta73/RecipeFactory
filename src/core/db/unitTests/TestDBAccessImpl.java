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
import core.db.impl.DBAccessImpl;
import core.db.interfaces.DBAccess;
import core.tables.impl.AccessImpl;
import core.tables.interfaces.Access;
import core.tables.interfaces.User;

/**
 * Testing DBAccess implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBAccessImpl {
    /**
     * DBAccess handle.
     */
    private DBAccess dbAccess;
    
    /** DATA **/
    private final int ID = 231;
    private final String ACCESS_CD = "My Access code";
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
        dbAccess = new DBAccessImpl(session);
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
     * Test getAccess(id).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetAccessById() throws SQLException {
        Access expectedAccess = new AccessImpl(ID, ACCESS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(ACCESS_CD);
        Mockito.when(resultSet.getString(3)).thenReturn(DISPLAY_NAME);
        Mockito.when(resultSet.getString(4)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Access foundAccess = dbAccess.getAccess(ID);

        assertNotNull(foundAccess);

        verify(expectedAccess,foundAccess);
    }

    /**
     * Test getAccess(code).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetAccessByCode() throws SQLException {
        Access expectedAccess = new AccessImpl(ID, ACCESS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(ACCESS_CD);
        Mockito.when(resultSet.getString(3)).thenReturn(DISPLAY_NAME);
        Mockito.when(resultSet.getString(4)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Access foundAccess = dbAccess.getAccess(ACCESS_CD);

        assertNotNull(foundAccess);

        verify(expectedAccess,foundAccess);
    }

    /**
     * Test createRecord(access).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        Access expectedAccess = new AccessImpl(ID, ACCESS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbAccess.createRecord(expectedAccess);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, ACCESS_CD);
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
     * Test createRecords(accessList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<Access> expectedAccessList = new LinkedList<Access>();
        expectedAccessList.add(new AccessImpl(ID, ACCESS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedAccessList.add(new AccessImpl(ID+1, ACCESS_CD+ID, DISPLAY_NAME+ID, DESCRIPTION+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbAccess.createRecords(expectedAccessList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, ACCESS_CD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, ACCESS_CD+ID);
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
     * Test updateRecord(access).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        Access expectedAccess = new AccessImpl(ID, ACCESS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbAccess.updateRecord(expectedAccess);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, ACCESS_CD);
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
     * Test updateRecords(accessList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecords() throws SQLException {
        List<Access> expectedAccessList = new LinkedList<Access>();
        expectedAccessList.add(new AccessImpl(ID, ACCESS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedAccessList.add(new AccessImpl(ID+1, ACCESS_CD+ID, DISPLAY_NAME+ID, DESCRIPTION+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        

        dbAccess.updateRecords(expectedAccessList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, ACCESS_CD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, ACCESS_CD+ID);
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
     * Check if both given Access rows match.
     * 
     * @param expectedAccess Access
     * @param foundAccess Access
     */
    private void verify(Access expectedAccess, Access foundAccess) {
        assertNotNull(foundAccess);
        assertEquals(expectedAccess.getId(),foundAccess.getId());
        assertEquals(expectedAccess.getAccessCd(),foundAccess.getAccessCd());
        assertEquals(expectedAccess.getDisplayName(),foundAccess.getDisplayName());
        assertEquals(expectedAccess.getDescription(),foundAccess.getDescription());
        assertEquals(expectedAccess.isDeleted(),foundAccess.isDeleted());
        assertEquals(expectedAccess.isShow(),foundAccess.isShow());
        assertEquals(expectedAccess.getCreatedBy(),foundAccess.getCreatedBy());
        assertEquals(expectedAccess.getCreatedDate(),foundAccess.getCreatedDate());
        assertEquals(expectedAccess.getLastUpdatedBy(),foundAccess.getLastUpdatedBy());
        assertEquals(expectedAccess.getLastUpdatedDate(),foundAccess.getLastUpdatedDate());
    }
}
