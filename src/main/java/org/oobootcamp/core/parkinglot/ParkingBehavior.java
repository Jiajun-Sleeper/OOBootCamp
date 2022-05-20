package org.oobootcamp.core.parkinglot;

import org.oobootcamp.core.parkinglot.Exceptions.InvalidTicketException;
import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotIsFullException;

public interface ParkingBehavior {
    Ticket park(Car car) throws ParkingLotIsFullException;

    Car pick(Ticket ticket) throws InvalidTicketException;
}
