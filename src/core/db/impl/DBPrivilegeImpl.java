package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBPrivilege;
import core.tables.impl.PrivilegeImpl;
import core.tables.interfaces.Privilege;

/**
 * The Privilege table DB access.
 * 
 * @author Vasco
 *
 */
public class DBPrivilegeImpl implements DBPrivilege {
    /**
     * Access can only be granted if a session is open.
     */
    private Session session;

    /**
     * The insert sql for new records.
     */
    private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getPrivilegeTable() 
            + " (display_name,status_id,description,`show`,deleted,created_by,last_updated_by)"
            + " VALUES(?,?,?,?,?,?,?)";
    
    /**
     * The update sql for old record changes.
     */
    private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getPrivilegeTable() 
            + " SET display_name = ?, status_id = ?, description = ?, `show` = ?, deleted = ?, "
            + " last_updated_by = ? WHERE id = ?";

    /**
     * The Constructor requiring a valid initialised session
     * to gain access to the database.
     * 
     * @param dbConnection
     */
    public DBPrivilegeImpl(Session session) {
        this.session = session;
    }
    
    /**
     * Select a Privilege by id
     * 
     * @param id int
     * @return Privilege
     */
    @Override
    public Privilege getPrivilege(int id) {
        // Initialise the final Privilege object to be returned.
        return getResults("SELECT * FROM " + DatabaseTableName.getPrivilegeTable() + " WHERE id = '"+id+"'").get(0);
    }
    
    /**
     * Get a list of results from the database.
     * 
     * @param sql
     * @return List Privilege
     */
    private List<Privilege> getResults(String sql) {
        // Validate argument
        if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

        // Initialise the final Privilege object to be returned.
        List<Privilege> finalPrivilegeList = new LinkedList<Privilege>();

        ResultSet rs = null;
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getPrivilegeDatabase())
                    .prepareStatement(sql);

            rs = prepSt
                    .executeQuery();

            while ( rs.next() ) {
                int id                      = rs.getInt(1);
                String displayName          = rs.getString(2);
                int statusId                = rs.getInt(3);
                String description          = rs.getString(4);
                boolean show                = rs.getBoolean(5);
                boolean deleted             = rs.getBoolean(6);
                String created_by           = rs.getString(7);
                Timestamp created_date      = rs.getTimestamp(8);
                String last_updated_by      = rs.getString(9);
                Timestamp last_updated_date = rs.getTimestamp(10);

                finalPrivilegeList.add(new PrivilegeImpl(id, displayName, statusId, description, show, 
                        deleted, created_by, created_date, last_updated_by, last_updated_date));
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

        return finalPrivilegeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(Privilege privilege) {
        if ( privilege == null ) throw new IllegalArgumentException("Access record cannot be null.");
        List<Privilege> privilegeList = new LinkedList<Privilege>();
        privilegeList.add(privilege);
        createRecords(privilegeList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<Privilege> privilegeList) {
        if ( privilegeList == null ) throw new IllegalArgumentException("Access list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getPrivilegeDatabase())
                    .prepareStatement(INSERT_SQL);
    
            for( Privilege privilege : privilegeList ) {
                prepSt.setString(1, privilege.getDisplayName());
                prepSt.setInt(2, privilege.getStatusId());
                prepSt.setString(3, privilege.getDescription());
                prepSt.setBoolean(4, privilege.isShow());
                prepSt.setBoolean(5, privilege.isDeleted());
                prepSt.setString(6, privilege.getCreatedBy());
                prepSt.setString(7, privilege.getLastUpdatedBy());            
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
        if ( privilege == null ) throw new IllegalArgumentException("Access record cannot be null.");
        List<Privilege> privilegeList = new LinkedList<Privilege>();
        privilegeList.add(privilege);
        updateRecords(privilegeList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<Privilege> privilegeList) {
        if ( privilegeList == null ) throw new IllegalArgumentException("Access list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getPrivilegeDatabase())
                    .prepareStatement(UPDATE_SQL);
    
            for( Privilege privilege : privilegeList ) {
                prepSt.setString(1, privilege.getDisplayName());
                prepSt.setInt(2, privilege.getStatusId());
                prepSt.setString(3, privilege.getDescription());
                prepSt.setBoolean(4, privilege.isShow());
                prepSt.setBoolean(5, privilege.isDeleted());
                prepSt.setString(6, privilege.getLastUpdatedBy());
                prepSt.setInt(7, privilege.getId());            
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