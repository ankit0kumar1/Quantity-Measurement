package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.core.IMeasurable;
import com.app.quantitymeasurement.core.LengthUnit;
import com.app.quantitymeasurement.core.Quantity;
import com.app.quantitymeasurement.core.TemperatureUnit;
import com.app.quantitymeasurement.core.VolumeUnit;
import com.app.quantitymeasurement.core.WeightUnit;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    // ===== Convert DTO -> Quantity object =====

    private Quantity<?> convertDtoToModel(QuantityDTO dto) {

        try {
            LengthUnit unit = LengthUnit.valueOf(dto.getUnit());
            return new Quantity<>(dto.getValue(), unit);
        } catch (Exception ignored) {}

        try {
            WeightUnit unit = WeightUnit.valueOf(dto.getUnit());
            return new Quantity<>(dto.getValue(), unit);
        } catch (Exception ignored) {}

        try {
            VolumeUnit unit = VolumeUnit.valueOf(dto.getUnit());
            return new Quantity<>(dto.getValue(), unit);
        } catch (Exception ignored) {}

        try {
            TemperatureUnit unit = TemperatureUnit.valueOf(dto.getUnit());
            return new Quantity<>(dto.getValue(), unit);
        } catch (Exception ignored) {}

        throw new QuantityMeasurementException("Unsupported Unit: " + dto.getUnit());
    }

    // ===== Get measurement type name =====

    private String getMeasurementType(Quantity<?> quantity) {
        IMeasurable unit = quantity.getUnit();
        if (unit instanceof LengthUnit)      return "LengthUnit";
        if (unit instanceof WeightUnit)      return "WeightUnit";
        if (unit instanceof VolumeUnit)      return "VolumeUnit";
        if (unit instanceof TemperatureUnit) return "TemperatureUnit";
        return "Unknown";
    }

    // ===== Build base DTO from input =====

    private QuantityMeasurementDTO buildBaseDTO(QuantityInputDTO input,
                                                 Quantity<?> q1,
                                                 Quantity<?> q2,
                                                 String operation) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
        dto.setThisValue(input.getThisQuantityDTO().getValue());
        dto.setThisUnit(input.getThisQuantityDTO().getUnit());
        dto.setThisMeasurementType(getMeasurementType(q1));
        dto.setThatValue(input.getThatQuantityDTO().getValue());
        dto.setThatUnit(input.getThatQuantityDTO().getUnit());
        dto.setThatMeasurementType(getMeasurementType(q2));
        dto.setOperation(operation);
        return dto;
    }

    // ===== Save to repository =====

    private void saveToRepository(QuantityMeasurementDTO dto) {
        repository.save(dto.toEntity());
    }

    // ================= COMPARE =================

    @Override
    public QuantityMeasurementDTO compare(QuantityInputDTO input) {

        Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
        Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());

        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "compare");

        try {
            boolean result = q1.equals(q2);
            dto.setResultString(String.valueOf(result));
            dto.setError(false);
        } catch (Exception e) {
            dto.setErrorMessage(e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }

    // ================= CONVERT =================

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO convert(QuantityInputDTO input) {

        Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
        Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());

        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "convert");

        try {

            IMeasurable unit = q1.getUnit();

            if (unit instanceof LengthUnit) {
                LengthUnit target = LengthUnit.valueOf(input.getThatQuantityDTO().getUnit());
                Quantity<LengthUnit> result = ((Quantity<LengthUnit>) q1).convertTo(target);
                dto.setResultValue(result.getValue());
                dto.setResultMeasurementType("LengthUnit");

            } else if (unit instanceof WeightUnit) {
                WeightUnit target = WeightUnit.valueOf(input.getThatQuantityDTO().getUnit());
                Quantity<WeightUnit> result = ((Quantity<WeightUnit>) q1).convertTo(target);
                dto.setResultValue(result.getValue());
                dto.setResultMeasurementType("WeightUnit");

            } else if (unit instanceof VolumeUnit) {
                VolumeUnit target = VolumeUnit.valueOf(input.getThatQuantityDTO().getUnit());
                Quantity<VolumeUnit> result = ((Quantity<VolumeUnit>) q1).convertTo(target);
                dto.setResultValue(result.getValue());
                dto.setResultMeasurementType("VolumeUnit");

            } else if (unit instanceof TemperatureUnit) {
                TemperatureUnit target = TemperatureUnit.valueOf(input.getThatQuantityDTO().getUnit());
                Quantity<TemperatureUnit> result = ((Quantity<TemperatureUnit>) q1).convertTo(target);
                dto.setResultValue(result.getValue());
                dto.setResultMeasurementType("TemperatureUnit");
            }

            dto.setError(false);

        } catch (Exception e) {
            dto.setErrorMessage(e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }

 // ===== Helper to convert a result to a specific target unit string =====

    private Quantity<?> convertToTargetUnit(Quantity<?> baseResult, String targetUnitName) {
        if (targetUnitName == null || targetUnitName.isEmpty()) {
            return baseResult;
        }

        try {
            IMeasurable unit = baseResult.getUnit();
            if (unit instanceof LengthUnit) {
                return ((Quantity<LengthUnit>) baseResult).convertTo(LengthUnit.valueOf(targetUnitName));
            } else if (unit instanceof WeightUnit) {
                return ((Quantity<WeightUnit>) baseResult).convertTo(WeightUnit.valueOf(targetUnitName));
            } else if (unit instanceof VolumeUnit) {
                return ((Quantity<VolumeUnit>) baseResult).convertTo(VolumeUnit.valueOf(targetUnitName));
            } else if (unit instanceof TemperatureUnit) {
                return ((Quantity<TemperatureUnit>) baseResult).convertTo(TemperatureUnit.valueOf(targetUnitName));
            }
        } catch (Exception e) {
            // If conversion fails (e.g., target unit not found), return original result
            return baseResult;
        }
        return baseResult;
    }

    // ================= UPDATED ADD =================

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO add(QuantityInputDTO input) {
        Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
        Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());
        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "add");

        try {
            if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
                throw new QuantityMeasurementException("Cannot perform arithmetic between different categories.");
            }

            // Perform addition
            Quantity baseResult = ((Quantity) q1).add((Quantity) q2);
            
            // Convert to target unit sent from React
            Quantity finalResult = (Quantity) convertToTargetUnit(baseResult, input.getTargetUnit());

            dto.setResultValue(finalResult.getValue());
            dto.setResultUnit(finalResult.getUnit().getUnitName());
            dto.setResultMeasurementType(getMeasurementType(q1));
            dto.setError(false);
        } catch (Exception e) {
            dto.setErrorMessage("add Error: " + e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }

    // ================= UPDATED SUBTRACT =================

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO subtract(QuantityInputDTO input) {
        Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
        Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());
        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "subtract");

        try {
            if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
                throw new QuantityMeasurementException("Cannot perform arithmetic between different categories.");
            }

            Quantity baseResult = ((Quantity) q1).subtract((Quantity) q2);
            
            // Convert to target unit
            Quantity finalResult = (Quantity) convertToTargetUnit(baseResult, input.getTargetUnit());

            dto.setResultValue(finalResult.getValue());
            dto.setResultUnit(finalResult.getUnit().getUnitName());
            dto.setResultMeasurementType(getMeasurementType(q1));
            dto.setError(false);
        } catch (Exception e) {
            dto.setErrorMessage("subtract Error: " + e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }
 // ================= UPDATED DIVIDE =================

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO divide(QuantityInputDTO input) {

        Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
        Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());

        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "divide");

        try {

            if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
                throw new QuantityMeasurementException(
                    "Cannot perform arithmetic between different measurement categories: "
                    + getMeasurementType(q1) + " and " + getMeasurementType(q2)
                );
            }

            // Division calculation
            double result = ((Quantity) q1).divide((Quantity) q2);

            dto.setResultValue(result);
            dto.setResultMeasurementType(getMeasurementType(q1));
            dto.setResultUnit(""); // Division of like units results in a ratio
            dto.setError(false);

        } catch (QuantityMeasurementException e) {
            dto.setErrorMessage("Divide by zero");
            dto.setError(true);
        } catch (ArithmeticException e) {
            dto.setErrorMessage("Divide by zero");
            dto.setError(true);
        } catch (Exception e) {
            dto.setErrorMessage("divide Error: " + e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }

    // ================= UPDATED MULTIPLY =================

    @Override
    @SuppressWarnings("unchecked")
    public QuantityMeasurementDTO multiply(QuantityInputDTO input) {

        Quantity<?> q1 = convertDtoToModel(input.getThisQuantityDTO());
        Quantity<?> q2 = convertDtoToModel(input.getThatQuantityDTO());

        QuantityMeasurementDTO dto = buildBaseDTO(input, q1, q2, "multiply");

        try {

            if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
                throw new QuantityMeasurementException(
                    "Cannot perform arithmetic between different measurement categories: "
                    + getMeasurementType(q1) + " and " + getMeasurementType(q2)
                );
            }

            // Perform multiplication
            double rawProduct = ((Quantity) q1).multiply((Quantity) q2);
            
            // Convert the product to the target unit
            Quantity baseProductQuantity = new Quantity(rawProduct, q1.getUnit());
            Quantity finalResult = (Quantity) convertToTargetUnit(baseProductQuantity, input.getTargetUnit());

            dto.setResultValue(finalResult.getValue());
            dto.setResultUnit(finalResult.getUnit().getUnitName());
            dto.setResultMeasurementType(getMeasurementType(q1));
            dto.setError(false);

        } catch (Exception e) {
            dto.setErrorMessage("multiply Error: " + e.getMessage());
            dto.setError(true);
        }

        saveToRepository(dto);
        return dto;
    }

    // ================= HISTORY & REPORTING =================

    @Override
    public List<QuantityMeasurementDTO> getHistoryByOperation(String operation) {
        List<QuantityMeasurementEntity> entities =
                repository.findByOperation(operation.toLowerCase());
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByMeasurementType(String measurementType) {
        List<QuantityMeasurementEntity> entities =
                repository.findByThisMeasurementType(measurementType);
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation.toLowerCase());
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        List<QuantityMeasurementEntity> entities = repository.findByIsErrorTrue();
        return QuantityMeasurementDTO.fromEntityList(entities);
    }
    
    

}