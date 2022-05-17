package org.oobootcamp.core.parkinglot;

import org.oobootcamp.core.parkinglot.Exceptions.InvalidTicketException;
import org.oobootcamp.core.parkinglot.Exceptions.NoParkingLotException;
import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotUnavailableException;

import java.util.List;

public class GraduateParkingBoy {
    private final List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }


    public Ticket park(Car car) {
        if (parkingLots.size() == 0) {
            throw new NoParkingLotException();
        }
        return parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().orElseThrow(ParkingLotUnavailableException::new).park(car);
    }

    public Car pickUpCar(Ticket ticket) {
        return parkingLots.stream().filter(parkingLot -> parkingLot.containCar(ticket)).findFirst().orElseThrow(InvalidTicketException::new).pickUpCar(ticket).get();
    }

}
