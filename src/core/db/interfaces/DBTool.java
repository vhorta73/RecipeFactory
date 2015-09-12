package core.db.interfaces;

import core.tables.interfaces.Tool;
/**
 * The Tool interface.
 * 
 * @author Vasco
 *
 */
public interface DBTool {
	/**
	 * Select a Tool by toolCd
	 * 
	 * @param toolCd String
	 * @return Tool
	 */
	public Tool getTool(String toolCd);

	/**
	 * Select a Tool by id
	 * 
	 * @param id toolId
	 * @return Tool
	 */
	public Tool getTool(int toolId);

}