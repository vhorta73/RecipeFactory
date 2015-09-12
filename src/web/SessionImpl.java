package web;

import mySQL.ConnectDB;
import core.tables.User;
/**
 * The Session implementation.
 * 
 * @author Vasco
 *
 */
public class SessionImpl implements Session {
	/**
	 * The boolean loggedin.
	 */
	private boolean loggedIn = false;
	
	/**
	 * The User.
	 */
	private User user;
	
	/**
	 * The dbConnection.
	 */
	private ConnectDB dbConnection;
	
	/**
	 * Set to false once user is logged in.
	 */
	private boolean isLoadingSession = true;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUser() {
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectDB getDB() {
		return dbConnection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDB(ConnectDB dbConnection) {
		this.dbConnection = dbConnection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLoggedIn(boolean loginStatus) {
		if ( loginStatus ) isLoadingSession = false;
		this.loggedIn = loginStatus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUserValidated() {
		return isLoadingSession;
	}

	@Override
	public void setUser(User user) {
		// Only set it once.
		if ( this.user == null ) this.user = user;
	}
}
