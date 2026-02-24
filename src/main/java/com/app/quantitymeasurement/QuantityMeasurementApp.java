package com.app.quantitymeasurement;

public class QuantityMeasurementApp {

	// generic equality demo
	public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {

		return quantity1.equals(quantity2);
	}

	// generic conversion demo
	// generic conversion demo
	public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
		return quantity.convertTo(targetUnit);
	}

	// generic addition (implicit target unit)
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1,
			Quantity<U> quantity2) {

		return quantity1.add(quantity2);
	}

	// generic addition (explicit target unit)
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2,
			U targetUnit) {

		return quantity1.add(quantity2, targetUnit);
	}

	public static void main(String[] args) {

		// ----- LENGTH DEMO -----
		Quantity<LengthUnit> length1 = new Quantity<>(1, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12, LengthUnit.INCHES);

		// equality check
		System.out.println("1 Feet equals 12 Inches : " + length1.equals(length2));

		// conversion
		Quantity<LengthUnit> convertedLength = length1.convertTo(LengthUnit.INCHES);
		System.out.println("1 Feet in Inches : " + convertedLength.getValue());

		// addition
		Quantity<LengthUnit> addedLength = length1.add(length2);
		System.out.println("1 Feet + 12 Inches in Feet : " + addedLength.getValue());

		// ----- WEIGHT DEMO -----
		Quantity<WeightUnit> weight1 = new Quantity<>(1, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> weight2 = new Quantity<>(1000, WeightUnit.GRAM);

		// equality check
		System.out.println("1 Kg equals 1000 g : " + weight1.equals(weight2));

		// conversion
		Quantity<WeightUnit> convertedWeight = weight1.convertTo(WeightUnit.GRAM);
		System.out.println("1 Kg in grams : " + convertedWeight.getValue());

		// addition
		Quantity<WeightUnit> addedWeight = weight1.add(weight2);
		System.out.println("1 Kg + 1000 g in Kg : " + addedWeight.getValue());

		// ================= UC11 VOLUME DEMO =================
		Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> volume3 = new Quantity<>(1.0, VolumeUnit.GALLON);

		// Equality
		System.out.println("Volume Equality L↔mL: " + demonstrateEquality(volume1, volume2));
		System.out.println("Volume Equality L↔Gallon: " + demonstrateEquality(volume1, volume3));

		// Conversion
		System.out.println("Convert Litre → mL: " + demonstrateConversion(volume1, VolumeUnit.MILLILITRE));
		System.out.println("Convert Gallon → Litre: " + demonstrateConversion(volume3, VolumeUnit.LITRE));

		// Addition (implicit target unit)
		System.out.println("Add L + mL: " + demonstrateAddition(volume1, volume2));

		// Addition (explicit target unit)
		System.out.println("Add L + Gallon → mL: " + demonstrateAddition(volume1, volume3, VolumeUnit.MILLILITRE));

	}
}