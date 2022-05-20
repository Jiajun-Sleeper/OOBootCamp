package org.oobootcamp.core.parkinglot;

import java.util.Comparator;
import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {
    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }


    @Override
    protected Comparator<ParkingLot> parkingStrategy() {
        return new DoNothingComparator();
    }

    public static class DoNothingComparator implements Comparator<ParkingLot> {

        @Override
        public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
            return parkingLot1.isFull() ?
                    parkingLot2.isFull() ? 0 : -1
                    : 1;
        }

    }
}
