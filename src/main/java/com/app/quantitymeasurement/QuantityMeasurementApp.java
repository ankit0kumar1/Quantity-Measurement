package com.app.quantitymeasurement;

public class QuantityMeasurementApp {

	public static boolean demonstrateLengthEquality(Length l1, Length l2) {
		return l1.equals(l2);
	}

	// Comparison demo
	public static boolean demonstrateLengthComparison(double v1, Length.LengthUnit u1, double v2,
			Length.LengthUnit u2) {
		Length l1 = new Length(v1, u1);
		Length l2 = new Length(v2, u2);
		return demonstrateLengthEquality(l1, l2);
	}

	// UC5
	// Static conversion API
	public static double convert(double value, Length.LengthUnit from, Length.LengthUnit to) {

		if (from == null || to == null)
			throw new IllegalArgumentException("Units cannot be null");

		if (Double.isNaN(value) || Double.isInfinite(value))
			throw new IllegalArgumentException("Invalid numeric value");

		double baseValue = value * from.getConversionFactor();
		return baseValue / to.getConversionFactor();
	}

	// Overloaded conversion method (using Length object)
	public static Length demonstrateLengthConversion(Length length, Length.LengthUnit targetUnit) {
		return length.convertTo(targetUnit);
	}

	// Overloaded conversion method (raw values)
	public static Length demonstrateLengthConversion(double value, Length.LengthUnit from, Length.LengthUnit to) {
		return new Length(value, from).convertTo(to);
	}

	// UC6 NEW METHOD
	public static Length demonstrateLengthAddition(Length l1, Length l2) {
		return l1.add(l2);
	}
	
	 //  UC7 NEW 
    public static Length demonstrateLengthAddition(Length l1,
                                                   Length l2,
                                                   Length.LengthUnit targetUnit) {
        return l1.add(l2, targetUnit);
    }

	public static void main(String[] args) {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println(l1.add(l2, Length.LengthUnit.FEET));
        System.out.println(l1.add(l2, Length.LengthUnit.INCHES));
        System.out.println(l1.add(l2, Length.LengthUnit.YARDS));
        System.out.println(l1.add(l2, Length.LengthUnit.CENTIMETERS));

	}

}
