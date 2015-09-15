package core.tables.interfaces;


/**
 * The Ingredient interface.
 * 
 * @author Vasco
 * 
 */
public interface Ingredient extends TableCommon {
	/**
	 * The primary id from the ingredients table,
	 * used mostly internally for efficiency on updating
	 * or searching.
	 * 
	 * @return integer id
	 */
	public int getId();
	
	/**
	 * The ingredient's name.
	 * This is primary key on the database, meaning that 
	 * can only exist one ingredient with this name.
	 * 
	 * @return String
	 */
	public String getName();
	
	/**
	 * The description is an optional field where the user
	 * can say which type of ingredient it is if the name
	 * is not explicit enough.
	 * 
	 * @return String
	 */
	public String getDescription();
	
	/**
	 * Notes is an optional field where users can leave any
	 * required notes for the next time the ingredient is used
	 * or ordered.
	 * 
	 * @return String
	 */
	public String getNotes();
}
