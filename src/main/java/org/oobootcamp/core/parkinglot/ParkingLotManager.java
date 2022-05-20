package org.oobootcamp.core.parkinglot;

import org.oobootcamp.core.parkinglot.Exceptions.InvalidTicketException;
import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotIsFullException;

import java.util.List;
import java.util.Optional;

public class ParkingLotManager implements ParkingBehavior{
    private final List<ParkingLot> parkingLots;
    private final List<ParkingBoy> parkingBoys;

    public ParkingLotManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        this.parkingLots = parkingLots;
        this.parkingBoys = parkingBoys;
    }

    @Override
    public Ticket park(Car car) throws ParkingLotIsFullException {
        Optional<ParkingBoy> chosenParkingBoy = parkingBoys.stream().filter(ParkingBoy::hasParkingSpace).findFirst();
        if (chosenParkingBoy.isPresent()) {
            return chosenParkingBoy.get().park(car);
        }
        return parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().orElseThrow(ParkingLotIsFullException::new).park(car);
    }

    @Override
    public Car pick(Ticket ticket) throws InvalidTicketException {
        return null;
    }
}
