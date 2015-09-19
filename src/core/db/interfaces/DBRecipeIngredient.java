package core.db.interfaces;

import java.util.List;

import core.tables.interfaces.RecipeIngredient;
/**
 * The DBRecipeIngredient interface.
 * 
 * @author Vasco
 *
 */
public interface DBRecipeIngredient {
    /**
     * Get a list of RecipeIngredient for a given Recipe id.
     * 
     * @param recipeId
     * @return RecipeIngredient
     */
    public List<RecipeIngredient> getRecipeIngredient(int RecipeId);

    /**
     * Create a new record with given information.
     * 
     * @param recipeIngredient RecipeIngredient
     */
    public void createRecord(RecipeIngredient recipeIngredient);
    
    /**
     * Create new records with given information.
     * 
     * @param recipeIngredientList List RecipeIngredient
     */
    public void createRecords(List<RecipeIngredient> recipeIngredientList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param recipeIngredient RecipeIngredient
     */
    public void updateRecord(RecipeIngredient recipeIngredient);
    
    /**
     * Update existing records with given information.
     * 
     * @param recipeIngredient List RecipeIngredient
     */
    public void updateRecords(List<RecipeIngredient> recipeIngredient);
}
