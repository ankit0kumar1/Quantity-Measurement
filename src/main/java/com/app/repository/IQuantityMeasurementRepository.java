package com.app.repository;

import java.util.List;
import com.app.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {

    void saveMeasurement(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> findAll();

}
