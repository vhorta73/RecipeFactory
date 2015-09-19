package core.tables.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import constants.EnumUnit;
import core.tables.impl.RecipeImpl;
import core.tables.interfaces.Recipe;

/**
 * Testing the Recipe implementation.
 * 
 * @author Vasco
 *
 */
public class TestRecipeImpl {
    /**
     * The recipe implementation object.
     */
    private Recipe recipe;
    
    /**
     * The Auto-increment id.
     */
    private final int ID = 1;
    
    /**
     * The Recipe name.
     */
    private final String NAME = "My first recipe";
    
    /**
     * The Recipe final quantity.
     */
    private final double FINAL_QUANTITY = 23.42;

    /**
     * The Recipe final quantity units.
     */
    private final String UNIT = EnumUnit.L.toString();
    
    /**
     * The Recipe description.
     */
    private final String DESCRIPTION = "This is my first description";

    /**
     * The Recipe notes.
     */
    private final String NOTES = "This is my first note";

    /**
     * The Show flag.
     */
    private final boolean SHOW = true;
    
    /**
     * The deleted flag.
     */
    private final boolean DELETED = false;

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
     * Load all elements into Recipe implementation
     * and leave it ready for testing.
     */
    @Before
    public void before() {
        recipe = new RecipeImpl(ID, NAME, FINAL_QUANTITY, UNIT, DESCRIPTION, NOTES, SHOW, 
                DELETED, CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
    }

    /**
     * Test id
     */
    @Test
    public void testId() {
        int foundId = recipe.getId();
        assertNotNull(foundId);
        assertTrue(ID == foundId);
    }

    /**
     * Test name
     */
    @Test
    public void testName() {
        String foundName = recipe.getName();
        assertNotNull(foundName);
        assertEquals(NAME, foundName);
    }

    /**
     * Test final quantity
     */
    @Test
    public void testFinalQuantity() {
        double foundQuantity = recipe.getFinalQuantity();
        assertNotNull(foundQuantity);
        assertTrue(FINAL_QUANTITY == foundQuantity);
    }

    /**
     * Test final quantity unit
     */
    @Test
    public void testUnit() {
        String foundUnit = recipe.getUnit();
        assertNotNull(foundUnit);
        assertEquals(foundUnit, UNIT);
    }

    /**
     * Test description
     */
    @Test
    public void testDescription() {
        String foundDescription = recipe.getDescription();
        assertNotNull(foundDescription);
        assertEquals(foundDescription, DESCRIPTION);
    }

    /**
     * Test note
     */
    @Test
    public void testNote() {
        String foundNote = recipe.getNotes();
        assertNotNull(foundNote);
        assertEquals(foundNote, NOTES);
    }

    /**
     * Test show
     */
    @Test
    public void testShow() {
        boolean foundShow = recipe.isShow();
        assertNotNull(foundShow);
        assertEquals(foundShow, SHOW);
    }

    /**
     * Test deleted
     */
    @Test
    public void testDeleted() {
        boolean foundDeleted = recipe.isDeleted();
        assertNotNull(foundDeleted);
        assertEquals(foundDeleted, DELETED);
    }

    /**
     * Test created by
     */
    @Test
    public void testCreatedBy() {
        String foundCreatedBy = recipe.getCreatedBy();
        assertNotNull(foundCreatedBy);
        assertEquals(foundCreatedBy, CREATED_BY);
    }

    /**
     * Test created date
     */
    @Test
    public void testCreatedDate() {
        Timestamp foundCreatedDate = recipe.getCreatedDate();
        assertNotNull(foundCreatedDate);
        assertEquals(foundCreatedDate, CREATED_DATE);
    }

    /**
     * Test last updated by
     */
    @Test
    public void testLastUpdatedBy() {
        String foundLastUpdatedBy = recipe.getLastUpdatedBy();
        assertNotNull(foundLastUpdatedBy);
        assertEquals(foundLastUpdatedBy, LAST_UPDATED_BY);
    }

    /**
     * Test last updated date
     */
    @Test
    public void testLastUpdatedDate() {
        Timestamp foundLastUpdatedDate = recipe.getLastUpdatedDate();
        assertNotNull(foundLastUpdatedDate);
        assertEquals(foundLastUpdatedDate, LAST_UPDATED_DATE);
    }
}
