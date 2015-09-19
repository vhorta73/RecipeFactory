package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBAccess;
import core.tables.impl.AccessImpl;
import core.tables.interfaces.Access;

/**
 * The Access table DB access.
 * 
 * @author Vasco
 *
 */
public class DBAccessImpl implements DBAccess {
    /**
     * Access can only be granted if a session is open.
     */
    private Session session;

    /**
     * The insert sql for new records.
     */
    private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getAccessTable() 
            + " (access_cd,display_name,description,`show`,deleted,created_by,last_updated_by)"
            + " VALUES(?,?,?,?,?,?,?)";
    
    /**
     * The update sql for old record changes.
     */
    private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getAccessTable() 
            + " SET access_cd = ?, display_name = ?, description = ?, `show` = ?, deleted = ?, "
            + " last_updated_by = ? WHERE id = ?";

    /**
     * The Constructor requiring a valid initialised session
     * to gain access to the database.
     * 
     * @param dbConnection
     */
    public DBAccessImpl(Session session) {
        this.session = session;
    }
    
    /**
     * Select an Access by code
     * 
     * @param accessCd String
     * @return Access
     */
    @Override
    public Access getAccess(String accessCd) {
        // Validate argument
        if ( accessCd == null ) throw new IllegalArgumentException("Access name cannot be null.");

        // Initialise the final Access object to be returned.
        return getResults("SELECT * FROM " + DatabaseTableName.getAccessTable() + " WHERE access_cd = '"+accessCd+"'").get(0);
    }

    /**
     * Select an Access by id
     * 
     * @param id int
     * @return Access
     */
    @Override
    public Access getAccess(int id) {
        // Initialise the final Access object to be returned.
        return getResults("SELECT * FROM " + DatabaseTableName.getAccessTable() + " WHERE id = '"+id+"'").get(0);
    }
    
    /**
     * Get a list of results from the database.
     * 
     * @param sql
     * @return List Access
     */
    private List<Access> getResults(String sql) {
        // Validate argument
        if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

        // Initialise the final Access object to be returned.
        List<Access> finalAccessList = new LinkedList<Access>();

        ResultSet rs = null;
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getAccessDatabase())
                    .prepareStatement(sql);

            rs = prepSt
                    .executeQuery();

            while ( rs.next() ) {
                int id                      = rs.getInt(1);
                String accessCd             = rs.getString(2);
                String displayName          = rs.getString(3);
                String description          = rs.getString(4);
                boolean show                = rs.getBoolean(5);
                boolean deleted             = rs.getBoolean(6);
                String created_by           = rs.getString(7);
                Timestamp created_date      = rs.getTimestamp(8);
                String last_updated_by      = rs.getString(9);
                Timestamp last_updated_date = rs.getTimestamp(10);

                finalAccessList.add(new AccessImpl(id, accessCd, displayName, description, show, deleted,
                        created_by, created_date, last_updated_by, last_updated_date));
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

        return finalAccessList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(Access access) {
        if ( access == null ) throw new IllegalArgumentException("Access record cannot be null.");
        List<Access> accessList = new LinkedList<Access>();
        accessList.add(access);
        createRecords(accessList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<Access> accessList) {
        if ( accessList == null ) throw new IllegalArgumentException("Access list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getAccessDatabase())
            		.prepareStatement(INSERT_SQL);
    
            for( Access access : accessList ) {
                prepSt.setString(1, access.getAccessCd());
                prepSt.setString(2, access.getDisplayName());
                prepSt.setString(3, access.getDescription());
                prepSt.setBoolean(4, access.isShow());
                prepSt.setBoolean(5, access.isDeleted());
                prepSt.setString(6, access.getCreatedBy());
                prepSt.setString(7, access.getLastUpdatedBy());            
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
    public void updateRecord(Access access) {
        if ( access == null ) throw new IllegalArgumentException("Access record cannot be null.");
        List<Access> accessList = new LinkedList<Access>();
        accessList.add(access);
        updateRecords(accessList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<Access> accessList) {
        if ( accessList == null ) throw new IllegalArgumentException("Access list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getAccessDatabase())
            		.prepareStatement(UPDATE_SQL);
    
            for( Access access : accessList ) {
                prepSt.setString(1, access.getAccessCd());
                prepSt.setString(2, access.getDisplayName());
                prepSt.setString(3, access.getDescription());
                prepSt.setBoolean(4, access.isShow());
                prepSt.setBoolean(5, access.isDeleted());
                prepSt.setString(6, access.getLastUpdatedBy());
                prepSt.setInt(7, access.getId());            
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