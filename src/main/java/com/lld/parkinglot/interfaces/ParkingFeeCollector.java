package com.lld.parkinglot.interfaces;

import com.lld.parkinglot.domain.Ticket;

public interface ParkingFeeCollector {
    double collect(Ticket ticket);
}
