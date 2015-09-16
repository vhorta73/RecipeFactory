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
