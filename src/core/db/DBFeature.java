package core.db;

import core.tables.interfaces.Feature;

/**
 * The DBFeature interface.
 * 
 * @author Vasco
 *
 */
public interface DBFeature {
	/**
	 * Get Feature row by feature code
	 * 
	 * @param featureCd String
	 * @return Feature
	 */
	public Feature getFeature(String featureCd);

	/**
	 * Get Feature row by feature id
	 * 
	 * @param id int
	 * @return Feature
	 */
	public Feature getFeature(int id);

}
