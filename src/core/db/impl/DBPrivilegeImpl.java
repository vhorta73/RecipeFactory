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
import core.tables.impl.PrivilegeToolFeatureAcessImpl;
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

				privilegeToolFeatureAccess.add(new PrivilegeToolFeatureAcessImpl(
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
}