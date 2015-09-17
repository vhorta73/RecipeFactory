package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBStatus;
import core.tables.impl.StatusImpl;
import core.tables.interfaces.Status;

/**
 * The Status table DB access.
 * 
 * @author Vasco
 *
 */
public class DBStatusImpl implements DBStatus {
    /**
     * Access can only be granted if a session is open.
     */
    private Session session;

    /**
     * The insert sql for new records.
     */
    private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getStatusTable() 
            + " (status_cd,display_name,description,`show`,deleted,created_by,last_updated_by)"
            + " VALUES(?,?,?,?,?,?,?)";
    
    /**
     * The update sql for old record changes.
     */
    private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getStatusTable() 
            + " SET status_cd = ?, display_name = ?, description = ?, `show` = ?, deleted = ?,"
            + " last_updated_by = ? WHERE id = ?";

    /**
     * The Constructor requiring a valid initialised session
     * to gain access to the database.
     * 
     * @param dbConnection
     */
    public DBStatusImpl(Session session) {
        this.session = session;
    }
    
    /**
     * Select a Status by status_cd
     * 
     * @param status_cd String
     * @return Status
     */
    @Override
    public Status getStatus(String status_cd) {
        // Validate argument
        if ( status_cd == null ) throw new IllegalArgumentException("Status code cannot be null.");

        // Initialise the final Ingredient object to be returned.
        return getResults("SELECT * FROM " + DatabaseTableName.getStatusTable() + " WHERE status_cd = '"+status_cd+"'").get(0);
    }

    /**
     * Select a Status by id
     * 
     * @param id statusId
     * @return Status
     */
    @Override
    public Status getStatus(int statusId) {
        // Initialise the final Ingredient object to be returned.
        return getResults("SELECT * FROM " + DatabaseTableName.getStatusTable() + " WHERE id = '"+statusId+"'").get(0);
    }
    
    /**
     * Get a list of results from the database.
     * 
     * @param sql
     * @return List Status
     */
    private List<Status> getResults(String sql) {
        // Validate argument
        if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

        // Initialise the final Ingredient object to be returned.
        List<Status> finalStatusList = new LinkedList<Status>();

        ResultSet rs = null;
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getStatusDatabase())
                    .prepareStatement(sql);

            rs = prepSt.executeQuery();

            while ( rs.next() ) {
                int id                      = rs.getInt(1);
                String status_cd            = rs.getString(2);
                String display_name         = rs.getString(3);
                String description          = rs.getString(4);
                boolean show                = rs.getBoolean(5);
                boolean deleted             = rs.getBoolean(6);
                String created_by           = rs.getString(7);
                Timestamp created_date      = rs.getTimestamp(8);
                String last_updated_by      = rs.getString(9);
                Timestamp last_updated_date = rs.getTimestamp(10);

                finalStatusList.add(new StatusImpl(id, status_cd, display_name, description, show, deleted,
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

        return finalStatusList;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(Status status) {
        if ( status == null ) throw new IllegalArgumentException("Status record cannot be null.");

        List<Status> statusList = new LinkedList<Status>();
        statusList.add(status);
        createRecords(statusList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<Status> statusList) {
        if ( statusList == null ) throw new IllegalArgumentException("Status list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getAccessDatabase())
                    .prepareStatement(INSERT_SQL);
    
            for( Status status : statusList ) {
                prepSt.setString(1, status.getStatusCd());
                prepSt.setString(2, status.getDisplayName());
                prepSt.setString(3, status.getDescription());
                prepSt.setBoolean(4, status.isShow());
                prepSt.setBoolean(5, status.isDeleted());
                prepSt.setString(6, status.getCreatedBy());
                prepSt.setString(7, status.getLastUpdatedBy());            
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
    public void updateRecord(Status status) {
        if ( status == null ) throw new IllegalArgumentException("Status record cannot be null.");

        List<Status> statusList = new LinkedList<Status>();
        statusList.add(status);
        updateRecords(statusList);
            }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<Status> statusList) {
        if ( statusList == null ) throw new IllegalArgumentException("Status list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getAccessDatabase())
                    .prepareStatement(UPDATE_SQL);
    
            for( Status status : statusList ) {
                prepSt.setString(1, status.getStatusCd());
                prepSt.setString(2, status.getDisplayName());
                prepSt.setString(3, status.getDescription());
                prepSt.setBoolean(4, status.isShow());
                prepSt.setBoolean(5, status.isDeleted());
                prepSt.setString(6, status.getLastUpdatedBy());
                prepSt.setInt(7, status.getId());            
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
