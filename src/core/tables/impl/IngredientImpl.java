package core.tables.impl;

import java.sql.Timestamp;

import core.tables.interfaces.Ingredient;

/**
 * The Ingredient implementation.
 * 
 * @author Vasco
 *
 */
public class IngredientImpl implements Ingredient {
	/**
	 * The Auto-increment id.
	 */
	private final int ID;
	
	/**
	 * The Ingredient unique name.
	 */
	private final String NAME;
	
	/**
	 * The Ingredient description.
	 */
	private final String DESCRIPTION;
	
	/**
	 * Show or no show flag.
	 */
	private final boolean SHOW;
		
	/**
	 * The deleted flag.
	 */
	private final boolean DELETED;
	
	/**
	 * The Ingredient notes.
	 */
	private final String NOTES;
	
	/**
	 * The username that created this ingredient.
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
	 * This constructor requires all the table database columns
	 * that matter for the system, which must be always supplied
	 * and the construction time.
	 * 
	 * @param id int auto increment
	 * @param name of the ingredient
	 * @param description for the ingredient
	 * @param notes on the ingredient
	 * @param createdBy username
	 * @param createdDate String date time
	 * @param lastUpdatedBy username
	 * @param lastUpdatedDate String date time
	 */
	public IngredientImpl(int id, String name, String description, String notes,  boolean show, boolean deleted,
			String createdBy, Timestamp createdDate, String lastUpdatedBy, Timestamp lastUpdatedDate ) {
		this.ID = id;
		this.NAME = name;
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
	public String getName() {
		return NAME;
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
	public String getNotes() {
		return NOTES;
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
}
