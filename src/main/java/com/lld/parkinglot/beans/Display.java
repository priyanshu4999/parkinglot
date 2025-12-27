package com.lld.parkinglot.beans;


import com.lld.parkinglot.domain.NotificationPayload;
import com.lld.parkinglot.interfaces.Subscriber;
import org.springframework.stereotype.Component;

@Component
public class Display implements Subscriber {

    @Override
    public void onNotification(NotificationPayload payload) {
        // render / log payload
    }
}
