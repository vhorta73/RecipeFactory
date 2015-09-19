package core.tables.unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import constants.EnumUnit;
import core.tables.impl.RecipeIngredientImpl;
import core.tables.interfaces.RecipeIngredient;

/**
 * Testing the Recipe Ingredient implementation.
 * 
 * @author Vasco
 *
 */
public class TestRecipeIngredientImpl {
    /**
     * The recipe ingredient implementation object.
     */
    private RecipeIngredient recipeIngredient;
    
    /**
     * The recipe id.
     */
    private final int RECIPE_ID = 1;
    
    /**
     * The ingredient id.
     */
    private final int INGREDIENT_ID = 1;
    
    /**
     * The quantity.
     */
    private final double QUANTITY = 25.21;
    
    /**
     * The quantity unit.
     */
    private final String UNIT = EnumUnit.L.toString();

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
     * Load all elements into Recipe Ingredient implementation
     * and leave it ready for testing.
     */
    @Before
    public void before() {
        recipeIngredient = new RecipeIngredientImpl(RECIPE_ID, INGREDIENT_ID, QUANTITY, UNIT, SHOW, DELETED,
                CREATED_BY, CREATED_DATE, LAST_UPDATED_BY, LAST_UPDATED_DATE);
    }

    /**
     * Test recipe id.
     */
    @Test
    public void testRecipeId() {
        int foundId = recipeIngredient.getRecipeId();
        assertNotNull(foundId);
        assertTrue(RECIPE_ID == foundId);
    }

    /**
     * Test ingredient id.
     */
    @Test
    public void testIngredientId() {
        int foundId = recipeIngredient.getIngredientId();
        assertNotNull(foundId);
        assertEquals(INGREDIENT_ID, foundId);
    }

    /**
     * Test quantity
     */
    @Test
    public void testQuantity() {
        double foundQuantity = recipeIngredient.getQuantity();
        assertNotNull(foundQuantity);
        assertTrue(QUANTITY == foundQuantity);
    }

    /**
     * Test quantity unit
     */
    @Test
    public void testUnit() {
        String foundUnit = recipeIngredient.getUnit();
        assertNotNull(foundUnit);
        assertEquals(foundUnit, UNIT);
    }

    /**
     * Test show
     */
    @Test
    public void testShow() {
        boolean foundShow = recipeIngredient.isShow();
        assertNotNull(foundShow);
        assertEquals(foundShow, SHOW);
    }

    /**
     * Test deleted
     */
    @Test
    public void testDeleted() {
        boolean foundDeleted = recipeIngredient.isDeleted();
        assertNotNull(foundDeleted);
        assertEquals(foundDeleted, DELETED);
    }

    /**
     * Test created by
     */
    @Test
    public void testCreatedBy() {
        String foundCreatedBy = recipeIngredient.getCreatedBy();
        assertNotNull(foundCreatedBy);
        assertEquals(foundCreatedBy, CREATED_BY);
    }

    /**
     * Test created date
     */
    @Test
    public void testCreatedDate() {
        Timestamp foundCreatedDate = recipeIngredient.getCreatedDate();
        assertNotNull(foundCreatedDate);
        assertEquals(foundCreatedDate, CREATED_DATE);
    }

    /**
     * Test last updated by
     */
    @Test
    public void testLastUpdatedBy() {
        String foundLastUpdatedBy = recipeIngredient.getLastUpdatedBy();
        assertNotNull(foundLastUpdatedBy);
        assertEquals(foundLastUpdatedBy, LAST_UPDATED_BY);
    }

    /**
     * Test last updated date
     */
    @Test
    public void testLastUpdatedDate() {
        Timestamp foundLastUpdatedDate = recipeIngredient.getLastUpdatedDate();
        assertNotNull(foundLastUpdatedDate);
        assertEquals(foundLastUpdatedDate, LAST_UPDATED_DATE);
    }
}
