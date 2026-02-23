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
}
