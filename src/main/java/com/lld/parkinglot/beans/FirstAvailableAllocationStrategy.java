package com.lld.parkinglot.beans;

import com.lld.parkinglot.domain.*;
import com.lld.parkinglot.interfaces.AllocationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FirstAvailableAllocationStrategy implements AllocationStrategy {

    @Override
    public Optional<Spot> allocateSpot(Vehicle vehicle, List<Spot> spots) {
        return spots.stream()
                .filter(spot -> !spot.isOccupied())
                .findFirst();
    }
}
