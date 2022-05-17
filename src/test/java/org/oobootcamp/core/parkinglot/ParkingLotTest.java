package org.oobootcamp.core.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.oobootcamp.core.parkinglot.Exceptions.CarNotFoundException;
import org.oobootcamp.core.parkinglot.Exceptions.DuplicateParkingException;
import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotUnavailableException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotTest {
    @Test
    public void should_parking_success_and_get_ticket_when_parking_given_a_car_and_available_parking_lot() {
        //given
        int count = 20;
        ParkingLot parkingLot = new ParkingLot(count);
        String carNo = "陕A T123";
        Car car = new Car(carNo);
        //when
        Ticket ticket = parkingLot.park(car);
        //then
        assertThat(ticket.getCarNo()).isEqualTo(carNo);
    }

    @Test
    public void should_parking_failed_with_parking_lot_unavailable_exception_when_parking_given_a_car_and_unavailable_parking_lot() {
        //given
        int count = 20;
        ParkingLot parkingLot = new ParkingLot(count);
        for (int i = 0; i < 20; i++) {
            Car car = new Car(UUID.randomUUID().toString());
            parkingLot.park(car);
        }
        String carNo = "陕A T123";
        Car car = new Car(carNo);
        //when
        assertThrows(ParkingLotUnavailableException.class, () -> parkingLot.park(car));
    }

    @Test
    public void should_parking_failed_with_duplicate_parking_exception_when_parking_given_a_parked_car_and_available_parking_lot() {
        //given
        int count = 20;
        ParkingLot parkingLot = new ParkingLot(count);
        String carNo = "陕A T123";
        Car car = new Car(carNo);
        parkingLot.park(car);
        //when
        assertThrows(DuplicateParkingException.class, () -> parkingLot.park(car));
    }

    @Test
    public void should_given_a_car_when_pick_up_given_a_right_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        Car myCar = new Car("陕A 12SF32");
        Ticket ticket = parkingLot.park(myCar);

        //when
        Optional<Car> car = parkingLot.pickUpCar(ticket);

        //then
        Assertions.assertTrue(car.isPresent());
        Assertions.assertEquals(myCar.getCarNo(), car.get().getCarNo());
    }

    @Test
    public void should_throw_car_not_found_exception_when_pick_up_given_fake_paring_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        Ticket ticket = new Ticket("陕A 12SF32");

        //when
        assertThrows(CarNotFoundException.class, () -> parkingLot.pickUpCar(ticket));
    }

    @Test
    public void should_return_true_give_a_full_parking_lot_when_check_if_the_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("1234"));

        boolean isFull = parkingLot.isFull();

        assertTrue(isFull);
    }

    @Test
    public void should_return_false_give_an_available_parking_lot_when_check_if_the_parking_lot_is_available() {
        ParkingLot parkingLot = new ParkingLot(1);

        boolean isFull = parkingLot.isFull();

        assertFalse(isFull);
    }

    @Test
    public void should_return_true_give_parking_lot_contain_a_car_when_check_if_the_parking_lot_contain_the_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("1234");
        Ticket ticket = parkingLot.park(car);

        boolean isContain = parkingLot.containCar(ticket);

        assertTrue(isContain);
    }

    @Test
    public void should_return_false_give_parking_lot_not_contain_a_car_when_check_if_the_parking_lot_contain_the_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = new Ticket("5678");

        boolean isContain = parkingLot.containCar(ticket);

        assertFalse(isContain);
    }
}
