package core.db.unitTests;

import static org.junit.Assert.*;

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbAccess = new DBAccessImpl(session);
	}

	/**
	 * Test id.
	 */
	@Test
	public void testId() {
		Access foundAccess = dbAccess.getAccess(ID);
		assertNotNull(foundAccess);
		assertEquals(ID, foundAccess.getId());
	}

	/**
	 * Test access code.
	 */
	@Test
	public void testAccessCd() {
		Access foundAccess = dbAccess.getAccess(ID);
		assertNotNull(foundAccess);
		assertEquals(ACCESS_CD, foundAccess.getAccessCd());
	}

	/**
	 * Test display name.
	 */
	@Test
	public void testDisplayName() {
		Access foundAccess = dbAccess.getAccess(ID);
		assertNotNull(foundAccess);
		assertEquals(DISPLAY_NAME, foundAccess.getDisplayName());
	}

	/**
	 * Test description.
	 */
	@Test
	public void testDescription() {
		Access foundAccess = dbAccess.getAccess(ID);
		assertNotNull(foundAccess);
		assertEquals(DESCRIPTION, foundAccess.getDescription());
	}

	/**
	 * Test Deleted.
	 */
	@Test
	public void testDeleted() {
		Access foundAccess = dbAccess.getAccess(ID);
		assertNotNull(foundAccess);
		assertEquals(DELETED, foundAccess.isDeleted());
	}

	/**
	 * Test show.
	 */
	@Test
	public void testShow() {
		Access foundAccess = dbAccess.getAccess(ID);
		assertNotNull(foundAccess);
		assertEquals(SHOW, foundAccess.isShow());
	}

	/**
	 * Test created by.
	 */
	@Test
	public void testCreatedBy() {
		Access foundAccess = dbAccess.getAccess(ID);
		assertNotNull(foundAccess);
		assertEquals(CREATED_BY, foundAccess.getCreatedBy());
	}

	/**
	 * Test created date.
	 */
	@Test
	public void testCreatedDate() {
		Access foundAccess = dbAccess.getAccess(ID);
		assertNotNull(foundAccess);
		assertEquals(CREATED_DATE, foundAccess.getCreatedDate());
	}

	/**
	 * Test last updated by
	 */
	@Test
	public void testLastUpdatedBy() {
		Access foundAccess = dbAccess.getAccess(ID);
		assertNotNull(foundAccess);
		assertEquals(LAST_UPDATED_BY, foundAccess.getLastUpdatedBy());
	}

	/**
	 * Test last updated date.
	 */
	@Test
	public void testLastUpdatedDate() {
		Access foundAccess = dbAccess.getAccess(ID);
		assertNotNull(foundAccess);
		assertEquals(LAST_UPDATED_DATE, foundAccess.getLastUpdatedDate());
	}
}
