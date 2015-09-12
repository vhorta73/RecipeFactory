package core.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
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
}
