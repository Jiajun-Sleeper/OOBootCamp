package org.oobootcamp.core.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotIsFullException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperParkingBoyTest {
    //Given：两个停车场，高空置率停车场1（容量为2，空的）、低空置率停车场2（容量为2，停1），和一辆车1
    //When： 让停车小弟停车时；
    //Then：停车成功，获得停车票，车停在高空置率停车场1
    @Test
    public void should_parking_in_parking_lot_1_when_super_parking_boy_park_the_car_given_parking_lot_1_with_high_percent_vacancy_rate_and_parking_lot_2_with_low_percent_vacancy_rate_and_a_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot2.park(new Car("any number"));
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car("陕A T123");
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        assertThat(parkingLot1.pick(ticket)).isEqualTo(car);
    }

    //Given：两个停车场，低空置率停车场1（容量为2，停1）、高空置率停车场2（容量为2，空的）和一辆车1；
    //When：让停车小弟停车时；
    //Then：停车成功，获得停车票，车停在高空置率停车场2
    @Test
    public void should_parking_in_parking_lot_2_when_super_parking_boy_park_the_car_given_parking_lot_1_with_low_percent_vacancy_rate_and_parking_lot_2_with_high_percent_vacancy_rate_and_a_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car("any number"));
        ParkingLot parkingLot2 = new ParkingLot(3);
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car("陕A T123");
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        assertThat(parkingLot2.pick(ticket)).isEqualTo(car);
    }

    //Given：停车场空置率相同 ，停车场1（容量为2，停1）、停车场2（容量为3，停1）和一辆车1；
    //When：让停车小弟停车时；
    //Then：停车成功，获得停车票，车停在停车场1
    @Test
    public void should_parking_in_parking_lot_1_when_super_parking_boy_park_the_car_given_parking_lot_1_with_3_free_space_and_parking_lot_2_with_3_free_space_and_a_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(2);
        parkingLot1.park(new Car("any number1"));
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot2.park(new Car("any number2"));
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car("陕A T123");
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        assertThat(ticket.getCarNo()).isEqualTo(car.getCarNo());
        assertThat(parkingLot1.pick(ticket)).isEqualTo(car);
    }

    //Given：停车场都满了 , 停车场1（容量为1，满的），停车场2（容量为1，满的），和一辆车1；
    //When：让停车小弟停车时；
    //Then：停车失败，返回“无停车场可用”
    @Test
    public void should_parking_failed_and_return_parking_lot_is_full_exception_when_super_parking_boy_park_the_car_given_parking_lot_1_with_no_free_space() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car("any number"));
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car("any number1"));
        List<ParkingLot> parkingLots = List.of(parkingLot1,parkingLot2);
        ParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car("陕A T123");
        //when & then
        assertThrows(ParkingLotIsFullException.class, () -> parkingBoy.park(car));
    }
}
