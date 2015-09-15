package core.db.interfaces;

import java.util.List;

import core.tables.interfaces.Ingredient;
/**
 * The Ingredient interface.
 * 
 * @author Vasco
 *
 */
public interface DBIngredient {
	/**
	 * Select an Ingredient by name
	 * 
	 * @param name ingredient
	 * @return Ingredient 
	 */
	public Ingredient getIngredient(String name);

	/**
	 * Select an Ingredient by id
	 * 
	 * @param id ingredient
	 * @return Ingredient
	 */
	public Ingredient getIngredient(int id);

	/**
	 * Return all existing ingredients.
	 * 
	 * @return List Ingredient
	 */
	public List<Ingredient> getIngredients();

	/**
     * Create a new record with given information.
     * 
     * @param ingredient Ingredient
     */
    public void createRecord(Ingredient ingredient);
    
    /**
     * Create new records with given information.
     * 
     * @param ingredientList List Ingredient
     */
    public void createRecords(List<Ingredient> ingredientList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param ingredient Ingredient
     */
    public void updateRecord(Ingredient ingredient);
    
    /**
     * Update existing records with given information.
     * 
     * @param ingredient List Ingredient
     */
    public void updateRecords(List<Ingredient> ingredient);
}