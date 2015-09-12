package core.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.Session;
import constants.DatabaseTableName;
import core.tables.Access;
import core.tables.AccessImpl;

/**
 * The Access table DB access.
 * 
 * @author Vasco
 *
 */
public class DBAccess {
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
	public DBAccess(Session session) {
		this.session = session;
	}
	
	/**
	 * Select an Access by code
	 * 
	 * @param accessCd String
	 * @return Access
	 */
	public Access getAccess(String accessCd) {
		// Validate argument
		if ( accessCd == null ) throw new IllegalArgumentException("Access name cannot be null.");

        // Initialise the final Access object to be returned.
		return getResults("SELECT * FROM " + DatabaseTableName.getAccessTable() + " WHERE access_cd = '"+accessCd+"'").get(0);
	}

	/**
	 * Select an Access by id
	 * 
	 * @param id int
	 * @return Access
	 */
	public Access getAccess(int id) {
        // Initialise the final Access object to be returned.
		return getResults("SELECT * FROM " + DatabaseTableName.getAccessTable() + " WHERE id = '"+id+"'").get(0);
	}
	
	/**
	 * Get a list of results from the database.
	 * 
	 * @param sql
	 * @return List Access
	 */
	private List<Access> getResults(String sql) {
		// Validate argument
		if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

		// Initialise the final Access object to be returned.
		List<Access> finalAccessList = new LinkedList<Access>();

		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = this.session.getDB()
					.getConnection(DatabaseTableName.getAccessDatabase())
					.prepareStatement(sql);
			rs = prepSt
					.executeQuery();

			while ( rs.next() ) {
				int id                      = rs.getInt(1);
				String accessCd             = rs.getString(2);
				String displayName          = rs.getString(3);
				String description          = rs.getString(4);
				String created_by           = rs.getString(5);
				Timestamp created_date      = rs.getTimestamp(6);
				String last_updated_by      = rs.getString(7);
				Timestamp last_updated_date = rs.getTimestamp(8);

				finalAccessList.add(new AccessImpl(id, accessCd, displayName, description, 
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

		return finalAccessList;
	}
}
