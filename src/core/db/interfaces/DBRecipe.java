package core.db.interfaces;

import java.util.List;

import core.tables.interfaces.Recipe;
/**
 * The DBRecipe interface.
 * 
 * @author Vasco
 *
 */
public interface DBRecipe {
    /**
     * Get a Recipe row by id.
     * 
     * @param recipeId
     * @return Recipe
     */
    public Recipe getRecipe(int RecipeId);

    /**
     * Get and Recipe row by name.
     * 
     * @param recipeName
     * @return Recipe
     */
    public Recipe getRecipe(String recipeName);
    
    /**
     * Create a new record with given information.
     * 
     * @param recipe Recipe
     */
    public void createRecord(Recipe recipe);
    
    /**
     * Create new records with given information.
     * 
     * @param recipeList List Recipe
     */
    public void createRecords(List<Recipe> recipeList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param recipe Recipe
     */
    public void updateRecord(Recipe recipe);
    
    /**
     * Update existing records with given information.
     * 
     * @param recipe List Access
     */
    public void updateRecords(List<Recipe> recipe);
}
