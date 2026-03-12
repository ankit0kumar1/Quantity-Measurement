package com.app.application;

import com.app.controller.QuantityMeasurementController;
import com.app.repository.QuantityMeasurementCacheRepository;
import com.app.service.IQuantityMeasurementService;
import com.app.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        var repository = QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        controller.performAddition();
    }

}
