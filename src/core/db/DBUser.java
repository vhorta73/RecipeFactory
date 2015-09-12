package core.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import web.Session;
import constants.DatabaseTableName;
import core.tables.User;
import core.tables.UserImpl;

/**
 * The User table database access.
 * 
 * @author Vasco
 *
 */
public class DBUser {
	/**
	 * The open Session to allow access to DB.
	 */
	private Session session;

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
	public DBUser(Session session, String username) {
		if ( username == null ) throw new IllegalArgumentException("Username cannot be null.");
		if ( session == null ) throw new IllegalArgumentException("Session cannot be null.");

		// If user is not yet validated, user data must be retrieved for validation.
		if ( session.isUserValidated() ) {
    		if ( !session.isLoggedIn() ) throw new IllegalStateException("User must be logged in.");
    		if ( !session.getUser().getUsername().equals(username) ) 
    			throw new IllegalStateException("Cannot update other user's details.");
		}

		this.username = username;
		this.session = session;
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
		String sql = "SELECT * FROM " + DatabaseTableName.getUserTable() + " WHERE username = '"+username+"'";

		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = this.session.getDB()
					.getConnection(DatabaseTableName.getUserDatabase())
					.prepareStatement(sql);
			rs = prepSt
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
		} finally {
			try {
				prepSt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * save the given password into this user records.
	 * 
	 * @param password
	 */
	public void savePassword(byte[] password) {
		int id = user.getId();
		String sql = "UPDATE " + DatabaseTableName.getUserTable() + " SET password = ? WHERE id = ? ";

		PreparedStatement prep = null;
		try {
			prep = this.session.getDB()
					.getConnection(DatabaseTableName.getUserDatabase())
					.prepareStatement(sql);
			prep.setBytes(1, password);
			prep.setInt(2, id);
			prep.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}