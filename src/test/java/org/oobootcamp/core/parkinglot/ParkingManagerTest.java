package org.oobootcamp.core.parkinglot;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.oobootcamp.core.parkinglot.Exceptions.ParkingLotIsFullException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ParkingManagerTest {

    /**
     * 一、可以让小弟停车
     */
    // simple1: 可以让graduate小弟停车
    // Given: 一个graduate小弟（管理1个停车场）, 一辆车
    // When: 通过manager停车
    // Then: 停车成功，返回车票，车是被graduate小弟停进去的
    @Test
    public void should_parking_success_and_get_a_ticket_when_parking_lot_manager_park_the_car_given_a_graduate_parking_boy_with_1_free_parking_lot_and_a_car() {
        //given
        var parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(new ParkingLot(1));
        ParkingBoy graduateParkingBoy = spy(new GraduateParkingBoy(parkingLots));
        List<ParkingBoy> parkingBoys = Lists.list(graduateParkingBoy);
        ParkingLotManager manager = new ParkingLotManager(List.of(), parkingBoys);
        Car car = new Car("陕A 12332");
        //when
        Ticket ticket = manager.park(car);
        //then
        verify(graduateParkingBoy).park(car);
    }

    // simple2: 可以让smart小弟停车
    // Given: 一个smart小弟（管理1个停车场）, 一辆车
    // When: 通过manager停车
    // Then: 停车成功，返回车票，车是被smart小弟停进去的
    @Test
    public void should_parking_success_and_get_a_ticket_when_parking_lot_manager_park_the_car_given_a_smart_parking_boy_with_1_free_parking_lot_and_a_car() {
        //given
        var parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(new ParkingLot(1));
        ParkingBoy smartyParkingBoy = spy(new SmartyParkingBoy(parkingLots));
        List<ParkingBoy> parkingBoys = Lists.list(smartyParkingBoy);
        ParkingLotManager manager = new ParkingLotManager(List.of(), parkingBoys);
        Car car = new Car("陕A 12332");
        //when
        Ticket ticket = manager.park(car);
        //then
        verify(smartyParkingBoy).park(car);
    }

    // simple3: 可以让super小弟停车
    // Given: 一个super小弟（管理1个停车场）, 一辆车
    // When: 通过manager停车
    // Then: 停车成功，返回车票，车是被super小弟停进去的
    @Test
    public void should_parking_success_and_get_a_ticket_when_parking_lot_manager_park_the_car_given_a_super_parking_boy_with_1_free_parking_lot_and_a_car() {
        //given
        var parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(new ParkingLot(1));
        ParkingBoy superParkingBoy = spy(new SuperParkingBoy(parkingLots));
        List<ParkingBoy> parkingBoys = Lists.list(superParkingBoy);
        ParkingLotManager manager = new ParkingLotManager(List.of(), parkingBoys);
        Car car = new Car("陕A 12332");
        //when
        Ticket ticket = manager.park(car);
        //then
        verify(superParkingBoy).park(car);
    }

    /**
     * 2、自己可以停车
     */
    //simple1：停车小弟管理的车位都满了的的时候，自己管理的车位没有满，自己可以停车
    // Given: 3个小弟（graduate小弟、smart小弟、super小弟）分别管理1个满的停车场（容量1），manager管了一个空的停车场（容量1） ， 一辆车
    // When: 通过manager停车
    // Then: 停车成功，返回车票，车是被manager停进去的
    @Test
    public void should_parking_success_and_get_a_ticket_when_parking_lot_manager_park_the_car_given_3_parking_boy_with_full_parking_lot_and_manager_with_a_free_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("any number1"));
        List<ParkingLot> parkingLotList1 = Lists.list(parkingLot);
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList1);

        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car("any number2"));
        List<ParkingLot> parkingLotList2 = Lists.list(parkingLot2);
        ParkingBoy smartyParkingBoy = new SmartyParkingBoy(parkingLotList2);

        ParkingLot parkingLot3 = new ParkingLot(1);
        parkingLot3.park(new Car("any number3"));
        List<ParkingLot> parkingLotList3 = Lists.list(parkingLot3);
        ParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList3);

        List<ParkingBoy> parkingBoys = Lists.list(graduateParkingBoy, smartyParkingBoy, superParkingBoy);
        ParkingLot managerParkingLot = spy(new ParkingLot(1));
        ParkingLotManager manager = new ParkingLotManager(List.of(managerParkingLot), parkingBoys);
        Car car = new Car("陕A 12332");
        //when
        Ticket ticket = manager.park(car);
        //then
        verify(managerParkingLot).park(car);
    }


    //simple2：没有停车小弟的时候，自己管理的车位没有满，自己可以停车
    // Given: 0个小弟，manager管了一个空的停车场（容量1） ， 一辆车
    // When: 通过manager停车
    // Then: 停车成功，返回车票，车是被manager停进去的
