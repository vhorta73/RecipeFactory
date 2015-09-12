package core.tables.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import core.tables.impl.PrivilegeToolImpl;
import core.tables.interfaces.PrivilegeTool;

/**
 * Testing the Privilege Tool implementation.
 * 
 * @author Vasco
 *
 */
public class TestPrivilegeToolImpl {
	/**
	 * The privilege tool implementation object.
	 */
	private PrivilegeTool privilegeTool;
	
	/**
	 * The Auto-increment id.
	 */
	private final int ID = 1;
	
	/**
	 * The privilege id.
	 */
	private final int PRIVILEGE_ID = 2;
	
	/**
	 * The tool id.
	 */
	private final int TOOL_ID = 3;
	
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
	 * Load all elements into Ingredient implementation
	 * and leave it ready for testing.
	 */
	@Before
	public void before() {
		privilegeTool = new PrivilegeToolImpl(ID, PRIVILEGE_ID, TOOL_ID, 
				CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
	}

	/**
	 * Test id
	 */
	@Test
	public void testId() {
		int foundId = privilegeTool.getId();
		assertNotNull(foundId);
		assertTrue(ID == foundId);
	}

	/**
	 * Test privilege id
	 */
	@Test
	public void testPrivilegeId() {
		int foundId = privilegeTool.getPrivilegeId();
		assertNotNull(foundId);
		assertTrue(foundId == PRIVILEGE_ID);
	}

	/**
	 * Test tool id
	 */
	@Test
	public void testToolId() {
		int foundId = privilegeTool.getToolId();
		assertNotNull(foundId);
		assertTrue(foundId == TOOL_ID);
	}

	/**
	 * Test created by
	 */
	@Test
	public void testCreatedBy() {
		String foundCreatedBy = privilegeTool.getCreatedBy();
		assertNotNull(foundCreatedBy);
		assertEquals(foundCreatedBy, CREATED_BY);
	}

	/**
	 * Test created date
	 */
	@Test
	public void testCreatedDate() {
		Timestamp foundCreatedDate = privilegeTool.getCreatedDate();
		assertNotNull(foundCreatedDate);
		assertEquals(foundCreatedDate, CREATED_DATE);
	}

	/**
	 * Test last updated by
	 */
	@Test
	public void testLastUpdatedBy() {
		String foundLastUpdatedBy = privilegeTool.getLastUpdatedBy();
		assertNotNull(foundLastUpdatedBy);
		assertEquals(foundLastUpdatedBy, LAST_UPDATED_BY);
	}

	/**
	 * Test last updated date
	 */
	@Test
	public void testLastUpdatedDate() {
		Timestamp foundLastUpdatedDate = privilegeTool.getLastUpdatedDate();
		assertNotNull(foundLastUpdatedDate);
		assertEquals(foundLastUpdatedDate, LAST_UPDATED_DATE);
	}
}
