package com.lld.parkinglot.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Floor {
    private final String id;
    private final Map<SpotType, List<Spot>> spots;

    public Floor(String id, Map<SpotType, List<Spot>> spots) {
        this.id = id;
        this.spots = spots;
    }

    public String getId() {
        return id;
    }

    public Map<SpotType, List<Spot>> getSpots() {
        return spots;
    }

    public Map<SpotType, List<Spot>> emptySpots() {
        return spots.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .filter(spot -> !spot.isOccupied())
                                .toList(),
                        (a, b) -> a,
                        () -> new EnumMap<>(SpotType.class)
                ));
    }
}
