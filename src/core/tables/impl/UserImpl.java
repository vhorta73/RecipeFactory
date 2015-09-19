package core.tables.impl;

import java.sql.Timestamp;

import core.tables.interfaces.User;

/**
 * The User implementation.
 * 
 * @author Vasco
 *
 */
public class UserImpl implements User {
	/**
	 * The Auto-increment id.
	 */
	private final int ID;
	
	/**
	 * The User unique username.
	 */
	private final String USERNAME;
	
	/**
	 * The User hashed password.
	 */
	private final byte[] HASHED_PASSWORD;
	
	/**
	 * The User status.
	 */
	private final String STATUS;
	
	/**
	 * The User privilege id.
	 */
	private final int PRIVILEGE_ID;
	
	/**
	 * The username that created this user.
	 */
	private final String CREATED_BY;
	
	/**
	 * The date and time this record was created.
	 */
	private final Timestamp CREATED_DATE;
	
	/**
	 * The username that last updated this record.
	 */
	private final String LAST_UPDATED_BY;
	
	/**
	 * The date and time this record was last updated.
	 */
	private final Timestamp LAST_UPDATED_DATE;

	
	public UserImpl(int id, String username, byte[] password, int privilegeId, String status, 
			String createdBy, Timestamp createdDate, String lastUpdatedBy, Timestamp lastUpdatedDate ) {
		this.ID = id;
		this.USERNAME = username;
		this.HASHED_PASSWORD = password;
		this.STATUS = status;
		this.PRIVILEGE_ID = privilegeId;
		this.CREATED_BY = createdBy;
		this.CREATED_DATE = createdDate;
		this.LAST_UPDATED_BY = lastUpdatedBy;
		this.LAST_UPDATED_DATE = lastUpdatedDate;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCreatedBy() {
		return CREATED_BY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp getCreatedDate() {
		return CREATED_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLastUpdatedBy() {
		return LAST_UPDATED_BY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp getLastUpdatedDate() {
		return LAST_UPDATED_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getId() {
		return ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUsername() {
		return USERNAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte[] getPassword() {
		return HASHED_PASSWORD;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPrivilegeId() {
		return PRIVILEGE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStatus() {
		return STATUS;
	}

}
