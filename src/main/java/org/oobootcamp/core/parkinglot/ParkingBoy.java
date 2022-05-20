package org.oobootcamp.core.parkinglot;

import org.oobootcamp.core.parkinglot.Exceptions.InvalidTicketException;
import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotIsFullException;

import java.util.Comparator;
import java.util.List;

public abstract class ParkingBoy implements ParkingBehavior {
    protected final List<ParkingLot> parkingLots;

    protected ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }


    @Override
    public Ticket park(Car car) throws ParkingLotIsFullException {
        return parkingLots.stream()
                .max(parkingStrategy())
                .orElseThrow(ParkingLotIsFullException::new)
                .park(car);
    }

    @Override
    public Car pick(Ticket ticket) throws InvalidTicketException {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.containCar(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pick(ticket);
    }

    public boolean hasParkingSpace() {
        return parkingLots.stream().anyMatch(parkingLot -> !parkingLot.isFull());
    }

    protected abstract Comparator<ParkingLot> parkingStrategy();
}
