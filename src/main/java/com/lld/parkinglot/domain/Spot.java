package com.lld.parkinglot.domain;

public class Spot {
    private final String id;
    private final SpotType type;
    private boolean occupied;

    public Spot(String id, SpotType type) {
        this.id = id;
        this.type = type;
        this.occupied = false;
    }

    public String getId() {
        return id;
    }

    public SpotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        this.occupied = true;
    }

    public void release() {
        this.occupied = false;
    }
}
