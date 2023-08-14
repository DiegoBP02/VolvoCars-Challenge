package com.example.demo.controllers;

import com.example.demo.dtos.TaxRequest;
import com.example.demo.entities.Car;
import com.example.demo.entities.Motorbike;
import com.example.demo.entities.Vehicle;
import com.example.demo.services.CongestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    private CongestionService congestionService;

    @PostMapping
    public ResponseEntity<Integer> calculateTax(@RequestBody(required = true) TaxRequest taxRequest) {
        String vehicleType = taxRequest.getVehicleType();
        LocalDateTime[] dates = taxRequest.getDates();

        Vehicle vehicle;
        if (vehicleType.equalsIgnoreCase("car")) {
            vehicle = new Car();
        } else if (vehicleType.equalsIgnoreCase("motorbike")) {
            vehicle = new Motorbike();
        } else {
            return ResponseEntity.badRequest().build();
        }

        int result = congestionService.calculateTax(vehicle, dates);

        return ResponseEntity.ok(result);
    }

}
