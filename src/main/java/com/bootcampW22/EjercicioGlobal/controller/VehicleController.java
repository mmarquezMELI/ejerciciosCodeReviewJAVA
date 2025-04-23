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
//@RequestMapping("/vehicles")
public class VehicleController {

    IVehicleService vehicleService;

    public VehicleController(VehicleServiceImpl vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<?> getVehicles(){
        return new ResponseEntity<>(vehicleService.searchAllVehicles(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addVehicle(@RequestBody VehicleDto vehicleDto){
        return new ResponseEntity<>(vehicleService.addVehicle(vehicleDto),HttpStatus.CREATED);
    }

    @GetMapping("/vehicles/color/{color}/year/{year}")
    public ResponseEntity<List<VehicleDto>> seachByColorAndYear(@PathVariable("color")String color,
                                                                @PathVariable("year") Integer year){
        return new ResponseEntity<>(vehicleService.searchByColorAndYear(color,year),HttpStatus.OK);

    }
}
