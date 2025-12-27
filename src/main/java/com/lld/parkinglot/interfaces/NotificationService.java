package com.lld.parkinglot.interfaces;

import com.lld.parkinglot.domain.NotificationPayload;

public interface NotificationService {
    void push(NotificationPayload payload);
}
