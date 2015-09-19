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
import constants.EnumUserStatus;
import core.db.impl.DBUserImpl;
import core.db.interfaces.DBUser;
import core.tables.impl.UserImpl;
import core.tables.interfaces.User;

/**
 * Testing DBUser implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBUserImpl {
    /**
     * DBUser handle.
     */
    private DBUser dbUser;
    
    /** DATA **/
    private final int ID = 231;
    private final String USERNAME = "username";
    private final byte[] PASSWORD = new byte[256];
    private final int PRIVILEGE_ID = 1;
    private final String STATUS = EnumUserStatus.ACTIVE.toString();
    private final String CREATED_BY = "Me";
    private final Timestamp CREATED_DATE = Timestamp.valueOf("2015-09-16 21:51:12");
    private final String LAST_UPDATED_BY = "Meself";
    private final Timestamp LAST_UPDATED_DATE = Timestamp.valueOf("2015-09-16 21:51:12");
    private final User USER = new UserImpl(ID, USERNAME, PASSWORD, PRIVILEGE_ID, STATUS, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

    /** MOCKITO **/
    private Session session = Mockito.mock(Session.class);
    private ConnectDB connect = Mockito.mock(ConnectDB.class);
    private Connection connection = Mockito.mock(Connection.class);
    private PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    private ResultSet resultSet = Mockito.mock(ResultSet.class);

    /**
     * Preparations for next test.
     */
    @Before
    public void before() {
        
        Mockito.when(session.isLoggedIn()).thenReturn(true);
        Mockito.when(session.isUserValidated()).thenReturn(true);
        Mockito.when(session.getUser()).thenReturn(USER);

        Mockito.when(session.getDB()).thenReturn(connect);
        Mockito.when(connect.getConnection(Mockito.anyString())).thenReturn(connection);
        try {
            Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
            Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUser = new DBUserImpl(session, USERNAME);
    }
    
    /**
     * Check if methods are always called at the end.
     * 
     * @throws SQLException 
     */
    @After
    public void after() throws SQLException {
//        Mockito.verify(preparedStatement, Mockito.times(1)).close();
    }

    /**
     * Test getUser().
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetUser() throws SQLException {
        User expectedUser = new UserImpl(ID, USERNAME, PASSWORD, PRIVILEGE_ID, STATUS,  
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(USERNAME);
        Mockito.when(resultSet.getBytes(3)).thenReturn(PASSWORD);
        Mockito.when(resultSet.getInt(4)).thenReturn(PRIVILEGE_ID);
        Mockito.when(resultSet.getString(5)).thenReturn(STATUS);
        Mockito.when(resultSet.getString(6)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(7)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(8)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(9)).thenReturn(LAST_UPDATED_DATE);

        dbUser = new DBUserImpl(session, USERNAME);
        User foundUser = dbUser.getUser();

        assertNotNull(foundUser);

        verify(expectedUser,foundUser);
    }

    /**
     * Test savePassword(password).
     * 
     * @throws SQLException 
     */
    @Test
    public void testSavePassword() throws SQLException {
        byte [] newPassword = new byte[123];
        User expectedUser = new UserImpl(ID, USERNAME, newPassword, PRIVILEGE_ID, STATUS, 
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(USERNAME);
        Mockito.when(resultSet.getBytes(3)).thenReturn(newPassword);
        Mockito.when(resultSet.getInt(4)).thenReturn(PRIVILEGE_ID);
        Mockito.when(resultSet.getString(5)).thenReturn(STATUS);
        Mockito.when(resultSet.getString(6)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(7)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(8)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(9)).thenReturn(LAST_UPDATED_DATE);

        dbUser = new DBUserImpl(session, USERNAME);
        dbUser.savePassword(newPassword);
        User foundUser = dbUser.getUser();

        assertNotNull(foundUser);

        verify(expectedUser,foundUser);
    }

    /**
     * Test createRecord(user).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        User expectedUser = new UserImpl(ID, USERNAME, PASSWORD, PRIVILEGE_ID, STATUS, 
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbUser = new DBUserImpl(session, USERNAME);
        dbUser.createRecord(expectedUser);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, USERNAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, STATUS);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(5, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test createRecords(userList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<User> expectedUserList = new LinkedList<User>();
        expectedUserList.add(new UserImpl(ID, USERNAME, PASSWORD, PRIVILEGE_ID, 
                STATUS, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedUserList.add(new UserImpl(ID+1, USERNAME+ID, PASSWORD, PRIVILEGE_ID+ID, 
                STATUS, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbUser.createRecords(expectedUserList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, USERNAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, USERNAME+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, PRIVILEGE_ID+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(3, STATUS);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(4, CREATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(5, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecord(user).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        User expectedUser = new UserImpl(ID, USERNAME, PASSWORD, PRIVILEGE_ID, 
                STATUS, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbUser.updateRecord(expectedUser);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, USERNAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(3, STATUS);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(4, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(5, ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Test updateRecords(userList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecords() throws SQLException {
        List<User> expectedUserList = new LinkedList<User>();
        expectedUserList.add(new UserImpl(ID, USERNAME, PASSWORD, PRIVILEGE_ID, 
                STATUS, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedUserList.add(new UserImpl(ID+1, USERNAME+ID, PASSWORD, PRIVILEGE_ID+ID, 
                STATUS, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        

        dbUser.updateRecords(expectedUserList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, USERNAME);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, USERNAME+ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, PRIVILEGE_ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(2, PRIVILEGE_ID+ID);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(3, STATUS);
        Mockito.verify(preparedStatement, Mockito.times(2)).setString(4, LAST_UPDATED_BY);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(5, ID);
        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(5, ID+1);
        Mockito.verify(preparedStatement, Mockito.times(2)).addBatch();
        Mockito.verify(preparedStatement, Mockito.times(1)).executeBatch();
    }

    /**
     * Check if both given User rows match.
     * 
     * @param expectedUser User
     * @param foundUser User
     */
    private void verify(User expectedUser, User foundUser) {
        assertNotNull(foundUser);
        assertEquals(expectedUser.getId(),foundUser.getId());
        assertEquals(expectedUser.getUsername(),foundUser.getUsername());
        assertEquals(expectedUser.getPassword(),foundUser.getPassword());
        assertEquals(expectedUser.getStatus(),foundUser.getStatus());
        assertEquals(expectedUser.getPrivilegeId(),foundUser.getPrivilegeId());
        assertEquals(expectedUser.getCreatedBy(),foundUser.getCreatedBy());
        assertEquals(expectedUser.getCreatedDate(),foundUser.getCreatedDate());
        assertEquals(expectedUser.getLastUpdatedBy(),foundUser.getLastUpdatedBy());
        assertEquals(expectedUser.getLastUpdatedDate(),foundUser.getLastUpdatedDate());
    }
}
