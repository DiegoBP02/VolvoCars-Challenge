package com.example.demo.services;

import com.example.demo.entities.Vehicle;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

@Component
public class CongestionTaxCalculator {

    private static Map<String, Integer> tollFreeVehicles = new HashMap<>();

    static {
        tollFreeVehicles.put("Motorcycle", 0);
        tollFreeVehicles.put("Tractor", 1);
        tollFreeVehicles.put("Emergency", 2);
        tollFreeVehicles.put("Diplomat", 3);
        tollFreeVehicles.put("Foreign", 4);
        tollFreeVehicles.put("Military", 5);
    }

    public int getTax(Vehicle vehicle, LocalDateTime[] dates) {
        LocalDateTime intervalStart = dates[0];
        int totalFee = 0;

        for (int i = 0; i < dates.length; i++) {
            LocalDateTime date = dates[i];
            int nextFee = GetTollFee(date, vehicle);
            int tempFee = GetTollFee(intervalStart, vehicle);

            Duration duration = Duration.between(intervalStart, date);
            long minutes = duration.toMinutes();

            if (minutes <= 60) {
                if (totalFee > 0) totalFee -= tempFee;
                if (nextFee >= tempFee) tempFee = nextFee;
                totalFee += tempFee;
            } else {
                totalFee += nextFee;
            }
        }

        if (totalFee > 60) totalFee = 60;
        return totalFee;
    }

    private boolean IsTollFreeVehicle(Vehicle vehicle) {
        if (vehicle == null) return false;
        String vehicleType = vehicle.getVehicleType();
        return tollFreeVehicles.containsKey(vehicleType);
    }

    private int GetTollFee(LocalDateTime dateTime, Vehicle vehicle) {
        if (IsTollFreeDate(dateTime) || IsTollFreeVehicle(vehicle)) return 0;

        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();

        if (hour == 6 && minute <= 29) return 8;
        else if (hour == 6) return 13;
        else if (hour == 7) return 18;
        else if (hour == 8 && minute <= 29) return 13;
        else if (hour >= 8 && hour <= 14 && minute >= 30) return 8;
        else if (hour == 15 && minute <= 29) return 13;
        else if (hour == 15 || hour == 16) return 18;
        else if (hour == 17) return 13;
        else if (hour == 18 && minute <= 29) return 8;
        else return 0;
    }

    private boolean IsTollFreeDate(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        int dayOfMonth = dateTime.getDayOfMonth();
        Month month = dateTime.getMonth();
        int year = dateTime.getYear();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) return true;

        if (year == 2013) {
            if ((month == Month.JANUARY && dayOfMonth == 1) ||
                    (month == Month.MARCH && (dayOfMonth == 28 || dayOfMonth == 29)) ||
                    (month == Month.APRIL && (dayOfMonth == 1 || dayOfMonth == 30)) ||
                    (month == Month.MAY && (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 9)) ||
                    (month == Month.JUNE && (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 21)) ||
                    (month == Month.JULY) ||
                    (month == Month.NOVEMBER && dayOfMonth == 1) ||
                    (month == Month.DECEMBER && (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 31))) {
                return true;
            }
        }
        return false;
    }

}
