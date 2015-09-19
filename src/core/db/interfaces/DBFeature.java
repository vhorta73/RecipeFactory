package core.db.interfaces;

import java.util.List;

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

    /**
     * Create a new record with given information.
     * 
     * @param feature Feature
     */
    public void createRecord(Feature feature);
    
    /**
     * Create new records with given information.
     * 
     * @param featureList List Feature
     */
    public void createRecords(List<Feature> featureList);
    
    /**
     * Update an existing record with given information.
     * 
     * @param feature Feature
     */
    public void updateRecord(Feature feature);
    
    /**
     * Update existing records with given information.
     * 
     * @param feature List Feature
     */
    public void updateRecords(List<Feature> feature);
}
