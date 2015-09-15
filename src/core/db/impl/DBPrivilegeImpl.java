package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBPrivilege;
import core.tables.impl.PrivilegeImpl;
import core.tables.impl.PrivilegeToolFeatureAccessImpl;
import core.tables.impl.PrivilegeToolImpl;
import core.tables.interfaces.Privilege;
import core.tables.interfaces.PrivilegeTool;
import core.tables.interfaces.PrivilegeToolFeatureAccess;

/**
 * The privilege table DB access.
 * 
 * @author Vasco
 *
 */
public class DBPrivilegeImpl implements DBPrivilege {
	/**
	 * The open Session to access the DB.
	 */
	private Session session;
	
	/**
	 * Final insert sql for new records.
	 */
	private final String INSERT_SQL_PRIVILEGE = "INSERT INTO " + DatabaseTableName.getPrivilegeTable() 
            + " (display_name,status_id,description,created_by,last_updated_by) VALUES(?,?,?,?,?)";
	
	/**
	 * Final insert sql for new records.
	 */
	private final String INSERT_SQL_PRIVILEGE_TOOL = "INSERT INTO " + DatabaseTableName.getPrivilegeToolTable() 
            + " (privilege_id,tool_id,created_by,last_updated_by) VALUES(?,?,?,?)";
	
	/**
	 * Final insert sql for new records.
	 */
	private final String INSERT_SQL_PRIVILEGE_TOOL_FEATURE_ACCESS = "INSERT INTO " + DatabaseTableName.getPrivilegeToolFeatureAccessTable()
            + " (privilege_tool_id,feature_id,access_id,created_by,last_updated_by) VALUES(?,?,?,?,?)";
	
	/**
	 * Final update sqls for changing old records.
	 */
	private final String UPDATE_SQL_PRIVILEGE = "UPDATE " + DatabaseTableName.getPrivilegeTable() 
            + " SET display_name = ?, status_id = ?, description = ?, last_updated_by = ? WHERE id = ? ";

	/**
	 * Final update sqls for changing old records.
	 */
	private final String UPDATE_SQL_PRIVILEGE_TOOL = "UPDATE " + DatabaseTableName.getPrivilegeToolTable() 
            + " SET privilege_id = ?, tool_id = ?, last_updated_by = ? WHERE id = ? ";
	
	/**
	 * Final update sqls for changing old records.
	 */
	private final String UPDATE_SQL_PRIVILEGE_TOOL_FEATURE_ACCESS = "UPDATE " + DatabaseTableName.getPrivilegeToolFeatureAccessTable() 
            + " SET access_id = ?, last_updated_by = ? WHERE privilege_tool_id = ? AND feature_id = ? ";


	/**
	 * The privilege id.
	 */
	private int privilegeId;

	/**
	 * The privilege found
	 */
	private Privilege privilege;
	
	/**
	 * The Privilege Tool list.
	 */
	private List<PrivilegeTool> privilegeTool;
	
	/**
	 * The Privilege Tool Feature Access list.
	 */
	private List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccess;
	
