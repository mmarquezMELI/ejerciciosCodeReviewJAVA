package com.bootcampW22.EjercicioGlobal.service;

import com.bootcampW22.EjercicioGlobal.dto.ResponseDto;
import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;

import java.util.List;

public interface IVehicleService {
    List<VehicleDto> searchAllVehicles();

    ResponseDto addVehicle(VehicleDto vehicleDto);

    List<VehicleDto> searchByColorAndYear(String color, Integer year);

        List<VehicleDto> searchByBrandAndRangeYear(String brand, Integer start_year, Integer end_year);

    Double averageSpeedByBrand(String brand);

    ResponseDto updateSpeed(Long id, String speed);

    ResponseDto addMassiveVehicle(List<VehicleDto> listVehicleDto);

    List<VehicleDto> findFuelByType(String type);

    ResponseDto deleteVehicle(Long id);

    List<VehicleDto> searchByTransmission(String type);

    ResponseDto updateFuel(Long id, String fuel);

    Double averageCapacityByBrand(String brand);

    List<VehicleDto> searchByDimensions(String length, String width);

    List<VehicleDto> searchByWeighRange(Double min, Double max);
}