//    @Test
//    public void should_parking_success_and_get_a_ticket_when_parking_lot_manager_park_the_car_given_3_parking_boy_with_full_parking_lot_and_manager_with_a_free_parking_lot_and_a_car() {
//        //given
//        List<ParkingBoy> parkingBoys = Collections.emptyList();
//        ParkingLot managerParkingLot = spy(new ParkingLot(1));
//        ParkingLotManager manager = new ParkingLotManager(List.of(managerParkingLot), parkingBoys);
//        Car car = new Car("陕A 12332");
//        //when
//        Ticket ticket = manager.park(car);
//        //then
//        verify(parkingBoys).park(car);
//    }

    /**
     * 3、停车位满了，无法停车
     */
    //simple1：停车小弟管理的车位都满了的的时候，自己管理的车位也满了，报“车位已满”
    // Given: 3个小弟（graduate小弟、smart小弟、super小弟）分别管理1个满的停车场（容量1），manager管了一个满的停车场（容量1） ， 一辆车
    // When: 通过manager停车
    // Then: 停车失败，返回"停车场已满"
    @Test
    public void should_throw_parking_lot_is_full_exception_when_parking_lot_manager_park_the_car_given_3_parking_boy_with_full_parking_lot_and_manager_with_a_full_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("any number1"));
        List<ParkingLot> parkingLotList1 = Lists.list(parkingLot);
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList1);

        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car("any number2"));
        List<ParkingLot> parkingLotList2 = Lists.list(parkingLot2);
        ParkingBoy smartyParkingBoy = new SmartyParkingBoy(parkingLotList2);

        ParkingLot parkingLot3 = new ParkingLot(1);
        parkingLot3.park(new Car("any number3"));
        List<ParkingLot> parkingLotList3 = Lists.list(parkingLot3);
        ParkingBoy superParkingBoy = new SuperParkingBoy(parkingLotList3);

        List<ParkingBoy> parkingBoys = Lists.list(graduateParkingBoy, smartyParkingBoy, superParkingBoy);
        ParkingLot managerParkingLot = new ParkingLot(1);
        managerParkingLot.park(new Car("any number4"));
        ParkingLotManager manager = new ParkingLotManager(List.of(managerParkingLot), parkingBoys);
        Car car = new Car("陕A 12332");
        //when & then
        assertThrows(ParkingLotIsFullException.class, () -> manager.park(car));
    }
//
//    //simple2：没有停车小弟的时候自己管理的车位满了，报“车位已满”
//    // Given: 0个小弟，manager管了一个满的停车场（容量1） ， 一辆车
//    // When: 通过manager停车
//    // Then: 停车失败，返回"停车场已满"
//    @Test
//    public void should_throw_parking_lot_is_full_exception_when_parking_lot_manager_park_the_car_given_3_parking_boy_with_full_parking_lot_and_manager_with_a_full_parking_lot_and_a_car() {
//        //given
//        List<ParkingBoy> parkingBoys = Collections.emptyList();
//        ParkingLot managerParkingLot = new ParkingLot(1);
//        managerParkingLot.park(new Car("any number4"));
//        ParkingLotManager manager = new ParkingLotManager(List.of(managerParkingLot), parkingBoys);
//        Car car = new Car("陕A 12332");
//        //when & then
//        assertThrows(ParkingLotIsFullException.class, () -> manager.park(car));
//    }
//
//    /**
//     * 4、可以让小弟取车
//     */
//    // simple1：可以让graduate停车小弟取车
//    // Given: 1个graduate小弟（容量2，停了车1和车2） ，车2的停车票
//    // When: 通过manager取车
//    // Then: 取车成功，获得车2
//    @Test
//    public void should_pick_car_when_parking_lot_manager_pick_car_given_a_graduate_parking_boy_with_a_parking_lot_with_car1_and_car2_and_car2_ticket() {
//        //given
//        var parkingLots = new ArrayList<ParkingLot>();
//        ParkingLot parkingLot = new ParkingLot(1);
//        parkingLot.park(new Car("car1 number"));
//        Car expectedCar = new Car("car2 number");
//        Ticket ticket = parkingLot.park(car2);
//        parkingLots.add(parkingLot);
//        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
//        List<ParkingBoy> parkingBoys = Lists.list(graduateParkingBoy);
//        ParkingLotManager manager = new ParkingLotManager(List.of(), parkingBoys);
//
//        //when
//        Car actualCar = manager.pick(ticket);
//        //then
//        assertThat(actualCar).isSameAs(expectedCar);
//    }
}
