package com.lld.parkinglot.interfaces;

import com.lld.parkinglot.domain.Spot;
import com.lld.parkinglot.domain.Ticket;
import com.lld.parkinglot.domain.Vehicle;

public interface ParkingTicketGenerator {
    Ticket assignTicket(Spot spot, Vehicle vehicle);
}
