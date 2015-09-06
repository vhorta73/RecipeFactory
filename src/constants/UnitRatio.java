package constants;

/**
 * Unit Ratio class, defining the ratio between two units.
 * 
 * @author Vasco
 *
 */
public class UnitRatio {
	/**
	 * Return the ration of conversion 
	 * when converting unit1 to unit 2.
	 * 
	 * @param unit1
	 * @param unit2
	 * @return ratio
	 */
	public static double getRatio(EnumUnit initialUnit, EnumUnit finalUnit){
		if ( initialUnit == null ) throw new IllegalArgumentException("Initial unit cannot be null.");
		if ( finalUnit == null )   throw new IllegalArgumentException("Final unit cannot be null.");

		if ( initialUnit.equals(EnumUnit.Kg) ) return getKgRatio(finalUnit);
		if ( initialUnit.equals(EnumUnit.g) )  return getGRatio(finalUnit);
		if ( initialUnit.equals(EnumUnit.mg) ) return getMgRatio(finalUnit);

		if ( initialUnit.equals(EnumUnit.L) )  return getLRatio(finalUnit);
		if ( initialUnit.equals(EnumUnit.dl) ) return getDlRatio(finalUnit);
		if ( initialUnit.equals(EnumUnit.cl) ) return getClRatio(finalUnit);
		if ( initialUnit.equals(EnumUnit.ml) ) return getMlRatio(finalUnit);

		throw new IllegalArgumentException("Unknown initial unit "+initialUnit);
	}
	
	private static double getKgRatio(EnumUnit unit) {
		if ( unit.equals(EnumUnit.Kg)) return 1;
		if ( unit.equals(EnumUnit.g) ) return 1000; 
		if ( unit.equals(EnumUnit.mg)) return 1000000;
		throw new IllegalArgumentException("Unknown unit "+unit);
	}

	private static double getGRatio(EnumUnit unit) {
		if ( unit.equals(EnumUnit.Kg)) return 0.001;
		if ( unit.equals(EnumUnit.g) ) return 1; 
		if ( unit.equals(EnumUnit.mg)) return 1000;
		throw new IllegalArgumentException("Unknown unit "+unit);
	}

	private static double getMgRatio(EnumUnit unit) {
		if ( unit.equals(EnumUnit.Kg)) return 0.000001;
		if ( unit.equals(EnumUnit.g) ) return 1000; 
		if ( unit.equals(EnumUnit.mg)) return 1;
		throw new IllegalArgumentException("Unknown unit "+unit);
	}

	private static double getLRatio(EnumUnit unit) {
		if ( unit.equals(EnumUnit.L))  return 1;
		if ( unit.equals(EnumUnit.dl)) return 10; 
		if ( unit.equals(EnumUnit.cl)) return 100;
		if ( unit.equals(EnumUnit.ml)) return 1000;
		throw new IllegalArgumentException("Unknown unit "+unit);
	}

	private static double getDlRatio(EnumUnit unit) {
		if ( unit.equals(EnumUnit.L))  return 0.1;
		if ( unit.equals(EnumUnit.dl)) return 1; 
		if ( unit.equals(EnumUnit.cl)) return 10;
		if ( unit.equals(EnumUnit.ml)) return 100;
		throw new IllegalArgumentException("Unknown unit "+unit);
	}

	private static double getClRatio(EnumUnit unit) {
		if ( unit.equals(EnumUnit.L))  return 0.01;
		if ( unit.equals(EnumUnit.dl)) return 0.1; 
		if ( unit.equals(EnumUnit.cl)) return 1;
		if ( unit.equals(EnumUnit.ml)) return 10;
		throw new IllegalArgumentException("Unknown unit "+unit);
	}

	private static double getMlRatio(EnumUnit unit) {
		if ( unit.equals(EnumUnit.L))  return 0.001;
		if ( unit.equals(EnumUnit.dl)) return 0.01; 
		if ( unit.equals(EnumUnit.cl)) return 0.1;
		if ( unit.equals(EnumUnit.ml)) return 1;
		throw new IllegalArgumentException("Unknown unit "+unit);
	}
}
