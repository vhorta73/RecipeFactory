package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBUser;
import core.tables.impl.UserImpl;
import core.tables.interfaces.User;

/**
 * The User table database access.
 * 
 * @author Vasco
 *
 */
public class DBUserImpl implements DBUser {
	/**
	 * The open Session to allow access to DB.
	 */
	private Session session;

    /**
     * The insert sql for new records.
     */
    private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getUserTable() 
            + " (username,password,privilege_id,status,created_by,last_updated_by)"
            + " VALUES(?,'',?,?,?,?)";
    
    /**
     * The update sql for old record changes.
     */
    private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getUserTable() 
            + " SET username = ?, privilege_id = ?, status = ?,"
            + " last_updated_by = ? WHERE id = ?";

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
	public DBUserImpl(Session session, String username) {
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
	@Override
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
	@Override
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
    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(User user) {
        if ( user == null ) throw new IllegalArgumentException("User record cannot be null.");
        
        List<User> userList = new LinkedList<User>();
        userList.add(user);
        createRecords(userList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<User> userList) {
        if ( userList == null ) throw new IllegalArgumentException("User list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getUserDatabase())
            		.prepareStatement(INSERT_SQL);
    
            for( User user : userList ) {
                prepSt.setString(1, user.getUsername());
                prepSt.setInt(2, user.getPrivilegeId());
                prepSt.setString(3, user.getStatus());
                prepSt.setString(4, user.getCreatedBy());
                prepSt.setString(5, user.getLastUpdatedBy());            
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
    public void updateRecord(User user) {
        if ( user == null ) throw new IllegalArgumentException("User record cannot be null.");

        List<User> userList = new LinkedList<User>();
        userList.add(user);
        updateRecords(userList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<User> userList) {
        if ( userList == null ) throw new IllegalArgumentException("User list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getAccessDatabase())
            		.prepareStatement(UPDATE_SQL);
    
            for( User user : userList ) {
                prepSt.setString(1, user.getUsername());
                prepSt.setInt(2, user.getPrivilegeId());
                prepSt.setString(3, user.getStatus());
                prepSt.setString(4, user.getLastUpdatedBy());
                prepSt.setInt(5, user.getId());            
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