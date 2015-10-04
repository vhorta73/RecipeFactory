package core.unitTests;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import web.interfaces.Session;

import com.mysql.jdbc.Connection;

import constants.EnumAccess;
import constants.EnumFeature;
import constants.EnumTool;
import core.ConnectDB;
import core.impl.UserPrivilegeImpl;
import core.interfaces.BEUserPrivilege;
import core.tables.interfaces.User;

/**
 * Testing UserPrivilege implementation.
 * 
 * @author Vasco
 *
 */
public class TestUserPrivilegeImpl {
    /**
     * UserPrivilege handle.
     */
    private BEUserPrivilege userPrivilege;
    
    /** DATA **/
    private final int PRIVILEGE_ID = 10;
    private final int TOOL_1 = 1;
    private final String TOOL_1_CD = EnumTool.BOX_MANAGEMENT.toString().toUpperCase();
    private final int FEATURE_1 = 1;
    private final String FEATURE_1_CD = EnumFeature.DELETE.toString().toUpperCase();
    private final int ACCESS_1 = 1;
    private final String ACCESS_1_CD = EnumAccess.FULL_ACCESS.toString().toUpperCase();

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
        // Pass login and privilege id and other deep session requests
        Mockito.when(session.getUser()).thenReturn(user);
        Mockito.when(session.isLoggedIn()).thenReturn(true);
        Mockito.when(session.getDB()).thenReturn(connect);
        Mockito.when(connect.getConnection(Mockito.anyString())).thenReturn(connection);
        Mockito.when(user.getPrivilegeId()).thenReturn(PRIVILEGE_ID);
        try {
            Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
            Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
            // Read one toolFeatureAccess record
            Mockito.when(resultSet.next())
            .thenReturn(true).thenReturn(false)  // ToolFeatureAccess
            .thenReturn(true).thenReturn(false)  // Tool
            .thenReturn(true).thenReturn(false)  // Feature
            .thenReturn(true).thenReturn(false); // Access

            Mockito.when(resultSet.getInt(1)).thenReturn(PRIVILEGE_ID).thenReturn(TOOL_1).thenReturn(FEATURE_1).thenReturn(ACCESS_1);
            Mockito.when(resultSet.getInt(2)).thenReturn(TOOL_1);
            Mockito.when(resultSet.getInt(3)).thenReturn(FEATURE_1);
            Mockito.when(resultSet.getInt(4)).thenReturn(ACCESS_1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Check if methods are always called at the end.
     * 
     * @throws SQLException 
     */
    @After
    public void after() throws SQLException {
        Mockito.verify(preparedStatement, Mockito.times(4)).close();
    }

    /**
     * Test can(EnumTool).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCanTool() throws SQLException {
        // Tool then Feature then Access
        Mockito.when(resultSet.getString(2)).thenReturn(TOOL_1_CD).thenReturn(FEATURE_1_CD).thenReturn(ACCESS_1_CD);

        userPrivilege = new UserPrivilegeImpl(session);

        boolean expected = true;
        boolean found = userPrivilege.can(EnumTool.BOX_MANAGEMENT);

        assertNotNull(found);

        assertEquals(expected,found);
    }

    /**
     * Test can(EnumTool) when false.
     * 
     * @throws SQLException 
     */
    @Test
    public void testCanToolFalse() throws SQLException {
        // Tool then Feature then Access
        Mockito.when(resultSet.getString(2)).thenReturn("Unknown code").thenReturn(FEATURE_1_CD).thenReturn(ACCESS_1_CD);

        userPrivilege = new UserPrivilegeImpl(session);

        boolean found = userPrivilege.can(EnumTool.BOX_MANAGEMENT);
        assertNotNull(found);
        assertFalse(found);
    }

    /**
     * Test can(EnumTool,EnumFeature).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCanToolFeature() throws SQLException {
        // Tool then Feature then Access
        Mockito.when(resultSet.getString(2)).thenReturn(TOOL_1_CD).thenReturn(FEATURE_1_CD).thenReturn(ACCESS_1_CD);

        userPrivilege = new UserPrivilegeImpl(session);

        boolean expected = true;
        boolean found = userPrivilege.can(EnumTool.BOX_MANAGEMENT, EnumFeature.DELETE);

        assertNotNull(found);
        assertEquals(expected,found);
    }

    /**
     * Test can(EnumTool,EnumFeature) false.
     * 
     * @throws SQLException 
     */
    @Test
    public void testCanToolFeatureFalse() throws SQLException {
        // Tool then Feature then Access
        Mockito.when(resultSet.getString(2)).thenReturn(TOOL_1_CD).thenReturn("Unknwon feature").thenReturn(ACCESS_1_CD);

        userPrivilege = new UserPrivilegeImpl(session);

        boolean found = userPrivilege.can(EnumTool.BOX_MANAGEMENT, EnumFeature.DELETE);
        assertNotNull(found);
        assertFalse(found);
    }

    /**
     * Test can(EnumTool,EnumFeature,EnumAccess).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCanToolFeatureAccess() throws SQLException {
        // Tool then Feature then Access
        Mockito.when(resultSet.getString(2)).thenReturn(TOOL_1_CD).thenReturn(FEATURE_1_CD).thenReturn(ACCESS_1_CD);

        userPrivilege = new UserPrivilegeImpl(session);

        boolean expected = true;
        boolean found = userPrivilege.can(EnumTool.BOX_MANAGEMENT, EnumFeature.DELETE, EnumAccess.FULL_ACCESS);

        assertNotNull(found);
        assertEquals(expected,found);
    }

    /**
     * Test can(EnumTool,EnumFeature,EnumAccess) false.
     * 
     * @throws SQLException 
     */
    @Test
    public void testCanToolFeatureAccessFalse() throws SQLException {
        // Tool then Feature then Access
        Mockito.when(resultSet.getString(2)).thenReturn(TOOL_1_CD).thenReturn(FEATURE_1_CD).thenReturn("Unknwon access");

        userPrivilege = new UserPrivilegeImpl(session);

        boolean found = userPrivilege.can(EnumTool.BOX_MANAGEMENT, EnumFeature.DELETE, EnumAccess.FULL_ACCESS);
        assertNotNull(found);
        assertFalse(found);
    }
}
