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
import core.db.impl.DBStatusImpl;
import core.db.interfaces.DBStatus;
import core.tables.impl.StatusImpl;
import core.tables.interfaces.Status;
import core.tables.interfaces.User;

/**
 * Testing DBStatus implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBStatusImpl {
    /**
     * DBStatus handle.
     */
    private DBStatus dbStatus;
    
    /** DATA **/
    private final int ID = 231;
    private final String STATUS_CD = "My Status code";
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
        dbStatus = new DBStatusImpl(session);
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
     * Test getStatus(id).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetStatusById() throws SQLException {
        Status expectedStatus = new StatusImpl(ID, STATUS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(STATUS_CD);
        Mockito.when(resultSet.getString(3)).thenReturn(DISPLAY_NAME);
        Mockito.when(resultSet.getString(4)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Status foundStatus = dbStatus.getStatus(ID);

        assertNotNull(foundStatus);

        verify(expectedStatus,foundStatus);
    }

    /**
     * Test getStatus(code).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetStatusByCode() throws SQLException {
        Status expectedStatus = new StatusImpl(ID, STATUS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(STATUS_CD);
        Mockito.when(resultSet.getString(3)).thenReturn(DISPLAY_NAME);
        Mockito.when(resultSet.getString(4)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Status foundStatus = dbStatus.getStatus(STATUS_CD);

        assertNotNull(foundStatus);

        verify(expectedStatus,foundStatus);
    }

    /**
     * Test createRecord(status).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        Status expectedStatus = new StatusImpl(ID, STATUS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbStatus.createRecord(expectedStatus);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, STATUS_CD);
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
     * Test createRecords(statusList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<Status> expectedStatusList = new LinkedList<Status>();
        expectedStatusList.add(new StatusImpl(ID, STATUS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedStatusList.add(new StatusImpl(ID+1, STATUS_CD+ID, DISPLAY_NAME+ID, DESCRIPTION+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbStatus.createRecords(expectedStatusList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, STATUS_CD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, STATUS_CD+ID);
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
     * Test updateRecord(status).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        Status expectedStatus = new StatusImpl(ID, STATUS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbStatus.updateRecord(expectedStatus);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, STATUS_CD);
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
     * Test updateRecords(statusList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecords() throws SQLException {
        List<Status> expectedStatusList = new LinkedList<Status>();
        expectedStatusList.add(new StatusImpl(ID, STATUS_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedStatusList.add(new StatusImpl(ID+1, STATUS_CD+ID, DISPLAY_NAME+ID, DESCRIPTION+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        

        dbStatus.updateRecords(expectedStatusList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, STATUS_CD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, STATUS_CD+ID);
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
     * Check if both given Status rows match.
     * 
     * @param expectedStatus Status
     * @param foundStatus Status
     */
    private void verify(Status expectedStatus, Status foundStatus) {
        assertNotNull(foundStatus);
        assertEquals(expectedStatus.getId(),foundStatus.getId());
        assertEquals(expectedStatus.getStatusCd(),foundStatus.getStatusCd());
        assertEquals(expectedStatus.getDisplayName(),foundStatus.getDisplayName());
        assertEquals(expectedStatus.getDescription(),foundStatus.getDescription());
        assertEquals(expectedStatus.isDeleted(),foundStatus.isDeleted());
        assertEquals(expectedStatus.isShow(),foundStatus.isShow());
        assertEquals(expectedStatus.getCreatedBy(),foundStatus.getCreatedBy());
        assertEquals(expectedStatus.getCreatedDate(),foundStatus.getCreatedDate());
        assertEquals(expectedStatus.getLastUpdatedBy(),foundStatus.getLastUpdatedBy());
        assertEquals(expectedStatus.getLastUpdatedDate(),foundStatus.getLastUpdatedDate());
    }
}
