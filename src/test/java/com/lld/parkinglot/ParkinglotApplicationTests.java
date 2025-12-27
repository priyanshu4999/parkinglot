package com.lld.parkinglot;



import com.lld.parkinglot.services.EntryGate;
import com.lld.parkinglot.services.ExitGate;
import com.lld.parkinglot.services.ParkingService;
import com.lld.parkinglot.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ParkingLotIntegrationTest {

    @Autowired
    private EntryGate entryGate;

    @Autowired
    private ExitGate exitGate;

    @Autowired
    private ParkingService parkingService;

    // -------------------- CONTEXT --------------------

    @Test
    @DisplayName("Spring context loads and core beans are wired")
    void contextLoads() {
        assertNotNull(entryGate);
        assertNotNull(exitGate);
        assertNotNull(parkingService);
    }

    // -------------------- ALLOCATION --------------------

    @Test
    @DisplayName("Bike gets SMALL spot")
    void bikeGetsSmallSpot() {
        Ticket ticket = entryGate.enter(VehicleType.BIKE);
        assertEquals(SpotType.SMALL, ticket.getSpot().getType());
    }

    @Test
    @DisplayName("Car gets MEDIUM spot")
    void carGetsMediumSpot() {
        Ticket ticket = entryGate.enter(VehicleType.CAR);
        assertEquals(SpotType.MEDIUM, ticket.getSpot().getType());
    }

    @Test
    @DisplayName("Bus gets LARGE spot")
    void busGetsLargeSpot() {
        Ticket ticket = entryGate.enter(VehicleType.BUS);
        assertEquals(SpotType.LARGE, ticket.getSpot().getType());
    }

    // -------------------- CHECK-IN --------------------

    @Test
    @DisplayName("Ticket records vehicle, spot, and entry time")
    void ticketHasRequiredData() {
        Ticket ticket = entryGate.enter(VehicleType.CAR);

        assertNotNull(ticket.getId());
        assertNotNull(ticket.getVehicle());
        assertNotNull(ticket.getSpot());
        assertNotNull(ticket.getEntryTime());
    }

    // -------------------- REAL-TIME AVAILABILITY --------------------

    @Test
    @DisplayName("Spot is occupied on entry and released on exit")
    void spotStateChangesCorrectly() {
        Ticket ticket = entryGate.enter(VehicleType.CAR);
        Spot spot = ticket.getSpot();

        assertTrue(spot.isOccupied());

        exitGate.exit(ticket);
        assertFalse(spot.isOccupied());
    }

    // -------------------- FEE CALCULATION --------------------

    @Test
    @DisplayName("Flat fee is charged on exit")
    void flatFeeChargedOnExit() {
        Ticket ticket = entryGate.enter(VehicleType.CAR);
        double fee = exitGate.exit(ticket);

        assertEquals(50.0, fee);
    }

    // -------------------- FAILURE CASE --------------------

    @Test
    @DisplayName("Exception thrown when no spot of required type is available")
    void throwsWhenNoSpotAvailable() {
        // assuming only one LARGE spot in config
        entryGate.enter(VehicleType.BUS);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> entryGate.enter(VehicleType.BUS)
        );

        assertTrue(ex.getMessage().contains("No parking spot"));
    }

    // -------------------- DOMAIN SANITY --------------------

    @Test
    @DisplayName("Floor emptySpots returns only unoccupied spots")
    void floorEmptySpotsWorks() {
        Spot s1 = new Spot("S1", SpotType.SMALL);
        Spot s2 = new Spot("S2", SpotType.SMALL);
        s2.occupy();

        Floor floor = new Floor(
                "F1",
                java.util.Map.of(SpotType.SMALL, java.util.List.of(s1, s2))
        );

        var empty = floor.emptySpots();

        assertEquals(1, empty.get(SpotType.SMALL).size());
        assertEquals("S1", empty.get(SpotType.SMALL).get(0).getId());
    }
}
