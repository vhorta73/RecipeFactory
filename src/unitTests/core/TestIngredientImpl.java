package unitTests.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import core.Ingredient;
import core.IngredientImpl;

/**
 * Testing the Ingredient implementation.
 * 
 * @author Vasco
 *
 */
public class TestIngredientImpl {
	/**
	 * The ingredient implementation object.
	 */
	private Ingredient ingredient;
	
	/**
	 * The Auto-increment id.
	 */
	private final int ID = 1;
	
	/**
	 * The Ingredient unique name.
	 */
	private final String NAME = "My First Ingredient";
	
	/**
	 * The Ingredient description.
	 */
	private final String DESCRIPTION = "This is my first Ingredient description";
	
	/**
	 * The Ingredient notes.
	 */
	private final String NOTES = "This is my first Ingredient note";
	
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
		ingredient = new IngredientImpl(ID, NAME, DESCRIPTION, NOTES, CREATED_BY, 
				CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
	}

	/**
	 * Test id
	 */
	@Test
	public void testId() {
		int foundId = ingredient.getId();
		assertNotNull(foundId);
		assertTrue(ID == foundId);
	}

	/**
	 * Test name
	 */
	@Test
	public void testName() {
		String foundName = ingredient.getName();
		assertNotNull(foundName);
		assertEquals(foundName, NAME);
	}

	/**
	 * Test description
	 */
	@Test
	public void testDescription() {
		String foundDescription = ingredient.getDescription();
		assertNotNull(foundDescription);
		assertEquals(foundDescription, DESCRIPTION);
	}

	/**
	 * Test created by
	 */
	@Test
	public void testCreatedBy() {
		String foundCreatedBy = ingredient.getCreatedBy();
		assertNotNull(foundCreatedBy);
		assertEquals(foundCreatedBy, CREATED_BY);
	}

	/**
	 * Test created date
	 */
	@Test
	public void testCreatedDate() {
		Timestamp foundCreatedDate = ingredient.getCreatedDate();
		assertNotNull(foundCreatedDate);
		assertEquals(foundCreatedDate, CREATED_DATE);
	}

	/**
	 * Test last updated by
	 */
	@Test
	public void testLastUpdatedBy() {
		String foundLastUpdatedBy = ingredient.getLastUpdatedBy();
		assertNotNull(foundLastUpdatedBy);
		assertEquals(foundLastUpdatedBy, LAST_UPDATED_BY);
	}

	/**
	 * Test last updated date
	 */
	@Test
	public void testLastUpdatedDate() {
		Timestamp foundLastUpdatedDate = ingredient.getLastUpdatedDate();
		assertNotNull(foundLastUpdatedDate);
		assertEquals(foundLastUpdatedDate, LAST_UPDATED_DATE);
	}
}
