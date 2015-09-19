package core.db.interfaces;

import java.util.List;

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

    /**
     * Create a new record with given information.
     * 
     * @param tool Tool
     */
    public void createRecord(Tool tool);
    
    /**
     * Create new records with given information.
     * 
     * @param toolList List Tool
     */
    public void createRecords(List<Tool> toolList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param tool Tool
     */
    public void updateRecord(Tool tool);
    
    /**
     * Update existing records with given information.
     * 
     * @param tool List Tool
     */
    public void updateRecords(List<Tool> tool);
}