package org.oobootcamp.core.parkinglot;

import org.oobootcamp.core.parkinglot.Exceptions.CarNotFoundException;
import org.oobootcamp.core.parkinglot.Exceptions.DuplicateParkingException;
import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotIsFullException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ParkingLot {

    private final int totalCount;

    private final List<Car> cars = new ArrayList<>();

    public int getTotalCount() {
        return totalCount;
    }

    public List<Car> getCars() {
        return cars;
    }

    public ParkingLot(int count) {
        this.totalCount = count;
    }

    public Ticket park(Car car) {
        if (cars.size() == totalCount) {
            throw new ParkingLotIsFullException();
        }
        if (cars.stream().anyMatch(car1 -> car1.getCarNo().equals(car.getCarNo()))) {
            throw new DuplicateParkingException();
        }

        cars.add(car);
        return new Ticket(car.getCarNo());
    }

    public Car pick(Ticket ticket) {
        String carNo = ticket.getCarNo();

        Car car = cars.stream().filter(c -> c.getCarNo().equals(carNo)).findFirst().orElseThrow(CarNotFoundException::new);
        cars.remove(car);

        return car;
    }

    public boolean isFull() {
        return cars.size() == totalCount;
    }

    public boolean containCar(Ticket ticket) {
        return cars.stream().anyMatch(car -> Objects.equals(car.getCarNo(), ticket.getCarNo()));
    }

    public static class FreeParkingSpaceRateComparator implements Comparator<ParkingLot> {

        @Override
        public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2) {
            return new BigDecimal(parkingLot1.totalCount - parkingLot1.cars.size()).divide(new BigDecimal(parkingLot1.totalCount), 2, RoundingMode.HALF_UP)
                    .compareTo(new BigDecimal(parkingLot2.totalCount - parkingLot2.cars.size()).divide(new BigDecimal(parkingLot2.totalCount), 2, RoundingMode.HALF_UP));
        }


    }
}
