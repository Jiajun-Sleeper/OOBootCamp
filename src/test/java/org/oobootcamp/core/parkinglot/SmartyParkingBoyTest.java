package org.oobootcamp.core.parkinglot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.core.parkinglot.Exceptions.InvalidTicketException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartyParkingBoyTest {
    //Given：两个停车场1（容量为3，空的）、停车场2（容量为1，空的）和一辆车1
    //When： 让停车小弟停车时；
    //Then：停车成功，获得停车场1的停车票1
    @Test
    public void should_parking_in_parking_lot_1_given_parking_lot_1_with_3_free_space_and_parking_lot_2_with_1_free_space_and_a_car_when_smart_parking_boy_park_the_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(3);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new SmartyParkingBoy(parkingLots);
        Car car = new Car("陕A T123");
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        assertThat(ticket.getCarNo()).isEqualTo(car.getCarNo());
        assertThat(parkingLot1.pick(ticket)).isEqualTo(car);
    }

    //Given：两个停车场1（容量为1，空的）、停车场2（容量为3，空的）和一辆车1；
    //When：让停车小弟停车时；
    //Then：停车成功，获得停车场2的停车票2
    @Test
    public void should_parking_in_parking_lot_2_given_parking_lot_1_with_1_free_space_and_parking_lot_2_with_3_free_space_and_a_car_when_smart_parking_boy_park_the_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new SmartyParkingBoy(parkingLots);
        Car car = new Car("陕A T123");
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        assertThat(ticket.getCarNo()).isEqualTo(car.getCarNo());
        assertThat(parkingLot2.pick(ticket)).isEqualTo(car);
    }

    //Given：两个停车场1（容量为3，空的）、停车场2（容量为3，空的）和一辆车1；
    //When：让停车小弟停车时；
    //Then：停车成功，获得停车场1的停车票1
    @Test
    public void should_parking_in_parking_lot_1_given_parking_lot_1_with_3_free_space_and_parking_lot_2_with_3_free_space_and_a_car_when_smart_parking_boy_park_the_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(3);
        ParkingLot parkingLot2 = new ParkingLot(3);
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new SmartyParkingBoy(parkingLots);
        Car car = new Car("陕A T123");
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        assertThat(ticket.getCarNo()).isEqualTo(car.getCarNo());
        assertThat(parkingLot1.pick(ticket)).isEqualTo(car);
    }

    //Given：一个停车场1（容量为1，满的），和一辆车1；
    //When：让停车小弟停车时；
    //Then：停车失败，返回“无停车场可用”
    @Test
    public void should_parking_failed_and_return_no_available_parking_lot_given_parking_lot_1_with_no_free_space_when_smart_parking_boy_park_the_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car("any number"));
        List<ParkingLot> parkingLots = List.of(parkingLot1);
        ParkingBoy parkingBoy = new SmartyParkingBoy(parkingLots);
        Car car = new Car("陕A T123");
        //when
        //then
        assertThrows(NoAvailableParkingLotException.class, () -> parkingBoy.park(car));

    }

    //Given：两个停车场1（容量为1，满的）、停车场2（容量为1，满的）和一张停车票2（车2停在停车场2）；
    //When：让停车小弟取车时；
    //Then：取车成功，获得车2
    @Test
    public void should_get_the_car_given_parking_lot_1_parked_a_car_and_parking_lot_2_parked_the_expected_car_and_a_ticket_of_the_expected_car_when_smart_parking_boy_pick_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car("any number"));
        ParkingLot parkingLot2 = new ParkingLot(1);
        Car expectedCar = new Car("陕A T123");
        Ticket ticket = parkingLot2.park(expectedCar);
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new SmartyParkingBoy(parkingLots);
        //when
        Car actualCar = parkingBoy.pick(ticket);
        //then
        assertThat(actualCar).isEqualTo(expectedCar);
    }

    //Given：给小弟一个停车场1（容量为1，满的），和一张无效停车票；
    //When：让停车小弟取车时；
    //Then：取车失败，返回“无效车票”
    @Test
    public void should_get_the_car_given_parking_lot_1_parked_a_car_and_parking_lot_2_parked_a_car_and_an_invalid_ticket_when_smart_parking_boy_pick_car() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car("any number"));
        List<ParkingLot> parkingLots = List.of(parkingLot1);
        ParkingBoy parkingBoy = new SmartyParkingBoy(parkingLots);
        Ticket ticket = new Ticket("invalid car number");
        //when
        //then
        assertThrows(InvalidTicketException.class, () -> parkingBoy.pick(ticket));
    }
}
