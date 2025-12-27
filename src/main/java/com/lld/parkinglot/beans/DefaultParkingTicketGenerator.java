package com.lld.parkinglot.beans;

import com.lld.parkinglot.domain.*;
import com.lld.parkinglot.interfaces.ParkingTicketGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DefaultParkingTicketGenerator implements ParkingTicketGenerator {

    @Override
    public Ticket assignTicket(Spot spot, Vehicle vehicle) {
        spot.occupy();
        return new Ticket(
                UUID.randomUUID().toString(),
                vehicle,
                spot,
                LocalDateTime.now()
        );
    }
}
