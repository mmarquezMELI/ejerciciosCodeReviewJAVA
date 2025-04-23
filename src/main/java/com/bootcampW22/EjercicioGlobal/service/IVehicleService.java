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
}
