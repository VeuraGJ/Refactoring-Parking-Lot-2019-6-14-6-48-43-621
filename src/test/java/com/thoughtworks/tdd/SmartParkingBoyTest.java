package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SmartParkingBoyTest {
    @Test
    public void should_return_secondCar_from_second_parkinglot_when_first_parkinglot_has_parking_one_Car_and_secondparkingLot_is_empty() throws Exception {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot sencondParkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(firstParkingLot);
        smartParkingBoy.manageParkingLot(sencondParkingLot);
        //when
        smartParkingBoy.parkCar(firstCar);
        Ticket secondTicket = smartParkingBoy.parkCar(secondCar);
        Car fetchCar = sencondParkingLot.fetchCar(secondTicket);
        //then
        assertSame(secondCar,fetchCar);
    }
    @Test
    public void should_return_car_when_give_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket ticket = smartParkingBoy.parkCar(car);
        Car fetchCar = smartParkingBoy.fetchCar(ticket);
        //then
        assertSame(car,fetchCar);
    }
    @Test
    public void should_fetch_multiple_car_when_give_multiple_ticket() throws Exception {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket firstTicket = smartParkingBoy.parkCar(firstCar);
        Ticket secondTicket = smartParkingBoy.parkCar(secondCar);
        Car fetchFirstCar = smartParkingBoy.fetchCar(firstTicket);
        Car fetchSecondCar = smartParkingBoy.fetchCar(secondTicket);
        //then
        assertSame(firstCar,fetchFirstCar);
        assertSame(secondCar,fetchSecondCar);
    }
    @Test
    public void should_fetch_no_car_when_give_wrong_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket ticket = smartParkingBoy.parkCar(car);
        Ticket wrongTicket = new Ticket();
        //then
        Assertions.assertThrows(Exception.class,()->{
            smartParkingBoy.fetchCar(wrongTicket);
        });
    }
    @Test
    public void should_fetch_no_car_when_not_give_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot);
        //when
        smartParkingBoy.parkCar(car);
        //then
        Assertions.assertThrows(Exception.class,()->{
            smartParkingBoy.fetchCar(null);
        });
    }
    @Test
    public void should_fetch_no_car_when_give_used_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket ticket = smartParkingBoy.parkCar(car);
        smartParkingBoy.fetchCar(ticket);
        Ticket usedTicket = ticket;
        //then
        Assertions.assertThrows(Exception.class,()->{
            smartParkingBoy.fetchCar(usedTicket);
        });
    }
    @Test
    public void should_not_park_car_when_parkinglot_has_no_position() throws Exception {
        //given
        Car eleventhCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot);
        //when
        for(int i = 0;i < 10;i++){
            Car car = new Car();
            smartParkingBoy.parkCar(car);
        }
        //then
        Assertions.assertThrows(Exception.class,()->{
            smartParkingBoy.parkCar(eleventhCar);
        });
    }
    @Test
    public void should_return_unrecognized_parking_ticket_when_give_wrong_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(smartParkingBoy);
        customer.setParkable(smartParkingBoy);
        String exceptedMessage = "Unrecognized parking ticket.";
        //when
        Ticket ticket = smartParkingBoy.parkCar(car);
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
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(smartParkingBoy);
        customer.setParkable(smartParkingBoy);
        String exceptedMessage = "Please provide your parking ticket.";
        //when
        Ticket ticket = smartParkingBoy.parkCar(car);
        String message=customer.fetchMyCar();
        //then
        assertSame(exceptedMessage,message);
    }
    @Test
    public void should_return_not_enough_position_when_parkinglot_has_no_position() throws Exception {
        //given
        Car eleventhCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(smartParkingBoy);
        customer.setParkable(smartParkingBoy);
        customer.setMyCar(eleventhCar);
        String exceptedMessage = "Not enough position.";
        //when
        for(int i = 0;i < 10;i++){
            Car car = new Car();
            smartParkingBoy.parkCar(car);
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
        ParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.manageParkingLot(firstParkingLot);
        smartParkingBoy.manageParkingLot(secondParkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(smartParkingBoy);
        customer.setParkable(smartParkingBoy);
        customer.setMyCar(eleventhCar);
        //when
        for(int i = 0;i < 10;i++){
            Car car = new Car();
            smartParkingBoy.parkCar(car);
        }
        Ticket ticket=smartParkingBoy.parkCar(eleventhCar);
        Car fetchCar=smartParkingBoy.fetchCar(ticket);
        //then
        assertSame(customer.getMyCar(),fetchCar);
    }
}
