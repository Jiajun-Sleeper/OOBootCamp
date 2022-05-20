package org.oobootcamp.core.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartyParkingBoy extends ParkingBoy {

    public SmartyParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected Comparator<ParkingLot> parkingStrategy() {
        return new FreeParkingSpaceNumberComparator();
    }


    public static class FreeParkingSpaceNumberComparator implements Comparator<ParkingLot> {

        @Override
        public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
            return Integer.compare((parkingLot1.getTotalCount() - parkingLot1.getCars().size()), parkingLot2.getTotalCount() - parkingLot2.getCars().size());
        }
    }
}