	/**
	 * Constructor requesting an id.
	 */
	public DBPrivilegeImpl(Session session) {
		if ( session == null ) throw new IllegalArgumentException("Session cannot be null.");
		if ( !session.isLoggedIn() ) throw new IllegalStateException("User must be logged in at this point.");
		if ( session.getUser() == null ) throw new IllegalStateException("No user loaded.");

		this.session = session;
		this.privilegeId = session.getUser().getId();
		
		loadPrivilegeData();
		loadPrivilegeTools();
		loadPrivilegeToolFeatureAccess();
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
	 * {@inheritDoc}
	 */
	@Override
	public List<PrivilegeTool> getPrivilegeTools() {
		return privilegeTool;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PrivilegeToolFeatureAccess> getToolFeatureAccess() {
		return privilegeToolFeatureAccess;
	}

	/**
	 * Load privilege data for the selected id.
	 * 
	 * @param id
	 */
	private void loadPrivilegeData() {
		// Validate argument
		if ( privilegeId == 0 ) throw new IllegalArgumentException("Id cannot be zero.");

		String sql = "SELECT * FROM " + DatabaseTableName.getPrivilegeTable() 
				+ " WHERE id = '"+privilegeId+"'";
		
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = this.session.getDB()
					.getConnection(DatabaseTableName.getPrivilegeDatabase())
					.prepareStatement(sql);
			rs    = prepSt
					.executeQuery();

			while ( rs.next() ) {
				int id                      = rs.getInt(1);
				String name                 = rs.getString(2);
				int status_id               = rs.getInt(3);
				String description          = rs.getString(4);
				String created_by           = rs.getString(5);
				Timestamp created_date      = rs.getTimestamp(6);
				String last_updated_by      = rs.getString(7);
				Timestamp last_updated_date = rs.getTimestamp(8);

				privilege = new PrivilegeImpl(id, name, status_id, description, 
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

	/**
	 * Load all Privilege Tools linked to this privilege Id.
	 */
	private void loadPrivilegeTools() {
		if ( privilegeId == 0 ) throw new IllegalArgumentException("Id cannot be zero.");

		String sql = "SELECT * FROM " + DatabaseTableName.getPrivilegeToolTable() 
				+ " WHERE privilege_id = '"+privilegeId+"'";
		
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		privilegeTool = new LinkedList<PrivilegeTool>();
		try {
			prepSt = this.session.getDB()
					.getConnection(DatabaseTableName.getPrivilegeDatabase())
					.prepareStatement(sql);
			rs    = prepSt
					.executeQuery();

			while ( rs.next() ) {
				int id                      = rs.getInt(1);
				int privilegeId             = rs.getInt(2);
				int toolId                  = rs.getInt(3);
				String created_by           = rs.getString(4);
				Timestamp created_date      = rs.getTimestamp(5);
				String last_updated_by      = rs.getString(6);
				Timestamp last_updated_date = rs.getTimestamp(7);

				privilegeTool.add(new PrivilegeToolImpl(id, privilegeId, toolId, 
						created_by, created_date, last_updated_by, last_updated_date));
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
	};

	/**
	 * Load all Tool Feature Accesses for all found privilege_tool_ids
	 */
	private void loadPrivilegeToolFeatureAccess() {
		// We are about to load all, so reset anything old.
		privilegeToolFeatureAccess = new LinkedList<PrivilegeToolFeatureAccess>();

		// Nothing to load.
		if ( privilegeTool.size() == 0 ) return;

		// Gather ids comma separated, closed in single quotes.
		StringJoiner privilegeToolIds = new StringJoiner("','");
		privilegeTool.stream().forEach(p -> privilegeToolIds.add(""+p.getId()));

		// Build the sql query
		String sql = "SELECT * FROM " + DatabaseTableName.getPrivilegeToolFeatureAccessTable() 
				+ " WHERE privilege_tool_id IN('"+privilegeToolIds+"')";
		
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = this.session.getDB()
					.getConnection(DatabaseTableName.getPrivilegeDatabase())
					.prepareStatement(sql);
			rs    = prepSt
					.executeQuery();

			while ( rs.next() ) {
				int privilegeToolId         = rs.getInt(1);
				int featureId               = rs.getInt(2);
				int accessId                = rs.getInt(3);
				String created_by           = rs.getString(4);
				Timestamp created_date      = rs.getTimestamp(5);
				String last_updated_by      = rs.getString(6);
				Timestamp last_updated_date = rs.getTimestamp(7);

				privilegeToolFeatureAccess.add(new PrivilegeToolFeatureAccessImpl(
						privilegeToolId, featureId, accessId, 
						created_by, created_date, last_updated_by, last_updated_date));
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

	/**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(Privilege privilege) {
        if ( privilege == null ) throw new IllegalArgumentException("Privilege record cannot be null.");
        
        List<Privilege> privilegeList = new LinkedList<Privilege>();
        privilegeList.add(privilege);
        createPrivilegeRecords(privilegeList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPrivilegeRecords(List<Privilege> privilegeList) {
        if ( privilegeList == null ) throw new IllegalArgumentException("Privilege list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getPrivilegeDatabase())
            		.prepareStatement(INSERT_SQL_PRIVILEGE);
    
            for( Privilege privilege : privilegeList ) {
                prepSt.setString(1, privilege.getDisplayName());
                prepSt.setInt(2, privilege.getStatus());
                prepSt.setString(3, privilege.getDescription());
                prepSt.setString(4, privilege.getCreatedBy());
                prepSt.setString(5, privilege.getLastUpdatedBy());            
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
    public void updateRecord(Privilege privilege) {
        if ( privilege == null ) throw new IllegalArgumentException("Privilege record cannot be null.");

        List<Privilege> privilegeList = new LinkedList<Privilege>();
        privilegeList.add(privilege);
        updatePrivilegeRecords(privilegeList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePrivilegeRecords(List<Privilege> privilegeList) {
        if ( privilegeList == null ) throw new IllegalArgumentException("Privilege list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getPrivilegeDatabase())
            		.prepareStatement(UPDATE_SQL_PRIVILEGE);
    
            for( Privilege privilege : privilegeList ) {
                prepSt.setString(1, privilege.getDisplayName());
                prepSt.setInt(2, privilege.getStatus());
                prepSt.setString(3, privilege.getDescription());
                prepSt.setString(4, privilege.getLastUpdatedBy());
                prepSt.setInt(5, privilege.getId());            
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
    public void createRecord(PrivilegeTool privilegeTool) {
        if ( privilegeTool == null ) throw new IllegalArgumentException("PrivilegeTool record cannot be null.");
        
        List<PrivilegeTool> privilegeToolList = new LinkedList<PrivilegeTool>();
        privilegeToolList.add(privilegeTool);
        createPrivilegeToolRecords(privilegeToolList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPrivilegeToolRecords(List<PrivilegeTool> privilegeToolList) {
        if ( privilegeToolList == null ) throw new IllegalArgumentException("PrivilegeTool list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getPrivilegeDatabase())
            		.prepareStatement(INSERT_SQL_PRIVILEGE_TOOL);
    
            for( PrivilegeTool privilegeTool : privilegeToolList ) {
                prepSt.setInt(1, privilegeTool.getPrivilegeId());
                prepSt.setInt(2, privilegeTool.getToolId());
                prepSt.setString(3, privilegeTool.getCreatedBy());
                prepSt.setString(4, privilegeTool.getLastUpdatedBy());            
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
    public void updateRecord(PrivilegeTool privilegeTool) {
        if ( privilegeTool == null ) throw new IllegalArgumentException("PrivilegeTool record cannot be null.");

        List<PrivilegeTool> privilegeToolList = new LinkedList<PrivilegeTool>();
        privilegeToolList.add(privilegeTool);
        updatePrivilegeToolRecords(privilegeToolList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePrivilegeToolRecords(List<PrivilegeTool> privilegeToolList) {
        if ( privilegeToolList == null ) throw new IllegalArgumentException("PrivilegeTool list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getPrivilegeDatabase())
            		.prepareStatement(UPDATE_SQL_PRIVILEGE_TOOL);
    
            for( PrivilegeTool privilegeTool : privilegeToolList ) {
                prepSt.setInt(1, privilegeTool.getPrivilegeId());
                prepSt.setInt(2, privilegeTool.getToolId());
                prepSt.setString(3, privilegeTool.getLastUpdatedBy());
                prepSt.setInt(4, privilegeTool.getId());            
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
    public void createRecord(PrivilegeToolFeatureAccess privilegeToolFeatureAccess) {
        if ( privilegeToolFeatureAccess == null ) throw new IllegalArgumentException("PrivilegeToolFeatureAccess record cannot be null.");
        
        List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccessList = new LinkedList<PrivilegeToolFeatureAccess>();
        privilegeToolFeatureAccessList.add(privilegeToolFeatureAccess);
        createPrivilegeToolFeatureAccessRecords(privilegeToolFeatureAccessList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPrivilegeToolFeatureAccessRecords(List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccessList) {
        if ( privilegeToolFeatureAccessList == null ) throw new IllegalArgumentException("PrivilegeToolFeatureAccess list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getPrivilegeDatabase())
            		.prepareStatement(INSERT_SQL_PRIVILEGE_TOOL_FEATURE_ACCESS);
    
            for( PrivilegeToolFeatureAccess privilegeToolFeatureAccess : privilegeToolFeatureAccessList ) {
                prepSt.setInt(1, privilegeToolFeatureAccess.getPrivilegeToolId());
                prepSt.setInt(2, privilegeToolFeatureAccess.getFeatureId());
                prepSt.setInt(3, privilegeToolFeatureAccess.getAccessId());
                prepSt.setString(4, privilegeToolFeatureAccess.getCreatedBy());
                prepSt.setString(5, privilegeToolFeatureAccess.getLastUpdatedBy());            
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
    public void updateRecord(PrivilegeToolFeatureAccess privilegeToolFeatureAccess) {
        if ( privilegeToolFeatureAccess == null ) throw new IllegalArgumentException("PrivilegeToolFeatureAccess record cannot be null.");

        List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccessList = new LinkedList<PrivilegeToolFeatureAccess>();
        privilegeToolFeatureAccessList.add(privilegeToolFeatureAccess);
        updatePrivilegeToolFeatureAccessRecords(privilegeToolFeatureAccessList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePrivilegeToolFeatureAccessRecords(List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccessList) {
        if ( privilegeToolFeatureAccessList == null ) throw new IllegalArgumentException("Privilege list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getPrivilegeDatabase())
            		.prepareStatement(UPDATE_SQL_PRIVILEGE_TOOL_FEATURE_ACCESS);
    
            for( PrivilegeToolFeatureAccess privilegeToolFeatureAccess : privilegeToolFeatureAccessList ) {
                prepSt.setInt(1, privilegeToolFeatureAccess.getAccessId());
                prepSt.setString(2, privilegeToolFeatureAccess.getLastUpdatedBy());
                prepSt.setInt(3, privilegeToolFeatureAccess.getPrivilegeToolId());
                prepSt.setInt(4, privilegeToolFeatureAccess.getFeatureId());
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