package com.bootcampW22.EjercicioGlobal.controller;

import com.bootcampW22.EjercicioGlobal.dto.ResponseDto;
import com.bootcampW22.EjercicioGlobal.dto.VehicleDto;
import com.bootcampW22.EjercicioGlobal.service.IVehicleService;
import com.bootcampW22.EjercicioGlobal.service.VehicleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    IVehicleService vehicleService;


    public VehicleController(VehicleServiceImpl vehicleService){
        this.vehicleService = vehicleService;
    }

    //0
    @GetMapping("/vehicles")
    public ResponseEntity<?> getVehicles(){
        return new ResponseEntity<>(vehicleService.searchAllVehicles(), HttpStatus.OK);
    }

    //1
    @PostMapping("/vehicles")
    public ResponseEntity<ResponseDto> addVehicle(@RequestBody VehicleDto vehicleDto){
        return new ResponseEntity<>(vehicleService.addVehicle(vehicleDto),HttpStatus.CREATED);
    }

    //2
    @GetMapping("/vehicles/color/{color}/year/{year}")
    public ResponseEntity<List<VehicleDto>> seachByColorAndYear(@PathVariable("color")String color,
                                                                @PathVariable("year") Integer year){
        return new ResponseEntity<>(vehicleService.searchByColorAndYear(color,year),HttpStatus.OK);

    }

    //3
    @GetMapping("/vehicles/brand/{brand}/between/{start_year}/{end_year}")
    public ResponseEntity<List<VehicleDto>> searchByBrandAndRangeYear(@PathVariable("brand") String brand,
                                                            @PathVariable("start_year") Integer start_year,
                                                                @PathVariable("end_year") Integer end_year){
        return new ResponseEntity<>(vehicleService.searchByBrandAndRangeYear(brand,start_year,end_year),HttpStatus.OK);
    }

    //4
    @GetMapping("/vehicles/average_speed/brand/{brand}")
    public ResponseEntity<Double> averageSpeedByBrand(@PathVariable String brand){
        return new ResponseEntity<>(vehicleService.averageSpeedByBrand(brand),HttpStatus.OK);
    }

    //5
    @PostMapping("/vehicles/batch")
    public ResponseEntity<ResponseDto> addMassiveVehicles(@RequestBody List<VehicleDto> listVehicleDto){
        return new ResponseEntity<>(vehicleService.addMassiveVehicle(listVehicleDto),HttpStatus.CREATED);
    }
    //6
    @PutMapping("/vehicles/{id}/update_speed")
    public ResponseEntity<ResponseDto> updateSpeed(@PathVariable("id") Long id,@RequestBody String speed){
        return new ResponseEntity<>(vehicleService.updateSpeed(id,speed),HttpStatus.OK);
    }
    //7
    @GetMapping("/vehicles/fuel_type/{type}")
    public ResponseEntity<List<VehicleDto>> findByFuelType(@PathVariable("type") String type){
        return new ResponseEntity<>(vehicleService.findFuelByType(type),HttpStatus.OK);
    }
    //8
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<ResponseDto> deleteVehicle(@PathVariable("id") Long id){
        return new ResponseEntity<>(vehicleService.deleteVehicle(id),HttpStatus.NO_CONTENT);
    }

    //9
    @GetMapping("/vehicles/transmission/{type}")
    public ResponseEntity<List<VehicleDto>> searchByTransmission(@PathVariable("type")String type){
        return new ResponseEntity<>(vehicleService.searchByTransmission(type),HttpStatus.OK);
    }

    //10
    @PutMapping("/vehicles/{id}/update_fuel")
    public ResponseEntity<ResponseDto> updateFuel(@PathVariable("id")Long id,@RequestBody String fuel){
        return new ResponseEntity<>(vehicleService.updateFuel(id,fuel),HttpStatus.OK);
    }

    //11
    @GetMapping("/vehicles/average_capacity/brand/{brand}")
    public ResponseEntity<Double> averageCapacityByBrand(@PathVariable("brand")String brand){
        return new ResponseEntity<>(vehicleService.averageCapacityByBrand(brand),HttpStatus.OK);
    }

    @GetMapping("/vehicles/dimensions")
    public ResponseEntity<List<VehicleDto>> searchByDimensions(@RequestParam("length")String length,
                                                               @RequestParam("width") String width){
        return new ResponseEntity<>(vehicleService.searchByDimensions(length,width),HttpStatus.OK);

    }

    @GetMapping("/vehicles/weight")
    public ResponseEntity<List<VehicleDto>> searchByWeighRange(@RequestParam("min")Double min,
                                                               @RequestParam("max")Double max){
        return new ResponseEntity<>(vehicleService.searchByWeighRange(min,max),HttpStatus.OK);
    }
}
