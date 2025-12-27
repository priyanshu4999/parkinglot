package com.lld.parkinglot.domain;

import java.util.*;

public class Floor {

    private final String id;
    private final Map<SpotType, List<Spot>> spotsByType;

    public Floor(String id, Map<SpotType, List<Spot>> spotsByType) {
        this.id = id;
        this.spotsByType = spotsByType;
    }

    public String getId() {
        return id;
    }

    public Map<SpotType, List<Spot>> emptySpots() {
        Map<SpotType, List<Spot>> result = new EnumMap<>(SpotType.class);

        for (var entry : spotsByType.entrySet()) {
            for (Spot spot : entry.getValue()) {
                if (!spot.isOccupied()) {
                    result
                            .computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                            .add(spot);
                }
            }
        }
        return result;
    }

    public boolean containsSpot(Spot spot) {
        return spotsByType.values().stream()
                .anyMatch(list -> list.contains(spot));
    }
}
