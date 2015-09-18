package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBPrivilegeToolFeatureAccess;
import core.tables.impl.PrivilegeToolFeatureAccessImpl;
import core.tables.interfaces.PrivilegeToolFeatureAccess;

/**
 * The PrivilegeToolFeatureAccess table DB access.
 * 
 * @author Vasco
 *
 */
public class DBPrivilegeToolFeatureAccessImpl implements DBPrivilegeToolFeatureAccess {
    /**
     * Access can only be granted if a session is open.
     */
    private Session session;

    /**
     * The insert sql for new records.
     */
    private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getPrivilegeToolFeatureAccessTable() 
            + " (privilege_id,tool_id,feature_id,access_id,`show`,deleted,created_by,last_updated_by)"
            + " VALUES(?,?,?,?,?,?,?,?)";
    
    /**
     * The update sql for old record changes.
     */
    private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getPrivilegeToolFeatureAccessTable() 
            + " SET access_id = ?, `show` = ?, deleted = ?, last_updated_by = ? "
            + " WHERE privilege_id = ? AND tool_id = ? ";

    /**
     * The Constructor requiring a valid initialised session
     * to gain access to the database.
     * 
     * @param dbConnection
     */
    public DBPrivilegeToolFeatureAccessImpl(Session session) {
        this.session = session;
    }
    
    /**
     * Select a Privilege by id
     * 
     * @param id int
     * @return PrivilegeToolFeatureAccess List
     */
    @Override
    public List<PrivilegeToolFeatureAccess> getPrivilegeToolFeatureAccessesByPrivilegeId(int privilegeId) {
        // Initialise the final Privilege object to be returned.
        return getResults("SELECT * FROM " + DatabaseTableName.getPrivilegeToolFeatureAccessTable() + " WHERE privilege_id = '"+privilegeId+"'");
    }
    
    /**
     * Get a list of results from the database.
     * 
     * @param sql
     * @return List PrivilegeToolFeatureAccess
     */
    private List<PrivilegeToolFeatureAccess> getResults(String sql) {
        // Validate argument
        if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

        // Initialise the final Privilege object to be returned.
        List<PrivilegeToolFeatureAccess> finalPrivilegeToolFeatureAccessList = new LinkedList<PrivilegeToolFeatureAccess>();

        ResultSet rs = null;
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getPrivilegeToolFeatureAccessDatabase())
                    .prepareStatement(sql);

            rs = prepSt
                    .executeQuery();

            while ( rs.next() ) {
                int privilegeId             = rs.getInt(1);
                int toolId                  = rs.getInt(2);
                int featureId               = rs.getInt(3);
                int accessId                = rs.getInt(4);
                boolean show                = rs.getBoolean(5);
                boolean deleted             = rs.getBoolean(6);
                String created_by           = rs.getString(7);
                Timestamp created_date      = rs.getTimestamp(8);
                String last_updated_by      = rs.getString(9);
                Timestamp last_updated_date = rs.getTimestamp(10);

                finalPrivilegeToolFeatureAccessList.add(new PrivilegeToolFeatureAccessImpl(privilegeId, 
                        toolId, featureId, accessId, show, deleted, created_by, created_date, 
                        last_updated_by, last_updated_date));
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

        return finalPrivilegeToolFeatureAccessList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(PrivilegeToolFeatureAccess privilegeToolFeatureAccess) {
        if ( privilegeToolFeatureAccess == null ) throw new IllegalArgumentException("Access record cannot be null.");
        List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccessList = new LinkedList<PrivilegeToolFeatureAccess>();
        privilegeToolFeatureAccessList.add(privilegeToolFeatureAccess);
        createRecords(privilegeToolFeatureAccessList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccessList) {
        if ( privilegeToolFeatureAccessList == null ) throw new IllegalArgumentException("Access list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getPrivilegeToolFeatureAccessDatabase())
                    .prepareStatement(INSERT_SQL);
    
            for( PrivilegeToolFeatureAccess privilegeToolFeatureAccess : privilegeToolFeatureAccessList ) {
                prepSt.setInt(1, privilegeToolFeatureAccess.getPrivilegeId());
                prepSt.setInt(2, privilegeToolFeatureAccess.getToolId());
                prepSt.setInt(3, privilegeToolFeatureAccess.getFeatureId());
                prepSt.setInt(4, privilegeToolFeatureAccess.getAccessId());
                prepSt.setBoolean(5, privilegeToolFeatureAccess.isShow());
                prepSt.setBoolean(6, privilegeToolFeatureAccess.isDeleted());
                prepSt.setString(7, privilegeToolFeatureAccess.getCreatedBy());
                prepSt.setString(8, privilegeToolFeatureAccess.getLastUpdatedBy());            
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
        if ( privilegeToolFeatureAccess == null ) throw new IllegalArgumentException("Access record cannot be null.");
        List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccessList = new LinkedList<PrivilegeToolFeatureAccess>();
        privilegeToolFeatureAccessList.add(privilegeToolFeatureAccess);
        updateRecords(privilegeToolFeatureAccessList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<PrivilegeToolFeatureAccess> privilegeToolFeatureAccessList) {
        if ( privilegeToolFeatureAccessList == null ) throw new IllegalArgumentException("Access list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getPrivilegeToolFeatureAccessDatabase())
                    .prepareStatement(UPDATE_SQL);
    
            for( PrivilegeToolFeatureAccess privilegeToolFeatureAccess : privilegeToolFeatureAccessList ) {
                prepSt.setInt(1, privilegeToolFeatureAccess.getAccessId());
                prepSt.setBoolean(2, privilegeToolFeatureAccess.isShow());
                prepSt.setBoolean(3, privilegeToolFeatureAccess.isDeleted());
                prepSt.setString(4, privilegeToolFeatureAccess.getLastUpdatedBy());
                prepSt.setInt(5, privilegeToolFeatureAccess.getPrivilegeId());
                prepSt.setInt(6, privilegeToolFeatureAccess.getToolId());
                prepSt.setInt(7, privilegeToolFeatureAccess.getFeatureId());
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