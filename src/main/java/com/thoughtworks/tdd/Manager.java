package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Manager extends ParkingBoy{
    private List<ParkingBoy> managementList;
    private Parkable parkable;
    private Fetchable fetchable;

    public Manager() {
        managementList = new ArrayList<>();
    }

    public void setParkable(Parkable parkable) {
        this.parkable = parkable;
    }

    public void setFetchable(Fetchable fetchable) {
        this.fetchable = fetchable;
    }

    @Override
    public Ticket parkCar(Car car) throws Exception {
        if(parkable == null){
            return super.parkCar(car);
        }else {
            return specifyParkingBoyParkCar(car);
        }
    }

    @Override
    public Car fetchCar(Ticket ticket) throws Exception {
        if(fetchable == null){
            return super.fetchCar(ticket);
        }else{
            return specifyParkingBoyFetchCar(ticket);
        }
    }

    public void manageParkingBoy(ParkingBoy parkingBoy){
        this.managementList.add(parkingBoy);
    }

    public Ticket specifyParkingBoyParkCar(Car car) throws Exception {
        return parkable.parkCar(car);
    }

    public Car specifyParkingBoyFetchCar(Ticket ticket) throws Exception {
        return fetchable.fetchCar(ticket);
    }

}
