package core.tables;

import java.sql.Timestamp;

import constants.UserStatus;

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
	private final UserStatus STATUS;
	
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

	
	public UserImpl(int id, String username, byte[] password, String status, int privilegeId, 
			String createdBy, Timestamp createdDate, String lastUpdatedBy, Timestamp lastUpdatedDate ) {
		this.ID = id;
		this.USERNAME = username;
		this.HASHED_PASSWORD = password;
		this.PRIVILEGE_ID = privilegeId;
		this.CREATED_BY = createdBy;
		this.CREATED_DATE = createdDate;
		this.LAST_UPDATED_BY = lastUpdatedBy;
		this.LAST_UPDATED_DATE = lastUpdatedDate;
		String upperStatus = status.toUpperCase();
		UserStatus userStatus = null;

		switch (upperStatus) {
		    case "ACTIVE"         : { userStatus = UserStatus.ACTIVE;         break; }
		    case "BLOCKED"        : { userStatus = UserStatus.BLOCKED;        break; }
	    	case "DELETED"        : { userStatus = UserStatus.DELETED;        break; }
    		case "INACTIVE"       : { userStatus = UserStatus.INACTIVE;       break; }
	    	case "NEW"            : { userStatus = UserStatus.NEW;            break; }
	    	case "PASSWORD_RESET" : { userStatus = UserStatus.PASSWORD_RESET; break; }
    		default               : { break; }
		}

		this.STATUS = userStatus;
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
	public UserStatus getStatus() {
		return STATUS;
	}

}
