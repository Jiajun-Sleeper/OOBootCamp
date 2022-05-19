package org.oobootcamp.core.parkinglot;

import org.oobootcamp.core.parkinglot.Exceptions.NoParkingPlantException;
import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotIsFullException;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {
    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        if (parkingLots.size() == 0) {
            throw new NoParkingPlantException();
        }
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(ParkingLotIsFullException::new)
                .park(car);
    }

}
