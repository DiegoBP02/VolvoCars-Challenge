package com.example.demo.services;

import com.example.demo.ApplicationConfigTest;
import com.example.demo.entities.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CongestionTaxCalculatorTest extends ApplicationConfigTest {

    @Autowired
    private CongestionTaxCalculator congestionTaxCalculator;

    Car car = mock(Car.class);
    LocalDateTime[] dates = {
            LocalDateTime.parse("2013-02-07T06:23:27"),
            LocalDateTime.parse("2013-02-07T15:27:00")
    };
    LocalDateTime[] allDates = {
            LocalDateTime.parse("2013-01-14T21:00:00"),
            LocalDateTime.parse("2013-01-15T21:00:00"),
            LocalDateTime.parse("2013-02-07T06:23:27"),
            LocalDateTime.parse("2013-02-07T15:27:00"),
            LocalDateTime.parse("2013-02-08T06:27:00"),
            LocalDateTime.parse("2013-02-08T06:20:27"),
            LocalDateTime.parse("2013-02-08T14:35:00"),
            LocalDateTime.parse("2013-02-08T15:29:00"),
            LocalDateTime.parse("2013-02-08T15:47:00"),
            LocalDateTime.parse("2013-02-08T16:01:00"),
            LocalDateTime.parse("2013-02-08T16:48:00"),
            LocalDateTime.parse("2013-02-08T17:49:00"),
            LocalDateTime.parse("2013-02-08T18:29:00"),
            LocalDateTime.parse("2013-02-08T18:35:00"),
            LocalDateTime.parse("2013-03-26T14:25:00"),
            LocalDateTime.parse("2013-03-28T14:07:27"),
    };

    @Test
    void givenVehicleAndDates_whenGetTax_thenReturnTax() {
        LocalDateTime[] date = {LocalDateTime.parse("2013-02-07T06:23:27")};
        int result = congestionTaxCalculator.getTax(car, date);
        assertEquals(8, result);
    }

    @Test
    void givenIsTollFreeVehicle_whenGetTax_thenReturnZero() {
        when(car.getVehicleType()).thenReturn("Foreign");
        int result = congestionTaxCalculator.getTax(car, dates);
        assertEquals(0, result);
    }

    @Test
    void givenVehicleAndDates_whenGetTax_thenLimitTaxTo60() {
        int result = congestionTaxCalculator.getTax(car, allDates);
        assertEquals(60, result);
    }

    @Test
    void givenVehicleAndDatesAreSmallerThan60Minutes_whenGetTax_thenReturnBiggerTax() {
        LocalDateTime[] datesWithin60Minutes = {
                LocalDateTime.parse("2013-02-08T15:29:00"),
                LocalDateTime.parse("2013-02-08T15:47:00"),
        };
        int result = congestionTaxCalculator.getTax(car, datesWithin60Minutes);
        assertEquals(18, result);
    }

    @Test
    void givenVehicleAndDatesAreBiggerThan60Minutes_whenGetTax_thenSumTheFees() {
        int result = congestionTaxCalculator.getTax(car, dates);
        assertEquals(21, result);
    }

}