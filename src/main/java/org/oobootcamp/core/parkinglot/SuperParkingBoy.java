package org.oobootcamp.core.parkinglot;

import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotIsFullException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends ParkingBoy {
    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return super.parkingLots.stream()
                .max(new ParkingLot.FreeParkingSpaceRateComparator())
                .orElseThrow(ParkingLotIsFullException::new)
                .park(car);
    }

    @Override
    protected Comparator<ParkingLot> parkingStrategy() {
        return new FreeParkingSpaceRateComparator();
    }

    public static class FreeParkingSpaceRateComparator implements Comparator<ParkingLot> {

        @Override
        public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
            return new BigDecimal(parkingLot1.getTotalCount() - parkingLot1.getCars().size()).divide(new BigDecimal(parkingLot1.getTotalCount()), 2, RoundingMode.HALF_UP)
                    .compareTo(new BigDecimal(parkingLot2.getTotalCount() - parkingLot2.getCars().size()).divide(new BigDecimal(parkingLot2.getTotalCount()), 2, RoundingMode.HALF_UP));
        }

    }
}
