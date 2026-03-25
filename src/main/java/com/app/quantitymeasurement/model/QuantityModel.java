package com.app.quantitymeasurement.model;

import com.app.quantitymeasurement.core.IMeasurable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuantityModel<U extends IMeasurable> {

	private double value;
	private U unit;

}
