package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBPrivilegeTool;
import core.tables.impl.PrivilegeToolImpl;
import core.tables.interfaces.PrivilegeTool;


/**
 * The PrivilegeTool table DB access.
 * 
 * @author Vasco
 *
 */
public class DBPrivilegeToolImpl implements DBPrivilegeTool {
    /**
     * Access can only be granted if a session is open.
     */
    private Session session;

    /**
     * The insert sql for new records.
     */
    private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getPrivilegeToolTable() 
            + " (privilege_id,tool_id,`show`,deleted,created_by,last_updated_by)"
            + " VALUES(?,?,?,?,?,?)";
    
    /**
     * The update sql for old record changes.
     */
    private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getPrivilegeToolTable() 
            + " SET `show` = ?, deleted = ?, last_updated_by = ? "
            + " WHERE privilege_id = ? AND tool_id = ? ";

    /**
     * The Constructor requiring a valid initialised session
     * to gain access to the database.
     * 
     * @param dbConnection
     */
    public DBPrivilegeToolImpl(Session session) {
        this.session = session;
    }
    
    /**
     * Select a PrivilegeTool by privilegeId
     * 
     * @param id int
     * @return PrivilegeTool
     */
    @Override
    public PrivilegeTool getPrivilegeTool(int privilegeId) {
        // Initialise the final Privilege object to be returned.
        return getResults("SELECT * FROM " + DatabaseTableName.getPrivilegeToolTable() + " WHERE privilege_id = '"+privilegeId+"'").get(0);
    }
    
    /**
     * Get a list of results from the database.
     * 
     * @param sql
     * @return List PrivilegeTool
     */
    private List<PrivilegeTool> getResults(String sql) {
        // Validate argument
        if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

        // Initialise the final PrivilegeTool object to be returned.
        List<PrivilegeTool> finalPrivilegeToolList = new LinkedList<PrivilegeTool>();

        ResultSet rs = null;
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getPrivilegeToolDatabase())
                    .prepareStatement(sql);

            rs = prepSt
                    .executeQuery();

            while ( rs.next() ) {
                int id                      = rs.getInt(1);
                int privilegeId             = rs.getInt(2);
                int toolId                  = rs.getInt(3);
                boolean show                = rs.getBoolean(4);
                boolean deleted             = rs.getBoolean(5);
                String created_by           = rs.getString(6);
                Timestamp created_date      = rs.getTimestamp(7);
                String last_updated_by      = rs.getString(8);
                Timestamp last_updated_date = rs.getTimestamp(9);

                finalPrivilegeToolList.add(new PrivilegeToolImpl(id, privilegeId, toolId, show, 
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

        return finalPrivilegeToolList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecord(PrivilegeTool privilegeTool) {
        if ( privilegeTool == null ) throw new IllegalArgumentException("Access record cannot be null.");
        List<PrivilegeTool> privilegeToolList = new LinkedList<PrivilegeTool>();
        privilegeToolList.add(privilegeTool);
        createRecords(privilegeToolList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<PrivilegeTool> privilegeToolList) {
        if ( privilegeToolList == null ) throw new IllegalArgumentException("Access list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getPrivilegeToolDatabase())
                    .prepareStatement(INSERT_SQL);
    
            for( PrivilegeTool privilegeTool : privilegeToolList ) {
                prepSt.setInt(1, privilegeTool.getPrivilegeId());
                prepSt.setInt(2, privilegeTool.getToolId());
                prepSt.setBoolean(3, privilegeTool.isShow());
                prepSt.setBoolean(4, privilegeTool.isDeleted());
                prepSt.setString(5, privilegeTool.getCreatedBy());
                prepSt.setString(6, privilegeTool.getLastUpdatedBy());            
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
        if ( privilegeTool == null ) throw new IllegalArgumentException("Access record cannot be null.");
        List<PrivilegeTool> privilegeToolList = new LinkedList<PrivilegeTool>();
        privilegeToolList.add(privilegeTool);
        updateRecords(privilegeToolList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<PrivilegeTool> privilegeToolList) {
        if ( privilegeToolList == null ) throw new IllegalArgumentException("Access list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
                    .getConnection(DatabaseTableName.getPrivilegeToolDatabase())
                    .prepareStatement(UPDATE_SQL);
    
            for( PrivilegeTool privilegeTool : privilegeToolList ) {
                prepSt.setBoolean(1, privilegeTool.isShow());
                prepSt.setBoolean(2, privilegeTool.isDeleted());
                prepSt.setString(3, privilegeTool.getLastUpdatedBy());
                prepSt.setInt(4, privilegeTool.getPrivilegeId());
                prepSt.setInt(5, privilegeTool.getToolId());
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