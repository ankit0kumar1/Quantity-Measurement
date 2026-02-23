package com.app.quantitymeasurement;

import com.app.quantitymeasurement.QuantityMeasurementApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {
	
	 // Feet = Feet (same value)
    @Test
    public void testEquality_FeetToFeet_SameValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    // Inches = Inches (same value)
    @Test
    public void testEquality_InchToInch_SameValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(1.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    // 1 foot = 12 inches
    @Test
    public void testEquality_FeetToInch_EquivalentValue() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }

    // symmetry check
    @Test
    public void testEquality_InchToFeet_EquivalentValue() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(inches.equals(feet));
    }

    // different feet values
    @Test
    public void testEquality_FeetToFeet_DifferentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    // different inches values
    @Test
    public void testEquality_InchToInch_DifferentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(2.0, Length.LengthUnit.INCHES);
        assertFalse(l1.equals(l2));
    }
    
    // null comparison
    @Test
    public void testEquality_NullComparison() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(l1.equals(null));
    }

    // reflexive property
    @Test
    public void testEquality_SameReference() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l1));
    }
 // UC4 test cases
 	@Test
 	public void testEquality_YardToYard_SameValue() {
 		Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
 		Length l2 = new Length(1.0, Length.LengthUnit.YARDS);
 		assertTrue(l1.equals(l2));
 	}

 	@Test
 	public void testEquality_YardToFeet_EquivalentValue() {
 		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
 		Length feet = new Length(3.0, Length.LengthUnit.FEET);
 		assertTrue(yard.equals(feet));
 	}

 	@Test
 	public void testEquality_FeetToYard_EquivalentValue() {
 		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
 		Length feet = new Length(3.0, Length.LengthUnit.FEET);
 		assertTrue(feet.equals(yard));
 	}

 	@Test
 	public void testEquality_YardToInches_EquivalentValue() {
 		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
 		Length inches = new Length(36.0, Length.LengthUnit.INCHES);
 		assertTrue(yard.equals(inches));
 	}

 	@Test
 	public void testEquality_InchesToYard_EquivalentValue() {
 		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
 		Length inches = new Length(36.0, Length.LengthUnit.INCHES);
 		assertTrue(inches.equals(yard));
 	}

 	@Test
 	public void testEquality_CentimeterToInches_EquivalentValue() {
 		Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
 		Length inch = new Length(0.393701, Length.LengthUnit.INCHES);
 		assertTrue(cm.equals(inch));
 	}

 	@Test
 	public void testEquality_CentimeterToFeet_NonEquivalentValue() {
 		Length cm = new Length(1.0, Length.LengthUnit.CENTIMETERS);
 		Length feet = new Length(1.0, Length.LengthUnit.FEET);
 		assertFalse(cm.equals(feet));
 	}

 	@Test
 	public void testEquality_TransitiveProperty() {
 		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
 		Length feet = new Length(3.0, Length.LengthUnit.FEET);
 		Length inches = new Length(36.0, Length.LengthUnit.INCHES);

 		assertTrue(yard.equals(feet));
 		assertTrue(feet.equals(inches));
 		assertTrue(yard.equals(inches));
 	}
 	
 	// UC5
 	@Test
	public void testConversion_FeetToInches() {
		assertEquals(12.0, QuantityMeasurementApp.convert(1, Length.LengthUnit.FEET, Length.LengthUnit.INCHES), 0.0001);
	}

	@Test
	public void testConversion_InchesToFeet() {
		assertEquals(2.0, QuantityMeasurementApp.convert(24, Length.LengthUnit.INCHES, Length.LengthUnit.FEET), 0.0001);
	}

	@Test
	public void testConversion_YardsToInches() {
		assertEquals(36.0, QuantityMeasurementApp.convert(1, Length.LengthUnit.YARDS, Length.LengthUnit.INCHES),
				0.0001);
	}

	@Test
	public void testConversion_InchesToYards() {
		assertEquals(2.0, QuantityMeasurementApp.convert(72, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS),
				0.0001);
	}

	@Test
	public void testConversion_CentimetersToInches() {
		assertEquals(1.0, QuantityMeasurementApp.convert(2.54, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES),
				0.01);
	}

	@Test
	public void testRoundTripConversion() {
		double value = 5;
		double result = QuantityMeasurementApp.convert(
				QuantityMeasurementApp.convert(value, Length.LengthUnit.FEET, Length.LengthUnit.INCHES),
				Length.LengthUnit.INCHES, Length.LengthUnit.FEET);

		assertEquals(value, result, 0.0001);
	}

	@Test
	public void testZeroValueConversion() {
		assertEquals(0.0, QuantityMeasurementApp.convert(0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES), 0.0001);
	}

	@Test
	public void testNegativeValueConversion() {
		assertEquals(-12.0, QuantityMeasurementApp.convert(-1, Length.LengthUnit.FEET, Length.LengthUnit.INCHES),
				0.0001);
	}

	@Test
	public void testInvalidUnitThrows() {
		assertThrows(IllegalArgumentException.class,
				() -> QuantityMeasurementApp.convert(1, null, Length.LengthUnit.FEET));
	}

	@Test
	public void testNaNThrows() {
		assertThrows(IllegalArgumentException.class,
				() -> QuantityMeasurementApp.convert(Double.NaN, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));
	}
	
	// UC6 tests

		@Test
		public void addFeetAndInches() {
			Length l1 = new Length(1.0, Length.LengthUnit.FEET);
			Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

			Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);
			Length expected = new Length(2.0, Length.LengthUnit.FEET);

			assertTrue(result.equals(expected));
		}

		@Test
		public void addSameUnitFeet() {
			Length l1 = new Length(1.0, Length.LengthUnit.FEET);
			Length l2 = new Length(2.0, Length.LengthUnit.FEET);

			Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);
			Length expected = new Length(3.0, Length.LengthUnit.FEET);

			assertTrue(result.equals(expected));
		}

		@Test
		public void addYardAndFeet() {
			Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
			Length l2 = new Length(3.0, Length.LengthUnit.FEET);

			Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);
			Length expected = new Length(2.0, Length.LengthUnit.YARDS);

			assertTrue(result.equals(expected));
		}

		@Test
		public void addWithZero() {
			Length l1 = new Length(5.0, Length.LengthUnit.FEET);
			Length l2 = new Length(0.0, Length.LengthUnit.INCHES);

			Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);
			Length expected = new Length(5.0, Length.LengthUnit.FEET);

			assertTrue(result.equals(expected));
		}

		@Test
		public void addNegativeValues() {
			Length l1 = new Length(5.0, Length.LengthUnit.FEET);
			Length l2 = new Length(-2.0, Length.LengthUnit.FEET);

			Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);
			Length expected = new Length(3.0, Length.LengthUnit.FEET);

			assertTrue(result.equals(expected));
		}
		
		// UC7
		@Test
	    public void testAddition_ExplicitTarget_Feet() {
	        Length l1 = new Length(1, Length.LengthUnit.FEET);
	        Length l2 = new Length(12, Length.LengthUnit.INCHES);
	        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, Length.LengthUnit.FEET);
	        assertTrue(result.equals(new Length(2, Length.LengthUnit.FEET)));
	    }

	    @Test
	    public void testAddition_ExplicitTarget_Inches() {
	        Length l1 = new Length(1, Length.LengthUnit.FEET);
	        Length l2 = new Length(12, Length.LengthUnit.INCHES);
	        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, Length.LengthUnit.INCHES);
	        assertTrue(result.equals(new Length(24, Length.LengthUnit.INCHES)));
	    }

	    @Test
	    public void testAddition_ExplicitTarget_Yards() {
	        Length l1 = new Length(1, Length.LengthUnit.FEET);
	        Length l2 = new Length(12, Length.LengthUnit.INCHES);
	        Length result = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, Length.LengthUnit.YARDS);
	        assertTrue(result.equals(new Length(0.6667, Length.LengthUnit.YARDS)));
	    }

	    @Test
	    public void testAddition_TargetUnitNullThrows() {
	        Length l1 = new Length(1, Length.LengthUnit.FEET);
	        Length l2 = new Length(12, Length.LengthUnit.INCHES);
	        assertThrows(IllegalArgumentException.class,
	                () -> QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, null));
	    }
    
}
