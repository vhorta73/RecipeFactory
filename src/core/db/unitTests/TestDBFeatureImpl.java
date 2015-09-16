package core.db.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import mySQL.ConnectDB;

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
