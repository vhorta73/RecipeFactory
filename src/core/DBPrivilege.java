package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import mySQL.ConnectDB;
import constants.DBTables;

/**
 * The privilege table DB access.
 * 
 * @author Vasco
 *
 */
public class DBPrivilege extends ConnectDB {
	/**
	 * The privilege found
	 */
	private Privilege privilege;

	/**
	 * Constructor requesting an id.
	 * 
	 * @param id
	 */
	public DBPrivilege(int id) {
		loadPrivilege(id);
	}

	/**
	 * The loaded Privileges.
	 * 
	 * @return Privilege
	 */
	public Privilege getPrivilege() {
		return privilege;
	}

	/**
	 * Load privilege data for the selected id.
	 * 
	 * @param id
	 */
	private void loadPrivilege(int privilege_id) {
		// Validate argument
		if ( privilege_id == 0 ) throw new IllegalArgumentException("Id cannot be zero.");

		String sql = "SELECT * FROM " + DBTables.getPrivilegeTable() 
				+ " WHERE id = '"+privilege_id+"'";
		
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = super
					.getConnection(DBTables.getPrivilegeDatabase())
					.prepareStatement(sql);
			rs    = prepSt
					.executeQuery();

			while ( rs.next() ) {
				int id                      = rs.getInt(1);
				String name                 = rs.getString(2);
				String type                 = rs.getString(3);
				String status               = rs.getString(4);
				String access               = rs.getString(5);
				String description          = rs.getString(6);
				String created_by           = rs.getString(7);
				Timestamp created_date      = rs.getTimestamp(8);
				String last_updated_by      = rs.getString(9);
				Timestamp last_updated_date = rs.getTimestamp(10);

				privilege = new PrivilegeImpl(id, name, type, status, access, description, 
						created_by, created_date, last_updated_by, last_updated_date);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if ( prepSt != null ) prepSt.close();
				if ( rs != null ) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}