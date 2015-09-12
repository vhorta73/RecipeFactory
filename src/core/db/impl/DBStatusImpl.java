package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBStatus;
import core.tables.impl.StatusImpl;
import core.tables.interfaces.Status;

/**
 * The Status table DB access.
 * 
 * @author Vasco
 *
 */
public class DBStatusImpl implements DBStatus {
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
	public DBStatusImpl(Session session) {
		this.session = session;
	}
	
	/**
	 * Select a Status by status_cd
	 * 
	 * @param status_cd String
	 * @return Status
	 */
	@Override
	public Status getStatus(String status_cd) {
		// Validate argument
		if ( status_cd == null ) throw new IllegalArgumentException("Status code cannot be null.");

        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DatabaseTableName.getStatusTable() + " WHERE status_cd = '"+status_cd+"'").get(0);
	}

	/**
	 * Select a Status by id
	 * 
	 * @param id statusId
	 * @return Status
	 */
	@Override
	public Status getStatus(int statusId) {
        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DatabaseTableName.getStatusTable() + " WHERE id = '"+statusId+"'").get(0);
	}
	
	/**
	 * Get a list of results from the database.
	 * 
	 * @param sql
	 * @return List Status
	 */
	private List<Status> getResults(String sql) {
		// Validate argument
		if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

		// Initialise the final Ingredient object to be returned.
		List<Status> finalStatusList = new LinkedList<Status>();

		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = this.session.getDB()
					.getConnection(DatabaseTableName.getStatusDatabase())
					.prepareStatement(sql);
			rs = prepSt
					.executeQuery();

			while ( rs.next() ) {
				int id                      = rs.getInt(1);
				String status_cd            = rs.getString(2);
				String display_name         = rs.getString(3);
				String description          = rs.getString(4);
				String created_by           = rs.getString(5);
				Timestamp created_date      = rs.getTimestamp(6);
				String last_updated_by      = rs.getString(7);
				Timestamp last_updated_date = rs.getTimestamp(8);

				finalStatusList.add(new StatusImpl(id, status_cd, display_name, description, 
						created_by, created_date, last_updated_by, last_updated_date));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepSt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return finalStatusList;
	}
}
