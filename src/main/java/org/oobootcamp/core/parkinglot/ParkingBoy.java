package org.oobootcamp.core.parkinglot;

import org.oobootcamp.core.parkinglot.Exceptions.InvalidTicketException;

import java.util.List;

public abstract class ParkingBoy {
    protected final List<ParkingLot> parkingLots;

    protected ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public abstract Ticket park(Car car);

    public Car pick(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.containCar(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pick(ticket);
    }
}
