package com.lld.parkinglot.interfaces;

import com.lld.parkinglot.domain.Spot;
import com.lld.parkinglot.domain.Vehicle;

import java.util.List;
import java.util.Optional;

public interface AllocationStrategy {
    Optional<Spot> allocateSpot(Vehicle vehicle, List<Spot> spots);
}
