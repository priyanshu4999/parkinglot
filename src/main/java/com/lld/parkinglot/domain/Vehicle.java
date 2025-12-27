package com.lld.parkinglot.domain;

public class Vehicle {
    private final String id;
    private final VehicleType type;

    public Vehicle(String id, VehicleType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }
}
