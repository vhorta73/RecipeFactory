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
import core.db.impl.DBFeatureImpl;
import core.db.interfaces.DBFeature;
import core.tables.impl.FeatureImpl;
import core.tables.interfaces.Feature;
import core.tables.interfaces.User;

/**
 * Testing DBFeature implementation.
 * 
 * @author Vasco
 *
 */
public class TestDBFeatureImpl {
    /**
     * DBFeature handle.
     */
    private DBFeature dbFeature;
    
    /** DATA **/
    private final int ID = 231;
    private final String FEATURE_CD = "My Feature code";
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
        dbFeature = new DBFeatureImpl(session);
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
     * Test getFeature(id).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetFeatureById() throws SQLException {
        Feature expectedFeature = new FeatureImpl(ID, FEATURE_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(FEATURE_CD);
        Mockito.when(resultSet.getString(3)).thenReturn(DISPLAY_NAME);
        Mockito.when(resultSet.getString(4)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Feature foundFeature = dbFeature.getFeature(ID);

        assertNotNull(foundFeature);

        verify(expectedFeature,foundFeature);
    }

    /**
     * Test getFeature(code).
     * 
     * @throws SQLException 
     */
    @Test
    public void testGetFeatureByCode() throws SQLException {
        Feature expectedFeature = new FeatureImpl(ID, FEATURE_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(ID);
        Mockito.when(resultSet.getString(2)).thenReturn(FEATURE_CD);
        Mockito.when(resultSet.getString(3)).thenReturn(DISPLAY_NAME);
        Mockito.when(resultSet.getString(4)).thenReturn(DESCRIPTION);
        Mockito.when(resultSet.getBoolean(5)).thenReturn(SHOW);
        Mockito.when(resultSet.getBoolean(6)).thenReturn(DELETED);
        Mockito.when(resultSet.getString(7)).thenReturn(CREATED_BY);
        Mockito.when(resultSet.getTimestamp(8)).thenReturn(CREATED_DATE);
        Mockito.when(resultSet.getString(9)).thenReturn(LAST_UPDATED_BY);
        Mockito.when(resultSet.getTimestamp(10)).thenReturn(LAST_UPDATED_DATE);

        Feature foundFeature = dbFeature.getFeature(FEATURE_CD);

        assertNotNull(foundFeature);

        verify(expectedFeature,foundFeature);
    }

    /**
     * Test createRecord(feature).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecord() throws SQLException {
        Feature expectedFeature = new FeatureImpl(ID, FEATURE_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbFeature.createRecord(expectedFeature);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, FEATURE_CD);
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
     * Test createRecords(featureList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testCreateRecords() throws SQLException {
        List<Feature> expectedFeatureList = new LinkedList<Feature>();
        expectedFeatureList.add(new FeatureImpl(ID, FEATURE_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedFeatureList.add(new FeatureImpl(ID+1, FEATURE_CD+ID, DISPLAY_NAME+ID, DESCRIPTION+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbFeature.createRecords(expectedFeatureList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, FEATURE_CD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, FEATURE_CD+ID);
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
     * Test updateRecord(feature).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecord() throws SQLException {
        Feature expectedFeature = new FeatureImpl(ID, FEATURE_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);

        dbFeature.updateRecord(expectedFeature);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, FEATURE_CD);
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
     * Test updateRecords(featureList).
     * 
     * @throws SQLException 
     */
    @Test
    public void testUpdateRecords() throws SQLException {
        List<Feature> expectedFeatureList = new LinkedList<Feature>();
        expectedFeatureList.add(new FeatureImpl(ID, FEATURE_CD, DISPLAY_NAME, DESCRIPTION, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));
        expectedFeatureList.add(new FeatureImpl(ID+1, FEATURE_CD+ID, DISPLAY_NAME+ID, DESCRIPTION+ID, 
                SHOW, DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE));

        dbFeature.updateRecords(expectedFeatureList);

        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, FEATURE_CD);
        Mockito.verify(preparedStatement, Mockito.times(1)).setString(1, FEATURE_CD+ID);
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
     * Check if both given Feature rows match.
     * 
     * @param expectedFeature Feature
     * @param foundFeature Feature
     */
    private void verify(Feature expectedFeature, Feature foundFeature) {
        assertNotNull(foundFeature);
        assertEquals(expectedFeature.getId(),foundFeature.getId());
        assertEquals(expectedFeature.getFeatureCd(),foundFeature.getFeatureCd());
        assertEquals(expectedFeature.getDisplayName(),foundFeature.getDisplayName());
        assertEquals(expectedFeature.getDescription(),foundFeature.getDescription());
        assertEquals(expectedFeature.isDeleted(),foundFeature.isDeleted());
        assertEquals(expectedFeature.isShow(),foundFeature.isShow());
        assertEquals(expectedFeature.getCreatedBy(),foundFeature.getCreatedBy());
        assertEquals(expectedFeature.getCreatedDate(),foundFeature.getCreatedDate());
        assertEquals(expectedFeature.getLastUpdatedBy(),foundFeature.getLastUpdatedBy());
        assertEquals(expectedFeature.getLastUpdatedDate(),foundFeature.getLastUpdatedDate());
    }
}
