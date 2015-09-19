package core.tables.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import core.tables.impl.FeatureImpl;
import core.tables.interfaces.Feature;

/**
 * Testing the Feature implementation.
 * 
 * @author Vasco
 *
 */
public class TestFeatureImpl {
	/**
	 * The feature implementation object.
	 */
	private Feature feature;
	
	/**
	 * The Auto-increment id.
	 */
	private final int ID = 1;
	
	/**
	 * The feature code.
	 */
	private final String FEATURE_CD = "Delete";
	
	/**
	 * The Feature display name.
	 */
	private final String DISPLAY_NAME = "Delete";
	
	/**
	 * The Feature description.
	 */
	private final String DESCRIPTION = "This is my first Feature description";

	/**
	 * Show flag.
	 */
	private final boolean SHOW = true;
	
	/**
	 * The deleted flag.
	 */
	private final boolean DELETED = false;

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
	 * Load all elements into Feature implementation
	 * and leave it ready for testing.
	 */
	@Before
	public void before() {
		feature = new FeatureImpl(ID, FEATURE_CD, DISPLAY_NAME, DESCRIPTION, SHOW, DELETED,
				CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
	}

	/**
	 * Test id
	 */
	@Test
	public void testId() {
		int foundId = feature.getId();
		assertNotNull(foundId);
		assertTrue(ID == foundId);
	}

	/**
	 * Test feature code
	 */
	@Test
	public void testFeatureCd() {
		String foundName = feature.getFeatureCd();
		assertNotNull(foundName);
		assertEquals(foundName, FEATURE_CD);
	}

	/**
	 * Test display name
	 */
	@Test
	public void testDisplayName() {
		String foundName = feature.getDisplayName();
		assertNotNull(foundName);
		assertEquals(foundName, DISPLAY_NAME);
	}

	/**
	 * Test description
	 */
	@Test
	public void testDescription() {
		String foundDescription = feature.getDescription();
		assertNotNull(foundDescription);
		assertEquals(foundDescription, DESCRIPTION);
	}

	/**
	 * Test show
	 */
	@Test
	public void testShow() {
		boolean foundShow = feature.isShow();
		assertNotNull(foundShow);
		assertEquals(foundShow, SHOW);
	}

	/**
	 * Test deleted
	 */
	@Test
	public void testDeleted() {
		boolean foundDeleted = feature.isDeleted();
		assertNotNull(foundDeleted);
		assertEquals(foundDeleted, DELETED);
	}

	/**
	 * Test created by
	 */
	@Test
	public void testCreatedBy() {
		String foundCreatedBy = feature.getCreatedBy();
		assertNotNull(foundCreatedBy);
		assertEquals(foundCreatedBy, CREATED_BY);
	}

	/**
	 * Test created date
	 */
	@Test
	public void testCreatedDate() {
		Timestamp foundCreatedDate = feature.getCreatedDate();
		assertNotNull(foundCreatedDate);
		assertEquals(foundCreatedDate, CREATED_DATE);
	}

	/**
	 * Test last updated by
	 */
	@Test
	public void testLastUpdatedBy() {
		String foundLastUpdatedBy = feature.getLastUpdatedBy();
		assertNotNull(foundLastUpdatedBy);
		assertEquals(foundLastUpdatedBy, LAST_UPDATED_BY);
	}

	/**
	 * Test last updated date
	 */
	@Test
	public void testLastUpdatedDate() {
		Timestamp foundLastUpdatedDate = feature.getLastUpdatedDate();
		assertNotNull(foundLastUpdatedDate);
		assertEquals(foundLastUpdatedDate, LAST_UPDATED_DATE);
	}
}
