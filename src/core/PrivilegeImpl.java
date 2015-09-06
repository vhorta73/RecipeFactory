package core;

import java.sql.Timestamp;

import constants.PrivilegeAccess;
import constants.PrivilegeStatus;
import constants.PrivilegeType;
/**
 * The Privilege implementation.
 * 
 * @author Vasco
 *
 */
public class PrivilegeImpl implements Privilege {
	/**
	 * The auto-increment id.
	 */
	public final int ID;
	
	/**
	 * The privilege name.
	 */
	public final String NAME;
	
	/**
	 * The privilege type.
	 */
	public final PrivilegeType TYPE;
	
	/**
	 * The privilege status.
	 */
	public final PrivilegeStatus STATUS;
	
	/**
	 * The privilege access.
	 */
	public final PrivilegeAccess ACCESS;
	
	/**
	 * The privilege description.
	 */
	public final String DESCRIPTION;

	/**
	 * The username that created this privilege.
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

	/**
	 * Constructor requested each column from the table to be given.
	 * 
	 * @param id 
	 * @param name
	 * @param type
	 * @param status
	 * @param access
	 * @param description
	 * @param createdBy
	 * @param createdDate
	 * @param lastUpdatedBy
	 * @param lastUpdatedDate
	 */
	public PrivilegeImpl(int id, String name, String type, 
			String status, String access, String description, 
			String createdBy, Timestamp createdDate, 
			String lastUpdatedBy, Timestamp lastUpdatedDate  ) {
		this.ID = id;
		this.NAME = name;
		this.DESCRIPTION = description;
		this.CREATED_BY = createdBy;
		this.CREATED_DATE = createdDate;
		this.LAST_UPDATED_BY = lastUpdatedBy;
		this.LAST_UPDATED_DATE = lastUpdatedDate;

		String upperType = type.toUpperCase();
		switch (upperType) {
		    case "SUPER_ADMIN" : { this.TYPE = PrivilegeType.SUPER_ADMIN; break; }
    		case "ADMIN"       : { this.TYPE = PrivilegeType.ADMIN;       break; }
		    case "MANAGER"     : { this.TYPE = PrivilegeType.MANAGER;     break; }
	    	case "NORMAL"      : { this.TYPE = PrivilegeType.NORMAL;      break; }
	    	case "TEMPORARY"   : { this.TYPE = PrivilegeType.TEMPORARY;   break; }
    		default            : { throw new IllegalArgumentException("Unknwon type: "+type); }
		}

		String upperStatus = status.toUpperCase();
		switch (upperStatus) {
		    case "ACTIVE"         : { this.STATUS = PrivilegeStatus.ACTIVE;         break; }
    		case "INACTIVE"       : { this.STATUS = PrivilegeStatus.INACTIVE;       break; }
		    case "BLOCKED"        : { this.STATUS = PrivilegeStatus.BLOCKED;        break; }
	    	case "PASSWORD_RESET" : { this.STATUS = PrivilegeStatus.PASSWORD_RESET; break; }
	    	case "DELETED"        : { this.STATUS = PrivilegeStatus.DELETED;        break; }
	    	case "NEW"            : { this.STATUS = PrivilegeStatus.NEW;            break; }
    		default               : { throw new IllegalArgumentException("Unknown status: "+status); }
		}

		String upperAccess = access.toUpperCase();
		switch (upperAccess) {
		    case "READ_WRITE" : { this.ACCESS = PrivilegeAccess.READ_WRITE; break; }
    		case "READ_ONLY"  : { this.ACCESS = PrivilegeAccess.READ_ONLY;  break; }
		    case "WRITE_ONLY" : { this.ACCESS = PrivilegeAccess.WRITE_ONLY; break; }
    		default           : { throw new IllegalArgumentException("Unknown acces: "+access); }
		}
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
	public String getName() {
		return NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrivilegeType getType() {
		return TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrivilegeStatus getStatus() {
		return STATUS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrivilegeAccess getAccess() {
		return ACCESS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
