package com.lld.parkinglot.beans;


import com.lld.parkinglot.domain.NotificationPayload;
import com.lld.parkinglot.interfaces.NotificationService;
import com.lld.parkinglot.interfaces.Subscriber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationPublisher implements NotificationService {

    private final List<Subscriber> subscribers = new ArrayList<>();

    public void registerSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void push(NotificationPayload payload) {
        for (Subscriber subscriber : subscribers) {
            subscriber.onNotification(payload);
        }
    }
}
