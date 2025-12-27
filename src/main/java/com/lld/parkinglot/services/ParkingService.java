package com.lld.parkinglot.services;



import com.lld.parkinglot.domain.*;
import com.lld.parkinglot.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ParkingService {

    private final AllocationStrategy allocationStrategy;
    private final ParkingTicketGenerator ticketGenerator;
    private final NotificationService notificationService;
    private final ParkingLot parkingLot;

    public ParkingService(
            AllocationStrategy allocationStrategy,
            ParkingTicketGenerator ticketGenerator,
            NotificationService notificationService,
            ParkingLot parkingLot
    ) {
        this.allocationStrategy = allocationStrategy;
        this.ticketGenerator = ticketGenerator;
        this.notificationService = notificationService;
        this.parkingLot = parkingLot;
    }

    public Ticket park(Vehicle vehicle) {
        SpotType requiredType = vehicleSpotType(vehicle);

        for (Floor floor : parkingLot.getFloors()) {
            Map<SpotType, List<Spot>> emptySpots = floor.emptySpots();
            List<Spot> candidates = emptySpots.get(requiredType);

            if (candidates == null || candidates.isEmpty()) {
                continue;
            }

            Optional<Spot> allocated =
                    allocationStrategy.allocateSpot(vehicle, candidates);

            if (allocated.isPresent()) {
                Ticket ticket = ticketGenerator.assignTicket(
                        allocated.get(),
                        vehicle
                );

                notificationService.push(
                        new NotificationPayload(floor.emptySpots())
                );
                return ticket;
            }
        }
        throw new RuntimeException("No parking spot available");
    }


    public void unpark(Ticket ticket) {
        Spot spot = ticket.getSpot();
        spot.release();
        notificationService.push(
                new NotificationPayload(findFloor(spot).emptySpots())
        );
    }

    private SpotType vehicleSpotType(Vehicle vehicle) {
        return switch (vehicle.getType()) {
            case BIKE -> SpotType.SMALL;
            case CAR -> SpotType.MEDIUM;
            case BUS -> SpotType.LARGE;
        };
    }

    private Floor findFloor(Spot spot) {
        return parkingLot.getFloors()
                .stream()
                .filter(f -> f.emptySpots().get(spot.getType()).contains(spot))
                .findFirst()
                .orElseThrow();
    }
}
