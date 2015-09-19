package core.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.db.interfaces.DBFeature;
import core.tables.impl.FeatureImpl;
import core.tables.interfaces.Feature;

/**
 * The Feature table DB access.
 * 
 * @author Vasco
 *
 */
public class DBFeatureImpl implements DBFeature {
	/**
	 * Access can only be granted if a session is open.
	 */
	private Session session;

	/**
	 * The final insert sql for new records.
	 */
	private final String INSERT_SQL = "INSERT INTO " + DatabaseTableName.getFeatureTable() 
            + " (feature_cd,display_name,`show`,deleted,description,created_by,last_updated_by)"
            + " VALUES(?,?,?,?,?,?,?)";

	/**
	 * The final update sql for old record changes.
	 */
	private final String UPDATE_SQL = "UPDATE " + DatabaseTableName.getFeatureTable() 
            + " SET feature_cd = ?, display_name = ?, description = ?, `show` = ?, deleted = ?, "
            + " last_updated_by = ? WHERE id = ?";

	/**
	 * The Constructor requiring a valid initialised session
	 * to gain access to the database.
	 * 
	 * @param dbConnection
	 */
	public DBFeatureImpl(Session session) {
		this.session = session;
	}
	
	/**
	 * Select a Feature by code
	 * 
	 * @param featureCd String
	 * @return Feature
	 */
	@Override
	public Feature getFeature(String featureCd) {
		// Validate argument
		if ( featureCd == null ) throw new IllegalArgumentException("Feature name cannot be null.");

        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DatabaseTableName.getFeatureTable() + " WHERE feature_cd = '"+featureCd+"'").get(0);
	}

	/**
	 * Select an Feature by id
	 * 
	 * @param id int
	 * @return Feature
	 */
	@Override
	public Feature getFeature(int id) {
        // Initialise the final Ingredient object to be returned.
		return getResults("SELECT * FROM " + DatabaseTableName.getFeatureTable() + " WHERE id = '"+id+"'").get(0);
	}
	
	/**
	 * Get a list of results from the database.
	 * 
	 * @param sql
	 * @return List Feature
	 */
	private List<Feature> getResults(String sql) {
		// Validate argument
		if ( sql == null ) throw new IllegalArgumentException("SQL query cannot be null.");

		// Initialise the final Ingredient object to be returned.
		List<Feature> finalFeatureList = new LinkedList<Feature>();

		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = this.session.getDB()
					.getConnection(DatabaseTableName.getFeatureDatabase())
					.prepareStatement(sql);
			rs = prepSt
					.executeQuery();

			while ( rs.next() ) {
				int id                      = rs.getInt(1);
				String featureCd            = rs.getString(2);
				String displayName          = rs.getString(3);
				String description          = rs.getString(4);
				boolean show                = rs.getBoolean(5);
				boolean deleted             = rs.getBoolean(6);
				String created_by           = rs.getString(7);
				Timestamp created_date      = rs.getTimestamp(8);
				String last_updated_by      = rs.getString(9);
				Timestamp last_updated_date = rs.getTimestamp(10);

				finalFeatureList.add(new FeatureImpl(id, featureCd, displayName, description, show, deleted,
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

		return finalFeatureList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createRecord(Feature feature) {
        if ( feature == null ) throw new IllegalArgumentException("Feature record cannot be null.");
        
        List<Feature> featureList = new LinkedList<Feature>();
        featureList.add(feature);
        createRecords(featureList);
	}

	/**
    /**
     * {@inheritDoc}
     */
    @Override
    public void createRecords(List<Feature> featureList) {
        if ( featureList == null ) throw new IllegalArgumentException("Feature list record cannot be null.");
        
        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getFeatureDatabase())
            		.prepareStatement(INSERT_SQL);
    
            for( Feature feature : featureList ) {
                prepSt.setString(1, feature.getFeatureCd());
                prepSt.setString(2, feature.getDisplayName());
                prepSt.setString(3, feature.getDescription());
                prepSt.setBoolean(4, feature.isShow());
                prepSt.setBoolean(5, feature.isDeleted());
                prepSt.setString(6, feature.getCreatedBy());
                prepSt.setString(7, feature.getLastUpdatedBy());            
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
    public void updateRecord(Feature feature) {
        if ( feature == null ) throw new IllegalArgumentException("Feature record cannot be null.");

        List<Feature> featureList = new LinkedList<Feature>();
        featureList.add(feature);
        updateRecords(featureList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecords(List<Feature> featureList) {
        if ( featureList == null ) throw new IllegalArgumentException("Feature list record cannot be null.");

        PreparedStatement prepSt = null;
        try {
            prepSt = this.session.getDB()
            		.getConnection(DatabaseTableName.getFeatureDatabase())
            		.prepareStatement(UPDATE_SQL);
    
            for( Feature feature : featureList ) {
                prepSt.setString(1, feature.getFeatureCd());
                prepSt.setString(2, feature.getDisplayName());
                prepSt.setString(3, feature.getDescription());
                prepSt.setBoolean(4, feature.isShow());
                prepSt.setBoolean(5, feature.isDeleted());
                prepSt.setString(6, feature.getLastUpdatedBy());
                prepSt.setInt(7, feature.getId());            
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