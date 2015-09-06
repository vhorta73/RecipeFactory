package core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
	 * This class can only be instantiated once per user.
	 */
	private User user;
	
	/**
	 * The username.
	 */
	private final String username;

	/**
	 * An username must be supplied at the construction time.
	 * 
	 * @param username
	 */
	public DBUser(String username) {
		if ( username == null ) throw new IllegalArgumentException("Username cannot be null.");
		this.username = username;
		loadDBUser();
	}
	/**
	 * Return the loaded user
	 * 
	 * @return User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Load the user details from the database.
	 */
	private void loadDBUser() {
		// The SQL query to get this user data.
		String sql = "SELECT * FROM " + DBTables.getUserTable() + " WHERE username = '"+username+"'";

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

				// There should be only one since username is unique key in user table.
				user = new UserImpl(id, username, password, status, privilegeId,
						created_by, created_date, last_updated_by, last_updated_date);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * save the given password into this user records.
	 * 
	 * @param password
	 */
	public void savePassword(byte[] password) {
		// TODO: This should request username and password from user before deleting passwords...
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