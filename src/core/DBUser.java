package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import mySQL.ConnectDB;
import constants.DBTables;

/**
 * The User table database access.
 * 
 * @author Vasco
 *
 */
public class DBUser extends ConnectDB {
	/**
	 * Select an User by username
	 * 
	 * @param name username
	 * @return User
	 */
	public User getUser(String username) {
		// Validate argument
		if ( username == null ) throw new IllegalArgumentException("username cannot be null.");

        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DBTables.getUserTable() + " WHERE username = '"+username+"'").get(0);
	}

	/**
	 * Get a list of results from the database.
	 * 
	 * @param sql
	 * @return List User
	 */
	private List<User> getResults(String sql) {
		// Validate argument
		if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

		// Initialise the final Ingredient object to be returned.
		List<User> finalUserList = new LinkedList<User>();

		try {
			ResultSet rs = super
					.getConnection(DBTables.getUserDatabase())
					.prepareStatement(sql)
					.executeQuery();

			while ( rs.next() ) {
				int id                      = rs.getInt(1);
				String username             = rs.getString(2);
				byte[] password             = rs.getBytes(3);
				int privilegeId             = rs.getInt(4);
				String status               = rs.getString(5);
				String created_by           = rs.getString(6);
				Timestamp created_date      = rs.getTimestamp(7);
				String last_updated_by      = rs.getString(8);
				Timestamp last_updated_date = rs.getTimestamp(9);

				finalUserList.add(new UserImpl(id, username, password, status, privilegeId,
						created_by, created_date, last_updated_by, last_updated_date));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalUserList;
	}

	/**
	 * For a given username, save the given password.
	 * 
	 * @param username 
	 * @param password
	 */
	public void savePassword(String username, byte[] password) {
		// TODO: This should request username and password from user before deleting passwords...
		User user = getUser(username);
		int id = user.getId();
		String sql = "UPDATE " + DBTables.getUserTable() + " SET password = ? WHERE id = ? ";

		try {
			PreparedStatement prep = super
					.getConnection(DBTables.getUserDatabase())
					.prepareStatement(sql);
			prep.setBytes(1, password);
			prep.setInt(2, id);
			prep.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
}
