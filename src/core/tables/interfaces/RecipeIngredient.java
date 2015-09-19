package core.tables.interfaces;

/**
 * The Recipe Ingredient interface.
 * 
 * @author Vasco
 *
 */
public interface RecipeIngredient extends TableCommon {
    /**
     * The recipe id.
     * 
     * @return int
     */
    public int getRecipeId();
    
    /**
     * The ingredient id.
     * 
     * @return int
     */
    public int getIngredientId();

    /**
     * The ingredient quantity.
     * 
     * @return double
     */
    public double getQuantity();

    /**
     * The ingredient quantity unit.
     * 
     * @return String
     */
    public String getUnit();

    /**
     * True if this record is showing.
     * 
     * @return true if showing
     */
    public boolean isShow();

    /**
     * True if this record is deleted.
     * 
     * @return true if deleted.
     */
    public boolean isDeleted();
}
