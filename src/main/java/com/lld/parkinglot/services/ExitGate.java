package com.lld.parkinglot.services;


import com.lld.parkinglot.domain.Ticket;
import com.lld.parkinglot.interfaces.ParkingFeeCollector;
import org.springframework.stereotype.Component;

@Component
public class ExitGate {

    private final ParkingService parkingService;
    private final ParkingFeeCollector feeCollector;

    public ExitGate(ParkingService parkingService,
                    ParkingFeeCollector feeCollector) {
        this.parkingService = parkingService;
        this.feeCollector = feeCollector;
    }

    public double exit(Ticket ticket) {
        double fee = feeCollector.collect(ticket);
        parkingService.unpark(ticket);
        return fee;
    }
}
