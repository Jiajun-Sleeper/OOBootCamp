package org.oobootcamp.core.parkinglot;

import org.oobootcamp.core.parkinglot.Exceptions.InvalidTicketException;
import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotUnavailableException;

import java.util.List;

public class SmartyParkingBoy extends ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SmartyParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket park(Car car) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(new ParkingLot.FreeParkingSpaceComparator())
                .orElseThrow(ParkingLotUnavailableException::new)
                .park(car);
    }

    @Override
    public Car pick(Ticket ticket) {
        return parkingLots.stream().filter(parkingLot -> parkingLot.containCar(ticket)).findFirst().orElseThrow(InvalidTicketException::new).pick(ticket);
    }

}
