package core.db;

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
}