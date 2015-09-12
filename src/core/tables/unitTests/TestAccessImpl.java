package core.tables.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import core.tables.impl.AccessImpl;
import core.tables.interfaces.Access;

/**
 * Testing the Access implementation.
 * 
 * @author Vasco
 *
 */
public class TestAccessImpl {
	/**
	 * The access implementation object.
	 */
	private Access access;
	
	/**
	 * The Auto-increment id.
	 */
	private final int ID = 1;
	
	/**
	 * The Access code.
	 */
	private final String ACCESS_CD = "FULL";
	
	/**
	 * The Access display name.
	 */
	private final String DISPLAY_NAME = "My First Access";
	
	/**
	 * The Access description.
	 */
	private final String DESCRIPTION = "This is my first Access description";
	
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
	 * Load all elements into Access implementation
	 * and leave it ready for testing.
	 */
	@Before
	public void before() {
		access = new AccessImpl(ID, ACCESS_CD, DISPLAY_NAME, DESCRIPTION,
				CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
	}

	/**
	 * Test id
	 */
	@Test
	public void testId() {
		int foundId = access.getId();
		assertNotNull(foundId);
		assertTrue(ID == foundId);
	}

	/**
	 * Test access code
	 */
	@Test
	public void testAccessCd() {
		String foundName = access.getAccessCd();
		assertNotNull(foundName);
		assertEquals(ACCESS_CD, foundName);
	}

	/**
	 * Test display name
	 */
	@Test
	public void testDisplayName() {
		String foundName = access.getDisplayName();
		assertNotNull(foundName);
		assertEquals(DISPLAY_NAME, foundName);
	}

	/**
	 * Test description
	 */
	@Test
	public void testDescription() {
		String foundDescription = access.getDescription();
		assertNotNull(foundDescription);
		assertEquals(foundDescription, DESCRIPTION);
	}

	/**
	 * Test created by
	 */
	@Test
	public void testCreatedBy() {
		String foundCreatedBy = access.getCreatedBy();
		assertNotNull(foundCreatedBy);
		assertEquals(foundCreatedBy, CREATED_BY);
	}

	/**
	 * Test created date
	 */
	@Test
	public void testCreatedDate() {
		Timestamp foundCreatedDate = access.getCreatedDate();
		assertNotNull(foundCreatedDate);
		assertEquals(foundCreatedDate, CREATED_DATE);
	}

	/**
	 * Test last updated by
	 */
	@Test
	public void testLastUpdatedBy() {
		String foundLastUpdatedBy = access.getLastUpdatedBy();
		assertNotNull(foundLastUpdatedBy);
		assertEquals(foundLastUpdatedBy, LAST_UPDATED_BY);
	}

	/**
	 * Test last updated date
	 */
	@Test
	public void testLastUpdatedDate() {
		Timestamp foundLastUpdatedDate = access.getLastUpdatedDate();
		assertNotNull(foundLastUpdatedDate);
		assertEquals(foundLastUpdatedDate, LAST_UPDATED_DATE);
	}
}
