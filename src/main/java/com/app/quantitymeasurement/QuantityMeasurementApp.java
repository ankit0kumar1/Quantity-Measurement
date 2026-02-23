package com.app.quantitymeasurement;

public class QuantityMeasurementApp {
	
	public static class Feet{
		private final double value;
		public Feet(double value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			
			//1 Same Object 
			if(this == obj) return true;
			
			//2.Null Check
			if(obj == null) return false;
			
			//3.Type Check
			if(getClass() != obj.getClass()) return false;
			
			//4. Cast Safely
			Feet other = (Feet) obj;
			
			//5. Compare double values safely
			return Double.compare(this.value, other.value)== 0;
			
		}
		
	}
	
	// Inner class to represent Inches
	public static class Inches{
		private final double value;
		public Inches(double value) {
			this.value = value;
		}
		@Override 
		public boolean equals(Object obj) {
			// 1 same object 
			if (this == obj)
				return true;
			// null check and type check
			if (obj == null || getClass() != obj.getClass())
				return false;
			//cast safely
			Inches inches = (Inches) obj;
			// compare double values safely
			return Double.compare(this.value, inches.value) == 0;
		}
	}
	public static void demonstrateFeetEquality() {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(1.0);
		System.out.println("Feet Equal: " + f1.equals(f2));
	}

	public static void demonstrateInchesEquality() {
		Inches i1 = new Inches(1.0);
		Inches i2 = new Inches(1.0);
		System.out.println("Inches Equal: " + i1.equals(i2));
	}

	public static void main(String[] args) {
		demonstrateFeetEquality();
		demonstrateInchesEquality();
	}
	
}
