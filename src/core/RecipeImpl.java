package core;

import constants.EnumUnit;
/**
 * The Ingredient implementation.
 * 
 * @author Vasco
 *
 */
public class RecipeImpl implements Recipe {
	/**
	 * The Ingredient value
	 */
	private final double amount;
	
	/**
	 * The Ingredient default Unit
	 */
	private final EnumUnit defaultUnit;
	
	/**
	 * Constructor requesting Ingredient's
	 * value and unit.
	 * 
	 * @param amount
	 * @param unit
	 */
	public RecipeImpl(double amount, EnumUnit unit) {
		this.amount = amount;
		this.defaultUnit = unit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getAmount(EnumUnit unit) {
		// Deal with null argument exception.
		if ( unit == null ) throw new IllegalArgumentException("Unit cannot be null.");
		
		// Return the converted amount.
		return UnitConversion.convert(amount,defaultUnit,unit);
	}

	@Override
	public String getCreatedBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastUpdatedBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastUpdatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVendor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNotes() {
		// TODO Auto-generated method stub
		return null;
	}
}
