package unitTests.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import core.User;
import core.UserImpl;

/**
 * Testing the User implementation.
 * 
 * @author Vasco
 *
 */
public class TestUserImpl {
	/**
	 * The User implementation object.
	 */
	private User user;
	
	/**
	 * The Auto-increment id.
	 */
	private final int ID = 1;
	
	/**
	 * The User unique name.
	 */
	private final String USERNAME = "myusername";
	
	/**
	 * The User hashed password.
	 */
	private final byte[] HASHED_PASSWORD = { 0x2 };
	
	/**
	 * The User status.
	 */
	private final String STATUS = "active";
	
	/**
	 * The User privilege id.
	 */
	private final int PRIVILEGE_ID = 123;
	
	/**
	 * The username that created this record.
	 */
	private final String CREATED_BY = "snoopy";
	
	/**
	 * The date and time this record was created.
	 */
	private final Timestamp CREATED_DATE = Timestamp.valueOf("2015-09-05 12:15:18");
	
	/**
	 * The username that last updated this record.
	 */
	private final String LAST_UPDATED_BY = "garfield";
	
	/**
	 * The date and time this record was last updated.
	 */
	private final Timestamp LAST_UPDATED_DATE = Timestamp.valueOf("2015-09-05 12:15:19");
	
	/**
	 * Load all elements into User implementation
	 * and leave it ready for testing.
	 */
	@Before
	public void before() {
		user = new UserImpl(ID, USERNAME, HASHED_PASSWORD, STATUS, PRIVILEGE_ID,
				CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
	}

	/**
	 * Test id
	 */
	@Test
	public void testId() {
		int foundId = user.getId();
		assertNotNull(foundId);
		assertTrue(ID == foundId);
	}

	/**
	 * Test name
	 */
	@Test
	public void testName() {
		String foundUsername = user.getUsername();
		assertNotNull(foundUsername);
		assertEquals(foundUsername, USERNAME);
	}

	/**
	 * Test hashed password
	 */
	@Test
	public void testHasedPassword() {
		byte[] foundHashedPassword = user.getPassword();
		assertNotNull(foundHashedPassword);
		assertEquals(foundHashedPassword, HASHED_PASSWORD);
	}

	/**
	 * Test created by
	 */
	@Test
	public void testCreatedBy() {
		String foundCreatedBy = user.getCreatedBy();
		assertNotNull(foundCreatedBy);
		assertEquals(foundCreatedBy, CREATED_BY);
	}

	/**
	 * Test created date
	 */
	@Test
	public void testCreatedDate() {
		Timestamp foundCreatedDate = user.getCreatedDate();
		assertNotNull(foundCreatedDate);
		assertEquals(foundCreatedDate, CREATED_DATE);
	}

	/**
	 * Test last updated by
	 */
	@Test
	public void testLastUpdatedBy() {
		String foundLastUpdatedBy = user.getLastUpdatedBy();
		assertNotNull(foundLastUpdatedBy);
		assertEquals(foundLastUpdatedBy, LAST_UPDATED_BY);
	}

	/**
	 * Test last updated date
	 */
	@Test
	public void testLastUpdatedDate() {
		Timestamp foundLastUpdatedDate = user.getLastUpdatedDate();
		assertNotNull(foundLastUpdatedDate);
		assertEquals(foundLastUpdatedDate, LAST_UPDATED_DATE);
	}
}
