package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBTool;
import core.tables.impl.ToolImpl;
import core.tables.interfaces.Tool;

/**
 * The Tool table DB access.
 * 
 * @author Vasco
 *
 */
public class DBToolImpl implements DBTool {
	/**
	 * Access can only be granted if a session is open.
	 */
	private Session session;

    /**
     * The insert sql for new records.
     */
    private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getToolTable() 
            + " (tool_cd,display_name,description,created_by,last_updated_by)"
            + " VALUES(?,?,?,?,?)";
    
    /**
     * The update sql for old record changes.
     */
    private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getToolTable() 
            + " SET tool_cd = ?, display_name = ?, description = ?,"
            + " last_updated_by = ? WHERE id = ?";


	/**
	 * The Constructor requiring a valid initialised session
	 * to gain access to the database.
	 * 
	 * @param dbConnection
	 */
	public DBToolImpl(Session session) {
		this.session = session;
	}
	
	/**
	 * Select a Tool by toolCd
	 * 
	 * @param toolCd String
	 * @return Tool
	 */
	@Override
	public Tool getTool(String toolCd) {
		// Validate argument
		if ( toolCd == null ) throw new IllegalArgumentException("Tool code cannot be null.");

        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DatabaseTableName.getToolTable() + " WHERE tool_cd = '"+toolCd+"'").get(0);
	}

	/**
	 * Select a Tool by id
	 * 
	 * @param id toolId
	 * @return Tool
	 */
	@Override
	public Tool getTool(int toolId) {
        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DatabaseTableName.getToolTable() + " WHERE id = '"+toolId+"'").get(0);
	}
	
	/**
	 * Get a list of results from the database.
	 * 
	 * @param sql
	 * @return List Status
	 */
	private List<Tool> getResults(String sql) {
		// Validate argument
		if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

		// Initialise the final Ingredient object to be returned.
		List<Tool> finalToolList = new LinkedList<Tool>();

		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = this.session.getDB()
					.getConnection(DatabaseTableName.getToolDatabase())
					.prepareStatement(sql);
			rs = prepSt
					.executeQuery();

			while ( rs.next() ) {
				int id                      = rs.getInt(1);
				String toolCd               = rs.getString(2);
				String display_name         = rs.getString(3);
				String description          = rs.getString(4);
				String created_by           = rs.getString(5);
				Timestamp created_date      = rs.getTimestamp(6);
				String last_updated_by      = rs.getString(7);
				Timestamp last_updated_date = rs.getTimestamp(8);

				finalToolList.add(new ToolImpl(id, toolCd, display_name, description, 
						created_by, created_date, last_updated_by, last_updated_date));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepSt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return finalToolList;
	}
    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(Tool tool) {
        if ( tool == null ) throw new IllegalArgumentException("Tool record cannot be null.");
        List<Tool> toolList = new LinkedList<Tool>();
        toolList.add(tool);
        createRecords(toolList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<Tool> toolList) {
        if ( toolList == null ) throw new IllegalArgumentException("Tool list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getAccessDatabase())
            		.prepareStatement(INSERT_SQL);

            for( Tool tool : toolList ) {
                prepSt.setString(1, tool.getToolCd());
                prepSt.setString(2, tool.getDisplayName());
                prepSt.setString(3, tool.getDescription());
                prepSt.setString(4, tool.getCreatedBy());
                prepSt.setString(5, tool.getLastUpdatedBy());            
                prepSt.addBatch();
            }
            prepSt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepSt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecord(Tool tool) {
        if ( tool == null ) throw new IllegalArgumentException("Tool record cannot be null.");

        List<Tool> toolList = new LinkedList<Tool>();
        toolList.add(tool);
        updateRecords(toolList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<Tool> toolList) {
        if ( toolList == null ) throw new IllegalArgumentException("Tool list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getToolDatabase())
            		.prepareStatement(UPDATE_SQL);

            for( Tool tool : toolList ) {
                prepSt.setString(1, tool.getToolCd());
                prepSt.setString(2, tool.getDisplayName());
                prepSt.setString(3, tool.getDescription());
                prepSt.setString(4, tool.getLastUpdatedBy());
                prepSt.setInt(5, tool.getId());            
                prepSt.addBatch();
            }
            prepSt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepSt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
