package com.app.quantitymeasurement;

public class Length {
	private final double value;
	private final LengthUnit unit;
	
	//Enum representing supported units 
	public enum LengthUnit{
		FEET(12.0), // 1 foot = 12 inches 
		INCHES(1.0),// Base Unit 
		YARDS(36.0),
		CENTIMETERS(0.393701); 
		
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
		 if (unit == null)
	            throw new IllegalArgumentException("Unit cannot be null");
		 if (Double.isNaN(value) || Double.isInfinite(value))
	            throw new IllegalArgumentException("Invalid numeric value");
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
	// UC5 NEW FEATURE → Instance conversion
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = convertToBaseUnit();
        double convertedValue = baseValue / targetUnit.getConversionFactor();
        return new Length(convertedValue, targetUnit);
    }
    
    @Override
    public String toString() {
        return value + " " + unit;
    }
    //UC6 NEW FEATURE ->Addition of two length units 
    public Length add(Length other) {
        if (other == null)
            throw new IllegalArgumentException("Cannot add null Length");

        double thisBase = this.convertToBaseUnit();
        double otherBase = other.convertToBaseUnit();

        double sumBase = thisBase + otherBase;

        double resultValue = sumBase / this.unit.getConversionFactor();

        return new Length(resultValue, this.unit);
    }
    
	// main for standalone testing
		public static void main(String[] args) {
			Length length1 = new Length(1.0, LengthUnit.FEET);
			Length length2 = new Length(12.0, LengthUnit.INCHES);

			System.out.println("Are lengths equal? " + length1.equals(length2));
			
			Length length3 = new Length(1.0, LengthUnit.YARDS);
			Length length4 = new Length(36, LengthUnit.INCHES);
			
			System.out.println("Are lengths equal? " + length3.equals(length4));
			
			Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
			Length length6 = new Length(39.3701, LengthUnit.INCHES);
			
			System.out.println("Are lengths equal? " + length5.equals(length6));
		}
}
