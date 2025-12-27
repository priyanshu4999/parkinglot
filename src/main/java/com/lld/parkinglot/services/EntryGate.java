package com.lld.parkinglot.services;

import com.lld.parkinglot.domain.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntryGate {

    private final ParkingService parkingService;

    public EntryGate(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public Ticket enter(VehicleType type) {
        Vehicle vehicle = new Vehicle(
                UUID.randomUUID().toString(),
                type
        );
        return parkingService.park(vehicle);
    }
}
