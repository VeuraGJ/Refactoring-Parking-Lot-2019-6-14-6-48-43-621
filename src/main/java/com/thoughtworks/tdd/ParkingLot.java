package com.thoughtworks.tdd;



import java.util.HashMap;

public class ParkingLot  {
    private HashMap<Ticket,Car> parkingTicketCar;
    private int capacity = 10;

    public ParkingLot() {
        parkingTicketCar = new HashMap<>();
    }
    public ParkingLot(int capacity) {

        parkingTicketCar = new HashMap<>();
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public Ticket parkCar(Car car) {
        Ticket ticket = new Ticket();
        parkingTicketCar.put(ticket,car);
        return ticket;
    }
    public Car fetchCar(Ticket ticket) {
        return parkingTicketCar.remove(ticket);
    }
    public Boolean isCarInParkingLot(Ticket ticket) {
        return parkingTicketCar.keySet().contains(ticket);
    }


    public int getEmptyPosition() {
        return capacity-parkingTicketCar.size();
    }

    public double getAvailablePositionRate() {
        return getEmptyPosition() /(double)capacity;
    }

}
