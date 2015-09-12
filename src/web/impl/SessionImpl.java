package web.impl;

import web.interfaces.Session;
import mySQL.ConnectDB;
import core.UserPrivilege;
import core.tables.interfaces.User;
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
	 * The User Privileges.
	 */
	private UserPrivilege userPrivileges;
	
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUser(User user) {
		// Only set it once.
		if ( this.user == null ) this.user = user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserPrivilege getUserPrivileges() {
		return userPrivileges;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUserPrivileges(UserPrivilege userPrivilege) {
		// Only set it once.
		if ( this.userPrivileges == null ) this.userPrivileges = userPrivilege;
	}
}
