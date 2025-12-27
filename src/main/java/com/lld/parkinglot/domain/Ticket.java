package com.lld.parkinglot.domain;

import java.time.LocalDateTime;

public class Ticket {
    private final String id;
    private final Vehicle vehicle;
    private final Spot spot;
    private final LocalDateTime entryTime;

    public Ticket(String id, Vehicle vehicle, Spot spot, LocalDateTime entryTime) {
        this.id = id;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = entryTime;
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Spot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
