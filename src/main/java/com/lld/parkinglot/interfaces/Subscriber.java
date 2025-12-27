package com.lld.parkinglot.interfaces;

import com.lld.parkinglot.domain.NotificationPayload;

public interface Subscriber {
    void onNotification(NotificationPayload payload);
}
