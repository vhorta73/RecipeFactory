package core.tables.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import core.tables.impl.ToolImpl;
import core.tables.interfaces.Tool;

/**
 * Testing the Tool implementation.
 * 
 * @author Vasco
 *
 */
public class TestToolImpl {
	/**
	 * The tool implementation object.
	 */
	private Tool tool;
	
	/**
	 * The Auto-increment id.
	 */
	private final int ID = 1;
	
	/**
	 * The Tool unique name code.
	 */
	private final String TOOL_CD = "this_tool";
	
	/**
	 * The Tool displaying name.
	 */
	private final String DISPLAY_NAME = "This is my tool name";
	
	/**
	 * The Tool description.
	 */
	private final String DESCRIPTION = "This is my first tool description";
	
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
	 * Load all elements into Tool implementation
	 * and leave it ready for testing.
	 */
	@Before
	public void before() {
		tool = new ToolImpl(ID, TOOL_CD, DISPLAY_NAME, DESCRIPTION, CREATED_BY, 
				CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
	}

	/**
	 * Test id
	 */
	@Test
	public void testId() {
		int foundId = tool.getId();
		assertNotNull(foundId);
		assertTrue(ID == foundId);
	}

	/**
	 * Test tool code
	 */
	@Test
	public void testToolCd() {
		String foundName = tool.getToolCd();
		assertNotNull(foundName);
		assertEquals(foundName, TOOL_CD);
	}

	/**
	 * Test tool display name
	 */
	@Test
	public void testDisplayName() {
		String foundName = tool.getDisplayName();
		assertNotNull(foundName);
		assertEquals(foundName, DISPLAY_NAME);
	}

	/**
	 * Test description
	 */
	@Test
	public void testDescription() {
		String foundDescription = tool.getDescription();
		assertNotNull(foundDescription);
		assertEquals(foundDescription, DESCRIPTION);
	}

	/**
	 * Test created by
	 */
	@Test
	public void testCreatedBy() {
		String foundCreatedBy = tool.getCreatedBy();
		assertNotNull(foundCreatedBy);
		assertEquals(foundCreatedBy, CREATED_BY);
	}

	/**
	 * Test created date
	 */
	@Test
	public void testCreatedDate() {
		Timestamp foundCreatedDate = tool.getCreatedDate();
		assertNotNull(foundCreatedDate);
		assertEquals(foundCreatedDate, CREATED_DATE);
	}

	/**
	 * Test last updated by
	 */
	@Test
	public void testLastUpdatedBy() {
		String foundLastUpdatedBy = tool.getLastUpdatedBy();
		assertNotNull(foundLastUpdatedBy);
		assertEquals(foundLastUpdatedBy, LAST_UPDATED_BY);
	}

	/**
	 * Test last updated date
	 */
	@Test
	public void testLastUpdatedDate() {
		Timestamp foundLastUpdatedDate = tool.getLastUpdatedDate();
		assertNotNull(foundLastUpdatedDate);
		assertEquals(foundLastUpdatedDate, LAST_UPDATED_DATE);
	}
}
