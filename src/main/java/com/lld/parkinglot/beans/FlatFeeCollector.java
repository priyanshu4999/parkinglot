package com.lld.parkinglot.beans;

import com.lld.parkinglot.domain.Ticket;
import com.lld.parkinglot.interfaces.ParkingFeeCollector;
import org.springframework.stereotype.Component;

@Component
public class FlatFeeCollector implements ParkingFeeCollector {

    private static final double FLAT_FEE = 50.0;

    @Override
    public double collect(Ticket ticket) {
        return FLAT_FEE;
    }
}
