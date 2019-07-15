package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ManagerTest {
    @Test
    public void should_return_car_when_specify_parkingboy_to_park_car() throws Exception {
        //give
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        parkingBoy.setParkingLots(parkingLotList);
        Manager manager = new Manager();
        manager.setParkingLots(parkingLotList);
        //when
        manager.manageParkingBoy(parkingBoy);
        manager.setParkable(parkingBoy);
        Ticket ticket = manager.specifyParkingBoyParkCar(car);
        Car fetchCar = parkingBoy.fetchCar(ticket);
        //then
        assertSame(car,fetchCar);
    }
    @Test
    public void should_return_car_when_specify_parkingboy_to_fetch_car() throws Exception {
        //give
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        Manager manager = new Manager();
        parkingBoy.setParkingLots(parkingLotList);
        //when
        manager.manageParkingBoy(parkingBoy);
        manager.setParkable(parkingBoy);
        manager.setFetchable(parkingBoy);
        Ticket ticket = manager.specifyParkingBoyParkCar(car);
        Car fetchCar = manager.specifyParkingBoyFetchCar(ticket);
        //then
        assertSame(car,fetchCar);
    }
    @Test
    public void should_return_car_when_give_ticket() throws Exception {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        Manager manager = new Manager();
        manager.setParkingLots(parkingLotList);
        ParkingLot managedParkingLot = new ParkingLot();
        manager.manageParkingLot(managedParkingLot);
        //when
        Ticket ticket = manager.parkCar(car);
        Car fetchCar = manager.fetchCar(ticket);
        //then
        assertSame(car,fetchCar);
    }
    @Test
    public void should_return_unrecognized_parking_ticket_when_manager_specify_parkingboy_fetch_car_with_wrong_ticket() throws Exception {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy();
        Manager manager = new Manager();
        parkingBoy.setParkingLots(parkingLotList);
        manager.manageParkingBoy(parkingBoy);
        manager.setFetchable(parkingBoy);
        Customer customer = new Customer();
        customer.setMyCar(car);
        customer.setFetchCar(manager);
        customer.setParkable(manager);
        String exceptedMessage = "Unrecognized parking ticket.";
        //when
        customer.parkMyCar();
        Ticket wrongTicket = new Ticket();
        customer.setParkingTicket(wrongTicket);
        String message=customer.fetchMyCar();
        //then
        assertSame(exceptedMessage,message);
    }
    @Test
    public void should_return_Please_provide_your_parking_ticket_when_manger_specify_parkingBoy_fetch_car_without_ticket() throws Exception {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy();
        Manager manager = new Manager();
        manager.setParkingLots(parkingLotList);
        parkingBoy.setParkingLots(parkingLotList);
        manager.manageParkingBoy(parkingBoy);
        manager.setFetchable(parkingBoy);
        Customer customer = new Customer();
        customer.setMyCar(car);
        customer.setFetchCar(manager);
        customer.setParkable(manager);
        String exceptedMessage = "Please provide your parking ticket.";
        //when
        customer.parkMyCar();
        customer.fetchMyCar();
        String message=customer.fetchMyCar();
        //then
        assertSame(exceptedMessage,message);
    }
    @Test
    public void should_return_not_enough_position_when_parkinglot_has_no_position() throws Exception {
        //given
        Car eleventhCar = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy();
        Manager manager = new Manager();
        manager.setParkingLots(parkingLotList);
        parkingBoy.setParkingLots(parkingLotList);
        manager.manageParkingBoy(parkingBoy);
        manager.setParkable(parkingBoy);
        Customer customer = new Customer();
        customer.setMyCar(eleventhCar);
        customer.setFetchCar(manager);
        customer.setParkable(manager);
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
}
