package core;

import core.db.interfaces.DBTableCommon;

/**
 * The Recipe interface.
 * 
 * @author Vasco
 *
 */
public interface Recipe extends DBTableCommon {
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
