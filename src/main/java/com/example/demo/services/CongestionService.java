package com.example.demo.services;

import com.example.demo.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class CongestionService {

    @Autowired
    private CongestionTaxCalculator congestionTaxCalculator;

    public int calculateTax(Vehicle vehicle, LocalDateTime[]dates) {
        return congestionTaxCalculator.getTax(vehicle,dates);
    }
}
