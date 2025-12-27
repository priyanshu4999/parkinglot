package com.lld.parkinglot.domain;

import java.util.List;
import java.util.Map;

public class NotificationPayload {
    private final Map<SpotType, List<Spot>> data;

    public NotificationPayload(Map<SpotType, List<Spot>> data) {
        this.data = data;
    }

    public Map<SpotType, List<Spot>> getData() {
        return data;
    }
}
