package com.app.controller;

import com.app.dto.QuantityDTO;
import com.app.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performAddition() {

        QuantityDTO q1 = new QuantityDTO(1.0, "FEET");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES");

        QuantityDTO result = service.add(q1, q2);

        System.out.println("Addition Result: " + result);
    }

}
