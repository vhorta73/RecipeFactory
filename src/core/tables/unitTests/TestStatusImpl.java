package core.tables.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import core.tables.impl.StatusImpl;
import core.tables.interfaces.Status;

/**
 * Testing the Status implementation.
 * 
 * @author Vasco
 *
 */
public class TestStatusImpl {
	/**
	 * The status implementation object.
	 */
	private Status status;
	
	/**
	 * The Auto-increment id.
	 */
	private final int ID = 1;
	
	/**
	 * The Status code name.
	 */
	private final String STATUS_CD = "Active";
	
	/**
	 * The Status display name.
	 */
	private final String DISPLAY_NAME = "Active";
	
	/**
	 * The Status description.
	 */
	private final String DESCRIPTION = "This is my first status description";
	
	/**
	 * The username that created this ingredient.
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
	 * Load all elements into Status implementation
	 * and leave it ready for testing.
	 */
	@Before
	public void before() {
		status = new StatusImpl(ID, STATUS_CD, DISPLAY_NAME, DESCRIPTION,
				CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
	}

	/**
	 * Test id
	 */
	@Test
	public void testId() {
		int foundId = status.getId();
		assertNotNull(foundId);
		assertTrue(ID == foundId);
	}

	/**
	 * Test name
	 */
	@Test
	public void testName() {
		String foundName = status.getStatusCd();
		assertNotNull(foundName);
		assertEquals(foundName, STATUS_CD);
	}

	/**
	 * Test display name
	 */
	@Test
	public void testDisplayName() {
		String foundName = status.getDisplayName();
		assertNotNull(foundName);
		assertEquals(foundName, DISPLAY_NAME);
	}

	/**
	 * Test description
	 */
	@Test
	public void testDescription() {
		String foundDescription = status.getDescription();
		assertNotNull(foundDescription);
		assertEquals(foundDescription, DESCRIPTION);
	}

	/**
	 * Test created by
	 */
	@Test
	public void testCreatedBy() {
		String foundCreatedBy = status.getCreatedBy();
		assertNotNull(foundCreatedBy);
		assertEquals(foundCreatedBy, CREATED_BY);
	}

	/**
	 * Test created date
	 */
	@Test
	public void testCreatedDate() {
		Timestamp foundCreatedDate = status.getCreatedDate();
		assertNotNull(foundCreatedDate);
		assertEquals(foundCreatedDate, CREATED_DATE);
	}

	/**
	 * Test last updated by
	 */
	@Test
	public void testLastUpdatedBy() {
		String foundLastUpdatedBy = status.getLastUpdatedBy();
		assertNotNull(foundLastUpdatedBy);
		assertEquals(foundLastUpdatedBy, LAST_UPDATED_BY);
	}

	/**
	 * Test last updated date
	 */
	@Test
	public void testLastUpdatedDate() {
		Timestamp foundLastUpdatedDate = status.getLastUpdatedDate();
		assertNotNull(foundLastUpdatedDate);
		assertEquals(foundLastUpdatedDate, LAST_UPDATED_DATE);
	}
}
