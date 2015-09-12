package core.tables.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import core.tables.impl.PrivilegeToolFeatureAcessImpl;
import core.tables.interfaces.PrivilegeToolFeatureAccess;

/**
 * Testing the Privilege Tool Feature Access implementation.
 * 
 * @author Vasco
 *
 */
public class TestPrivilegeToolFeatureAccessImpl {
	/**
	 * The Privilege Tool Feature Access implementation object.
	 */
	private PrivilegeToolFeatureAccess privilegeToolFeatureAccess;
	
	/**
	 * The privilege tool id.
	 */
	private final int PRIVILEGE_TOOL_ID = 1;
	
	/**
	 * The feature id.
	 */
	private final int FEATURE_ID = 1;
	
	/**
	 * The access id.
	 */
	private final int ACCESS_ID = 1;
	
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
		privilegeToolFeatureAccess = new PrivilegeToolFeatureAcessImpl(PRIVILEGE_TOOL_ID,
				FEATURE_ID, ACCESS_ID, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
	}

	/**
	 * Test privilege tool id
	 */
	@Test
	public void testPrivilegeToolId() {
		int foundId = privilegeToolFeatureAccess.getPrivilegeToolId();
		assertNotNull(foundId);
		assertTrue(PRIVILEGE_TOOL_ID == foundId);
	}

	/**
	 * Test feature id
	 */
	@Test
	public void testFeatureId() {
		int foundId = privilegeToolFeatureAccess.getFeatureId();
		assertNotNull(foundId);
		assertTrue(FEATURE_ID == foundId);
	}

	/**
	 * Test access id
	 */
	@Test
	public void testAccessId() {
		int foundId = privilegeToolFeatureAccess.getAccessId();
		assertNotNull(foundId);
		assertTrue(ACCESS_ID == foundId);
	}

	/**
	 * Test created by
	 */
	@Test
	public void testCreatedBy() {
		String foundCreatedBy = privilegeToolFeatureAccess.getCreatedBy();
		assertNotNull(foundCreatedBy);
		assertEquals(foundCreatedBy, CREATED_BY);
	}

	/**
	 * Test created date
	 */
	@Test
	public void testCreatedDate() {
		Timestamp foundCreatedDate = privilegeToolFeatureAccess.getCreatedDate();
		assertNotNull(foundCreatedDate);
		assertEquals(foundCreatedDate, CREATED_DATE);
	}

	/**
	 * Test last updated by
	 */
	@Test
	public void testLastUpdatedBy() {
		String foundLastUpdatedBy = privilegeToolFeatureAccess.getLastUpdatedBy();
		assertNotNull(foundLastUpdatedBy);
		assertEquals(foundLastUpdatedBy, LAST_UPDATED_BY);
	}

	/**
	 * Test last updated date
	 */
	@Test
	public void testLastUpdatedDate() {
		Timestamp foundLastUpdatedDate = privilegeToolFeatureAccess.getLastUpdatedDate();
		assertNotNull(foundLastUpdatedDate);
		assertEquals(foundLastUpdatedDate, LAST_UPDATED_DATE);
	}
}
