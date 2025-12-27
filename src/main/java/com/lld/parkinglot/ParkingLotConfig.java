package com.lld.parkinglot.beans;

import com.lld.parkinglot.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ParkingLotConfig {

    @Bean
    public ParkingLot parkingLot() {

        Map<SpotType, List<Spot>> floor1Spots = new EnumMap<>(SpotType.class);

        floor1Spots.put(
                SpotType.SMALL,
                List.of(
                        new Spot("S1", SpotType.SMALL),
                        new Spot("S2", SpotType.SMALL)
                )
        );

        floor1Spots.put(
                SpotType.MEDIUM,
                List.of(
                        new Spot("M1", SpotType.MEDIUM),
                        new Spot("M2", SpotType.MEDIUM)
                )
        );

        floor1Spots.put(
                SpotType.LARGE,
                List.of(
                        new Spot("L1", SpotType.LARGE)
                )
        );

        Floor floor1 = new Floor("F1", floor1Spots);

        return new ParkingLot(
                "PL1",
                List.of(floor1)
        );
    }
}
