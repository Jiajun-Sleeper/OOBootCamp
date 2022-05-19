package org.oobootcamp.core.parkinglot;

import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotIsFullException;

import java.util.List;

public class SmartyParkingBoy extends ParkingBoy {

    public SmartyParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return super.parkingLots.stream()
                .max(new ParkingLot.FreeParkingSpaceComparator())
                .orElseThrow(ParkingLotIsFullException::new)
                .park(car);
    }
}
