package com.app.quantitymeasurement;

public class Length {
	private final double value;
	private final LengthUnit unit;
	
	//Enum representing supported units 
	public enum LengthUnit{
		FEET(12.0), // 1 foot = 12 inches 
		INCHES(1.0); // Base Unit 
		
		public final double conversionFactor;
		
		LengthUnit(double conversionFactor){
			this.conversionFactor = conversionFactor;
		}
		
		public double getConversionFactor() {
			return conversionFactor;
		}
	}
	
	// constructor with validation
	public Length(double value , LengthUnit unit) {
		
		this.value = value;
		this.unit = unit;
	}
	
	// convert to base unit (Inches)
	public double convertToBaseUnit() {
		return value * unit.getConversionFactor();
	}
	
	// compare two length objects 
	public boolean compare(Length other) {
		if (other == null) {
            return false;
        }
        return Double.compare(
                this.convertToBaseUnit(),
                other.convertToBaseUnit()
        ) == 0;
	}
	
	@Override
    public boolean equals(Object obj) {

        // Same reference
        if (this == obj) return true;

        // Null or different type
        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return compare(other);
    }
	// main for standalone testing
		public static void main(String[] args) {
			Length length1 = new Length(1.0, LengthUnit.FEET);
			Length length2 = new Length(12.0, LengthUnit.INCHES);

			System.out.println("Are lengths equal? " + length1.equals(length2));
		}
}
