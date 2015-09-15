package core;

import core.tables.interfaces.TableCommon;

/**
 * The Recipe interface.
 * 
 * @author Vasco
 *
 */
public interface Recipe extends TableCommon {
	/**
	 * The auto-increment id.
	 * 
	 * @return
	 */
	public int getId();
	
	/**
	 * The unique recipe name.
	 * 
	 * @return
	 */
	public String getName();
}
