package core.tables.impl;

import java.sql.Timestamp;

import core.tables.interfaces.RecipeIngredient;

/**
 * The Recipe Ingredient implementation.
 * 
 * @author Vasco
 *
 */
public class RecipeIngredientImpl implements RecipeIngredient {
    /**
     * The recipe id.
     */
    private final int RECIPE_ID;
    
    /**
     * The ingredient id.
     */
    private final int INGREDIENT_ID;
    
    /**
     * The ingredient quantity.
     */
    private final Double QUANTITY;
    
    /**
     * The ingredient quantity unit.
     */
    private final String UNIT;
    
    /**
     * Show or no show flag.
     */
    private final boolean SHOW;
        
    /**
     * The deleted flag.
     */
    private final boolean DELETED;
        
    /**
     * The username that created this record.
     */
    private final String CREATED_BY;
    
    /**
     * The date and time this record was created.
     */
    private final Timestamp CREATED_DATE;
    
    /**
     * The username that last updated this record.
     */
    private final String LAST_UPDATED_BY;
    
    /**
     * The date and time this record was last updated.
     */
    private final Timestamp LAST_UPDATED_DATE;

    /**
     * Constructor.
     * 
     * @param recipeId int
     * @param ingredientId int
     * @param quantity double
     * @param unit String
     * @param show Boolean
     * @param deleted Boolean
     * @param createdBy String
     * @param createdDate Timestamp
     * @param lastUpdatedBy String
     * @param lastUpdatedDate Timestamp
     */
    public RecipeIngredientImpl(int recipeId, int ingredientId, double quantity, String unit, boolean show, boolean deleted, String createdBy, 
            Timestamp createdDate, String lastUpdatedBy, Timestamp lastUpdatedDate ) {
        this.RECIPE_ID = recipeId;
        this.INGREDIENT_ID = ingredientId;
        this.QUANTITY = quantity;
        this.UNIT = unit;
        this.SHOW = show;
        this.DELETED = deleted;
        this.CREATED_BY = createdBy;
        this.CREATED_DATE = createdDate;
        this.LAST_UPDATED_BY = lastUpdatedBy;
        this.LAST_UPDATED_DATE = lastUpdatedDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreatedBy() {
        return CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp getCreatedDate() {
        return CREATED_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastUpdatedBy() {
        return LAST_UPDATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp getLastUpdatedDate() {
        return LAST_UPDATED_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDeleted() {
        return DELETED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShow() {
        return SHOW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUnit() {
        return UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRecipeId() {
        return RECIPE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIngredientId() {
        return INGREDIENT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getQuantity() {
        return QUANTITY;
    }
}
