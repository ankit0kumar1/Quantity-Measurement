package com.app.quantitymeasurement;

public class QuantityMeasurementApp {
	
	public static boolean demonstrateLengthEquality(Length l1, Length l2) {
		return l1.equals(l2);
	}
	
	// Feet equality demo
	public static void demonstrateFeetEquality() {
		Length f1 = new Length(1.0, Length.LengthUnit.FEET);
		Length f2 = new Length(1.0, Length.LengthUnit.FEET);
		System.out.println("Feet Equal: " + demonstrateLengthEquality(f1, f2));
	}
	
	// Inches equality demo
	public static void demonstrateInchesEquality() {
		Length i1 = new Length(1.0, Length.LengthUnit.INCHES);
		Length i2 = new Length(1.0 , Length.LengthUnit.INCHES);
		System.out.println("Inches Equal: " + demonstrateLengthEquality(i1, i2));
	}
	// Feet & Inches comparison demo
	public static void demonstrateFeetInchesComparison() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
		System.out.println("Feet & Inches equal? " + demonstrateLengthEquality(l1, l2));
	}

	public static void main(String[] args) {
		demonstrateFeetEquality();
		demonstrateInchesEquality();
		demonstrateFeetInchesComparison();
		
	}
	
}
