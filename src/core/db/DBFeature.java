package core.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import web.interfaces.Session;
import constants.DatabaseTableName;
import core.tables.impl.FeatureImpl;
import core.tables.interfaces.Feature;

/**
 * The Feature table DB access.
 * 
 * @author Vasco
 *
 */
public class DBFeature {
	/**
	 * Access can only be granted if a session is open.
	 */
	private Session session;

	/**
	 * The Constructor requiring a valid initialised session
	 * to gain access to the database.
	 * 
	 * @param dbConnection
	 */
	public DBFeature(Session session) {
		this.session = session;
	}
	
	/**
	 * Select a Feature by code
	 * 
	 * @param featureCd String
	 * @return Feature
	 */
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
				String created_by           = rs.getString(5);
				Timestamp created_date      = rs.getTimestamp(6);
				String last_updated_by      = rs.getString(7);
				Timestamp last_updated_date = rs.getTimestamp(8);

				finalFeatureList.add(new FeatureImpl(id, featureCd, displayName, description, 
						created_by, created_date, last_updated_by, last_updated_date));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prepSt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return finalFeatureList;
	}
}
