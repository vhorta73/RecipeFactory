package core.tables.impl;

import java.sql.Timestamp;

import core.tables.interfaces.Recipe;

/**
 * The Recipe implementation.
 * 
 * @author Vasco
 *
 */
public class RecipeImpl implements Recipe {
    /**
     * The Auto-increment id.
     */
    private final int ID;
    
    /**
     * The Recipe unique name.
     */
    private final String NAME;
    
    /**
     * The Recipe final quantity.
     */
    private final Double FINAL_QUANTITY;
    
    /**
     * The Recipe final quantity unit.
     */
    private final String UNIT;
    
    /**
     * The Recipe description.
     */
    private final String DESCRIPTION;
        
    /**
     * The Recipe notes.
     */
    private final String NOTES;
        
    /**
     * Show or no show flag.
     */
    private final boolean SHOW;
        
    /**
     * The deleted flag.
     */
    private final boolean DELETED;
        
    /**
     * The username that created this record.
     */
    private final String CREATED_BY;
    
    /**
     * The date and time this record was created.
     */
    private final Timestamp CREATED_DATE;
    
    /**
     * The username that last updated this record.
     */
    private final String LAST_UPDATED_BY;
    
    /**
     * The date and time this record was last updated.
     */
    private final Timestamp LAST_UPDATED_DATE;

    /**
     * Constructor.
     * 
     * @param id int
     * @param name String
     * @param finalQuantity double
     * @param unit String
     * @param description String
     * @param notes String
     * @param show boolean
     * @param deleted boolean
     * @param createdBy String
     * @param createdDate Timestamp
     * @param lastUpdatedBy String
     * @param lastUpdatedDate Timestamp
     */
    public RecipeImpl(int id, String name, double finalQuantity, String unit, String description, 
            String notes, boolean show, boolean deleted, String createdBy, 
            Timestamp createdDate, String lastUpdatedBy, Timestamp lastUpdatedDate ) {
        this.ID = id;
        this.NAME = name;
        this.FINAL_QUANTITY = finalQuantity;
        this.UNIT = unit;
        this.DESCRIPTION = description;
        this.NOTES = notes;
        this.SHOW = show;
        this.DELETED = deleted;
        this.CREATED_BY = createdBy;
        this.CREATED_DATE = createdDate;
        this.LAST_UPDATED_BY = lastUpdatedBy;
        this.LAST_UPDATED_DATE = lastUpdatedDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreatedBy() {
        return CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp getCreatedDate() {
        return CREATED_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastUpdatedBy() {
        return LAST_UPDATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp getLastUpdatedDate() {
        return LAST_UPDATED_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDeleted() {
        return DELETED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShow() {
        return SHOW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getFinalQuantity() {
        return FINAL_QUANTITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUnit() {
        return UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNotes() {
        return NOTES;
    }
}
