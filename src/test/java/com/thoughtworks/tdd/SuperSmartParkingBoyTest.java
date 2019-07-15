package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SuperSmartParkingBoyTest {
    @Test
    public void should_return_fifthCar_from_second_parkinglot_when_first_parkinglot_has_parked_three_Car_and_secondparkingLot_has_parking_one_Car() throws Exception {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thridCar = new Car();
        Car fourthCar = new Car();
        Car fifthCar = new Car();
        ParkingLot firstParkingLot = new ParkingLot(5);
        firstParkingLot.parkCar(firstCar);
        firstParkingLot.parkCar(secondCar);
        firstParkingLot.parkCar(thridCar);
        ParkingLot sencondParkingLot = new ParkingLot();
        sencondParkingLot.parkCar(fourthCar);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(firstParkingLot);
        superSmartParkingBoy.manageParkingLot(sencondParkingLot);
        //when
        Ticket ticket = superSmartParkingBoy.parkCar(fifthCar);
        Car fetchCar = sencondParkingLot.fetchCar(ticket);
        //then
        assertSame(fifthCar,fetchCar);
    }
    @Test
    public void should_return_car_when_give_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket ticket = superSmartParkingBoy.parkCar(car);
        Car fetchCar = superSmartParkingBoy.fetchCar(ticket);
        //then
        assertSame(car,fetchCar);
    }
    @Test
    public void should_fetch_multiple_car_when_give_multiple_ticket() throws Exception {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket firstTicket = superSmartParkingBoy.parkCar(firstCar);
        Ticket secondTicket = superSmartParkingBoy.parkCar(secondCar);
        Car fetchFirstCar = superSmartParkingBoy.fetchCar(firstTicket);
        Car fetchSecondCar = superSmartParkingBoy.fetchCar(secondTicket);
        //then
        assertSame(firstCar,fetchFirstCar);
        assertSame(secondCar,fetchSecondCar);
    }
    @Test
    public void should_fetch_no_car_when_give_wrong_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket ticket = superSmartParkingBoy.parkCar(car);
        Ticket wrongTicket = new Ticket();
        //then
        Assertions.assertThrows(Exception.class,()->{
            superSmartParkingBoy.fetchCar(wrongTicket);
        });
    }
    @Test
    public void should_fetch_no_car_when_not_give_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot);
        //when
        superSmartParkingBoy.parkCar(car);
        //then
        Assertions.assertThrows(Exception.class,()->{
            superSmartParkingBoy.fetchCar(null);
        });
    }
    @Test
    public void should_fetch_no_car_when_give_used_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket ticket = superSmartParkingBoy.parkCar(car);
        superSmartParkingBoy.fetchCar(ticket);
        Ticket usedTicket = ticket;
        //then
        Assertions.assertThrows(Exception.class,()->{
            superSmartParkingBoy.fetchCar(usedTicket);
        });
    }
    @Test
    public void should_not_park_car_when_parkinglot_has_no_position() throws Exception {
        //given
        Car eleventhCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot);
        //when
        for(int i = 0;i < 10;i++){
            Car car = new Car();
            superSmartParkingBoy.parkCar(car);
        }
        //then
        Assertions.assertThrows(Exception.class,()->{
            superSmartParkingBoy.parkCar(eleventhCar);
        });
    }
    @Test
    public void should_return_unrecognized_parking_ticket_when_give_wrong_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(superSmartParkingBoy);
        customer.setParkable(superSmartParkingBoy);
        String exceptedMessage = "Unrecognized parking ticket.";
        //when
        Ticket ticket = superSmartParkingBoy.parkCar(car);
        Ticket wrongTicket = new Ticket();
        customer.setParkingTicket(wrongTicket);
        String message=customer.fetchMyCar();
        //then
        assertSame(exceptedMessage,message);
    }
    @Test
    public void should_return_Please_provide_your_parking_ticket_when_give_no_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(superSmartParkingBoy);
        customer.setParkable(superSmartParkingBoy);
        String exceptedMessage = "Please provide your parking ticket.";
        //when
        Ticket ticket = superSmartParkingBoy.parkCar(car);
        String message=customer.fetchMyCar();
        //then
        assertSame(exceptedMessage,message);
    }
    @Test
    public void should_return_not_enough_position_when_parkinglot_has_no_position() throws Exception {
        //given
        Car eleventhCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(superSmartParkingBoy);
        customer.setParkable(superSmartParkingBoy);
        customer.setMyCar(eleventhCar);
        String exceptedMessage = "Not enough position.";
        //when
        for(int i = 0;i < 10;i++){
            Car car = new Car();
            superSmartParkingBoy.parkCar(car);
        }
        String message=customer.parkMyCar();
        //then
        assertSame(exceptedMessage,message);
    }
    @Test
    public void should_return_eleventhCar_in_second_parkinglot_when_first_parkinglot_has_no_position() throws Exception {
        //given
        Car eleventhCar = new Car();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.manageParkingLot(firstParkingLot);
        superSmartParkingBoy.manageParkingLot(secondParkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(superSmartParkingBoy);
        customer.setParkable(superSmartParkingBoy);
        customer.setMyCar(eleventhCar);
        //when
        for(int i = 0;i < 10;i++){
            Car car = new Car();
            superSmartParkingBoy.parkCar(car);
        }
        Ticket ticket=superSmartParkingBoy.parkCar(eleventhCar);
        Car fetchCar=superSmartParkingBoy.fetchCar(ticket);
        //then
        assertSame(customer.getMyCar(),fetchCar);
    }
}
