package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {
    @Test
    public void should_return_car_when_give_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket ticket = parkingBoy.parkCar(car);
        Car fetchCar = parkingBoy.fetchCar(ticket);
        //then
        assertSame(car,fetchCar);
    }
    @Test
    public void should_fetch_multiple_car_when_give_multiple_ticket() throws Exception {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket firstTicket = parkingBoy.parkCar(firstCar);
        Ticket secondTicket = parkingBoy.parkCar(secondCar);
        Car fetchFirstCar = parkingBoy.fetchCar(firstTicket);
        Car fetchSecondCar = parkingBoy.fetchCar(secondTicket);
        //then
        assertSame(firstCar,fetchFirstCar);
        assertSame(secondCar,fetchSecondCar);
    }
    @Test
    public void should_fetch_no_car_when_give_wrong_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket ticket = parkingBoy.parkCar(car);
        Ticket wrongTicket = new Ticket();
        //then
        Assertions.assertThrows(Exception.class,()->{
            parkingBoy.fetchCar(wrongTicket);
        });
    }
    @Test
    public void should_fetch_no_car_when_not_give_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        //when
        parkingBoy.parkCar(car);
        //then
        Assertions.assertThrows(Exception.class,()->{
            parkingBoy.fetchCar(null);
        });
    }
    @Test
    public void should_fetch_no_car_when_give_used_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        //when
        Ticket ticket = parkingBoy.parkCar(car);
        parkingBoy.fetchCar(ticket);
        Ticket usedTicket = ticket;
        //then
        Assertions.assertThrows(Exception.class,()->{
            parkingBoy.fetchCar(usedTicket);
        });
    }
    @Test
    public void should_not_park_car_when_parkinglot_has_no_position() throws Exception {
        //given
        Car eleventhCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        //when
        for(int i = 0;i < 10;i++){
            Car car = new Car();
            parkingBoy.parkCar(car);
        }
        //then
        Assertions.assertThrows(Exception.class,()->{
            parkingBoy.parkCar(eleventhCar);
        });
    }
    @Test
    public void should_return_unrecognized_parking_ticket_when_give_wrong_ticket() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(parkingBoy);
        customer.setParkable(parkingBoy);
        String exceptedMessage = "Unrecognized parking ticket.";
        //when
        Ticket ticket = parkingBoy.parkCar(car);
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
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(parkingBoy);
        customer.setParkable(parkingBoy);
        String exceptedMessage = "Please provide your parking ticket.";
        //when
        Ticket ticket = parkingBoy.parkCar(car);
        String message=customer.fetchMyCar();
        //then
        assertSame(exceptedMessage,message);
    }
    @Test
    public void should_return_not_enough_position_when_parkinglot_has_no_position() throws Exception {
        //given
        Car eleventhCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(parkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(parkingBoy);
        customer.setParkable(parkingBoy);
        customer.setMyCar(eleventhCar);
        String exceptedMessage = "Not enough position.";
        //when
        for(int i = 0;i < 10;i++){
            Car car = new Car();
            parkingBoy.parkCar(car);
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
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.manageParkingLot(firstParkingLot);
        parkingBoy.manageParkingLot(secondParkingLot);
        Customer customer = new Customer();
        customer.setFetchCar(parkingBoy);
        customer.setParkable(parkingBoy);
        customer.setMyCar(eleventhCar);
        //when
        for(int i = 0;i < 10;i++){
            Car car = new Car();
            parkingBoy.parkCar(car);
        }
        Ticket ticket=parkingBoy.parkCar(eleventhCar);
        Car fetchCar=parkingBoy.fetchCar(ticket);
        //then
        assertSame(customer.getMyCar(),fetchCar);
    }
}
