package core;

import java.math.BigDecimal;

import constants.EnumUnit;
import constants.UnitRatio;

/**
 * Unit Conversion static class.
 * 
 * @author Vasco
 *
 */
public class UnitConversion {
	/**
	 * Convert the given amount and unit to the requested unit.
	 * 
	 * @param amount
	 * @param initialUnit
	 * @param finalUnit
	 * @return double converted amount
	 */
	public static double convert(double amount, EnumUnit initialUnit, EnumUnit finalUnit) { 
		if ( initialUnit == null ) throw new IllegalArgumentException("Initial Unit cannot be null.");
		if ( finalUnit == null ) throw new IllegalArgumentException("Final Unit cannot be null.");
		
		// Don't ask, don't tell... Java's hell!
		BigDecimal ratioBD  = BigDecimal.valueOf(UnitRatio.getRatio(initialUnit, finalUnit));
		BigDecimal amountBD = BigDecimal.valueOf(amount);
		BigDecimal result   = ratioBD.multiply(amountBD);

		return result.doubleValue();
	};

}
